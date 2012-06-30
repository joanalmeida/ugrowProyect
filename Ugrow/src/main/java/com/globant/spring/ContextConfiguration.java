package com.globant.spring;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import com.mysql.jdbc.Driver;

@Configuration
@ImportResource("/WEB-INF/applicationContext-security.xml")
public class ContextConfiguration {	
		
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ContextConfiguration.class);		
		ctx.scan("com.globant");
		ctx.refresh();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean eMF = new LocalContainerEntityManagerFactoryBean();
		eMF.setDataSource(dataSource());
		eMF.setPackagesToScan("com.globant.entities");
		Properties properties=new Properties();
		properties.put("hibernate.hbm2ddl.auto","create");
		eMF.setJpaProperties(properties);
		HibernatePersistence hp = new HibernatePersistence();
		eMF.setPersistenceProvider(hp);
		return eMF;
	}
	
	@Bean
	public DataSource dataSource() {
		Driver driver = null;		
		try {
			driver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SimpleDriverDataSource datasource = new SimpleDriverDataSource(driver,"jdbc:mysql://localhost/test","","");
		return (DataSource) datasource;
	}

	@Bean
	public VelocityConfigurer velocityConfig() {
		VelocityConfigurer vc = new VelocityConfigurer();
		vc.setResourceLoaderPath("/WEB-INF/velocity/");
		return vc;
	}

	@Bean
	public ViewResolver viewResolver() {
		VelocityViewResolver vr = new VelocityViewResolver();
		vr.setCache(true);
		vr.setPrefix("");
		vr.setSuffix(".html");
		vr.setExposeSpringMacroHelpers(true);
		return vr;
	}
}