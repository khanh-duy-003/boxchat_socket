package com.duypk.socket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import vn.com.unit.miragesql.miragesql.SqlManagerImpl;
import vn.com.unit.miragesql.miragesql.bean.BeanDescFactory;
import vn.com.unit.miragesql.miragesql.bean.FieldPropertyExtractor;
import vn.com.unit.miragesql.miragesql.dialect.MySQLDialect;
import vn.com.unit.miragesql.miragesql.integration.spring.SpringConnectionProvider;
import vn.com.unit.miragesql.miragesql.naming.RailsLikeNameConverter;
import vn.com.unit.springframework.data.mirage.repository.config.EnableMirageRepositories;

//@EnableMirageRepositories(basePackages = {"com.duypk.socket.repository"}, sqlManagerRef = "sqlManager")
@Configuration
@EnableMirageRepositories(basePackages = {"com.duypk.socket.repository"})
public class MirageConfig {

  @Bean
  SqlManagerImpl sqlManager(DataSourceTransactionManager transactionManager) {
      SqlManagerImpl sqlManager = new SqlManagerImpl();
      sqlManager.setConnectionProvider(connectionProvider(transactionManager));
      sqlManager.setDialect(new MySQLDialect());
      sqlManager.setBeanDescFactory(beanDescFactory());
      sqlManager.setNameConverter(new RailsLikeNameConverter());
      return sqlManager;
  }

  @Bean
  SpringConnectionProvider connectionProvider(DataSourceTransactionManager transactionManager) {
      SpringConnectionProvider connectionProvider = new SpringConnectionProvider();
      connectionProvider.setTransactionManager(transactionManager);
      return connectionProvider;
  }

  @Bean
  BeanDescFactory beanDescFactory() {
      BeanDescFactory beanDescFactory = new BeanDescFactory();
      beanDescFactory.setPropertyExtractor(new FieldPropertyExtractor());
      return beanDescFactory;
  }
}