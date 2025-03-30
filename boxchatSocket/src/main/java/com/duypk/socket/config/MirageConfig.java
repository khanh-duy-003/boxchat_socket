//package com.duypk.socket.config;
//
//import org.slf4j.bridge.SLF4JBridgeHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//import vn.com.unit.miragesql.miragesql.SqlManager;
//import vn.com.unit.miragesql.miragesql.SqlManagerImpl;
//import vn.com.unit.miragesql.miragesql.bean.BeanDescFactory;
//import vn.com.unit.miragesql.miragesql.bean.FieldPropertyExtractor;
//import vn.com.unit.miragesql.miragesql.dialect.MySQLDialect;
//import vn.com.unit.miragesql.miragesql.integration.spring.SpringConnectionProvider;
//import vn.com.unit.miragesql.miragesql.naming.RailsLikeNameConverter;
//import vn.com.unit.miragesql.miragesql.provider.ConnectionProvider;
//
//public class MirageConfig {
//	
//	@Autowired
//    private DataSourceTransactionManager transactionManager;
//	
//	@Bean
//    public SqlManager sqlManagerService() {
//
//        // Bridge java.util.logging used by mirage
//        SLF4JBridgeHandler.removeHandlersForRootLogger();
//        SLF4JBridgeHandler.install();
//
//        SqlManager sqlManager = new SqlManagerImpl();
//        sqlManager.setConnectionProvider(connectionProvider());
//        sqlManager.setDialect(new MySQLDialect());
//        sqlManager.setBeanDescFactory(beanDescFactory());
//        sqlManager.setNameConverter(new RailsLikeNameConverter());
//
//        return sqlManager;
//    }
//
//    @Bean
//    public ConnectionProvider connectionProvider() {
//        SpringConnectionProvider springConnectionProvider = new SpringConnectionProvider();
//        springConnectionProvider.setTransactionManager(transactionManager);
//        return springConnectionProvider;
//    }
//
//    @Bean
//    public BeanDescFactory beanDescFactory() {
//        BeanDescFactory beanDescFactory = new BeanDescFactory();
//        beanDescFactory.setPropertyExtractor(new FieldPropertyExtractor());
//        return beanDescFactory;
//    }
//
//}
