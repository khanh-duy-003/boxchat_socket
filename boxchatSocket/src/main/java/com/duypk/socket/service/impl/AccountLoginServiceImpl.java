package com.duypk.socket.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.duypk.socket.entity.PkDevAccountEntity;
import com.duypk.socket.repository.PkDevAccountRepository;
import com.duypk.socket.req.LoginReq;
import com.duypk.socket.req.RegisterReq;
import com.duypk.socket.res.LoginRes;
import com.duypk.socket.service.AccountLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountLoginServiceImpl implements AccountLoginService {
	
	@Autowired
	private PkDevAccountRepository accountRepository;
	
	@Value("${jwt.signerKey}")
	private String SIGNER_KEY;
	
	/* START QUERY */
	private boolean checkExitAccount1(String username) {
        return Objects.nonNull(accountRepository.findOne(username));
    }
	
	/* END QUERY */

	/* START LOGIC */
	@Override
	public LoginRes checkExitAccount(LoginReq req) throws Exception {
		var user = accountRepository.findOne(req.getUsername());
		if (Objects.isNull(user))
			throw new ApplicationContextException("lỗi không tồn tại");
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		boolean pwCheck = passwordEncoder.matches(req.getPassword(), user.getPassword());
		
		if (!pwCheck)
			throw new ApplicationContextException("Sai pass");
		
		var token = this.generateToken(req.getUsername());
		
		return LoginRes.builder().token(token).build();
		
	}
	
	private String generateToken(String username) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		
		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
											.subject(username)
											.issuer(username)
											.issueTime(new Date())
											.expirationTime(new Date(
													Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
											))
											.claim("customClaim", "Custom")
											.build();
		
		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
		
		JWSObject jwsObject = new JWSObject(header, payload);
		
		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
    public void register(RegisterReq req) throws Exception {
        if (this.checkExitAccount1(req.getUsername()))
            throw new ApplicationContextException("username tồn tại");
        
        ObjectMapper mapper = new ObjectMapper();
        PkDevAccountEntity accountEntity = mapper.convertValue(req, PkDevAccountEntity.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        accountEntity.setPassword(passwordEncoder.encode(req.getPassword()));
        accountEntity.setActive(1L);
        accountEntity.setCreatedDate(new Date());
        accountRepository.create(accountEntity);
    }

}
