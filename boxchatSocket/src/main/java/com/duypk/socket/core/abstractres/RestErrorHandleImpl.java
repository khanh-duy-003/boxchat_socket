package com.duypk.socket.core.abstractres;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.duypk.socket.core.basereponse.BaseException;
import com.duypk.socket.core.basereponse.BaseRes;
import com.duypk.socket.core.basereponse.ErrorDetailRes;
import com.duypk.socket.core.basereponse.ExceptionCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Service
public class RestErrorHandleImpl implements RestErrorHandle {
	
	@Autowired
    private MessageSource messageSource;

    private AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
    
    public static final String UNDERSCORE = "_";
    
    public static final int STATUS = HttpStatus.INTERNAL_SERVER_ERROR.value();
    
    public static final String MESSAGE = HttpStatus.INTERNAL_SERVER_ERROR.name();
    
    public static final String TITLE = "System Exception";

    @Override
    public BaseRes handleBindingResult(BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse httpServletResponse, long start) {
        long took = start != 0 ? (System.currentTimeMillis() - start) : start;
        Locale locale = acceptHeaderLocaleResolver.resolveLocale(request);
        BaseRes baseRes = new BaseRes();
        baseRes.setStatus(HttpStatus.BAD_REQUEST.value());
        baseRes.setMessage(HttpStatus.BAD_REQUEST.name());
        baseRes.setTitle("Validation Exception");
        Object data = bindingResult.getAllErrors();
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<ErrorDetailRes> errorDetails = new ArrayList<>();
        for (FieldError fieldError : errors) {
            ErrorDetailRes errorDetailRes = new ErrorDetailRes();
            setCustomBindingResultException(fieldError, errorDetailRes, locale);
            errorDetails.add(errorDetailRes);
        }
        baseRes.setErrors(errorDetails);
        baseRes.setTime(new Date());
        baseRes.setTook(took);
        baseRes.setData(data);
        if (httpServletResponse != null) {
            httpServletResponse.setStatus(baseRes.getStatus());
        }
        return baseRes;
    }

    private void setCustomBindingResultException(FieldError fieldError, ErrorDetailRes errorDetailRes, Locale locale) {
        String code = fieldError.getCode();
        String defaultMessage = fieldError.getDefaultMessage();
        if (code != null && code.contains(UNDERSCORE)) {
            ExceptionCode expCode = new ExceptionCode(code);
            errorDetailRes.setCode(String.valueOf(expCode.getValue()));
            errorDetailRes.setMessage(messageSource.getMessage(expCode.getText(), fieldError.getArguments(),
                    fieldError.getDefaultMessage(), locale));
        } else if (defaultMessage != null && defaultMessage.contains(UNDERSCORE)) {
            ExceptionCode expCode = new ExceptionCode(defaultMessage);
            errorDetailRes.setCode(String.valueOf(expCode.getValue()));
            errorDetailRes.setMessage(messageSource.getMessage(expCode.getText(), fieldError.getArguments(),
                    fieldError.getDefaultMessage(), locale));
        } else {
            JavaxValidationExceptionCodeEnum codeEnum = JavaxValidationExceptionCodeEnum
                    .resolveByCode(fieldError.getCode());
            errorDetailRes.setCode(codeEnum.getValue());
            errorDetailRes.setMessage(messageSource.getMessage(codeEnum.getMessageCode(), fieldError.getArguments(),
                    fieldError.getDefaultMessage(), locale));
        }
        errorDetailRes.setObjectName(fieldError.getObjectName());
        errorDetailRes.setField(fieldError.getField());
        errorDetailRes.setRejectValue(fieldError.getRejectedValue());
    }

    @Override
    public BaseRes handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request,
            HttpServletResponse httpServletResponse, long start) {
        long took = start != 0 ? (System.currentTimeMillis() - start) : start;
        Locale locale = acceptHeaderLocaleResolver.resolveLocale(request);
        BaseRes baseRes = new BaseRes();
        baseRes.setStatus(HttpStatus.BAD_REQUEST.value());
        baseRes.setMessage(HttpStatus.BAD_REQUEST.name());
        List<ErrorDetailRes> errorDetails = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            ErrorDetailRes errorDetailRes = new ErrorDetailRes();
            setCustomConstraintViolationException(violation, errorDetailRes, locale);
            errorDetails.add(errorDetailRes);
        }
        baseRes.setTitle("Validation Exception");
        baseRes.setErrors(errorDetails);
        baseRes.setTime(new Date());
        baseRes.setTook(took);
        if (httpServletResponse != null) {
            httpServletResponse.setStatus(baseRes.getStatus());
        }
        return baseRes;
    }

    private void setCustomConstraintViolationException(ConstraintViolation<?> violation, ErrorDetailRes errorDetailRes,
            Locale locale) {
        String code = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
        JavaxValidationExceptionCodeEnum codeEnum = JavaxValidationExceptionCodeEnum.resolveByCode(code);
        errorDetailRes.setCode(codeEnum.getValue());
        errorDetailRes
                .setMessage(messageSource.getMessage(codeEnum.getMessageCode(), null, violation.getMessage(), locale));
        errorDetailRes.setObjectName(null);
        errorDetailRes.setField(((PathImpl) violation.getPropertyPath()).getLeafNode().asString());
        errorDetailRes.setRejectValue(violation.getInvalidValue());
    }

    @Override
    public BaseRes handleException(Exception ex, HttpServletRequest request, HttpServletResponse httpServletResponse,
            long start) {
        long took = start != 0 ? (System.currentTimeMillis() - start) : start;
        Locale locale = acceptHeaderLocaleResolver.resolveLocale(request);
        if (ex instanceof BaseException) {
            BaseException baseException = (BaseException) ex;
            BaseRes baseRes = new BaseRes(baseException, took);
            ExceptionCode expCode = baseException.getExceptionCode();
            if (expCode != null) {
                ErrorDetailRes errorDetailRes = new ErrorDetailRes();
                errorDetailRes.setCode(String.valueOf(expCode.getValue()));
                errorDetailRes.setMessage(messageSource.getMessage(expCode.getText(), baseException.getArgs(),
                        baseException.getDefaultMessage(), locale));
                baseRes.addError(errorDetailRes);
            }
            if (httpServletResponse != null) {
                httpServletResponse.setStatus(baseRes.getStatus());
            }
            return baseRes;
        } else if (ex instanceof DetailException) {
            DetailException detailException = (DetailException) ex;
            BaseRes baseRes = new BaseRes(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
                    "Detail Exception", took);
            ExceptionCode expCode = detailException.getExceptionCode();
            if (expCode != null) {
                ErrorDetailRes errorDetailRes = new ErrorDetailRes();
                errorDetailRes.setCode(String.valueOf(expCode.getValue()));
                errorDetailRes.setMessage(messageSource.getMessage(expCode.getText(), detailException.getParamater(),
                        detailException.getMessage(), locale));
                baseRes.addError(errorDetailRes);
            }
            if (httpServletResponse != null) {
                httpServletResponse.setStatus(baseRes.getStatus());
            }
            return baseRes;
        } else {
            BaseRes baseRes = new BaseRes(STATUS, MESSAGE, TITLE, took);
            ErrorDetailRes errorDetailRes = new ErrorDetailRes();
            errorDetailRes.setCode(String.valueOf(STATUS));
            if (Objects.isNull(errorDetailRes.getMessage()) || errorDetailRes.getMessage().isEmpty())
                errorDetailRes.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            baseRes.addError(errorDetailRes);
            if (httpServletResponse != null) {
                httpServletResponse.setStatus(baseRes.getStatus());
            }
            return baseRes;
        }
    }

}
