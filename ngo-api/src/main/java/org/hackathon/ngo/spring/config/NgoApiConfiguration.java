package org.hackathon.ngo.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan("org.hackathon")
@EnableJpaRepositories(basePackages={"org.hackathon.data"})
@PropertySource(value={"classpath:ngo.properties"})
@EnableAspectJAutoProxy
public class NgoApiConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	@Autowired
	public DataSource getDataSource(DatabasePopulator databasePopulator) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("ngo.driver.classname"));
		dataSource.setUrl(environment.getProperty("ngo.url"));
		dataSource.setUsername(environment.getProperty("ngo.jdbc.username"));
		dataSource.setPassword(environment.getProperty("ngo.jdbc.password"));
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		return  dataSource;
	}
	
	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(Boolean.TRUE);
		jpaVendorAdapter.setShowSql(Boolean.TRUE);
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter); 
		entityManagerFactoryBean.setPackagesToScan("org.hackathon");
		Properties jpaProperties = new Properties();
		//jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}
	
	@Bean
	@Autowired
	public DatabasePopulator getPopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(true);
		databasePopulator.addScript(new ClassPathResource("initSql.sql"));
		return databasePopulator;
	}
}
