package com.sundays.merryaround.board.store;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(value="com.sundays.merryaround.board.store", sqlSessionFactoryRef="dbSqlSessionFactoryBean")
@EnableTransactionManagement
public class DbConfig {

   @Bean(name = "dbHikariConfig")
   @ConfigurationProperties(prefix = "spring.datasource.hikari")
   public HikariConfig dbHikariConfig() {
      return new HikariConfig();
   }

   @Bean(name = "dbDataSource")
   public DataSource dataSource(@Qualifier("dbHikariConfig") HikariConfig configuration) throws Exception {
      return new HikariDataSource(configuration);
   }

   @Bean(name = "dbTransactionManager")
   public DataSourceTransactionManager dbDataSourceTransactionManager(@Qualifier("dbDataSource") DataSource dataSource) throws Exception {
      return new DataSourceTransactionManager(dataSource);
   }
   
   @Bean(name="dbSqlSessionFactoryBean")
   public SqlSessionFactoryBean dbSqlSessionFactoryBean(@Qualifier("dbDataSource") DataSource dataSource) throws Exception {
      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
      sessionFactory.setDataSource(dataSource);
      Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/board/*_mapper.xml");
      sessionFactory.setMapperLocations(res);
      return sessionFactory;
   }
   
   @Bean(name="dbSqlSessionTemplate")
   public SqlSessionTemplate dbSqlSessionTemplate(@Qualifier("dbSqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
      SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
      return sqlSessionTemplate;
   }
}