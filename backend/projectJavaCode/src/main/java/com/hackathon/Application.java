package com.hackathon;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class,DataSourceAutoConfiguration.class})
public class Application extends org.springframework.boot.context.web.SpringBootServletInitializer {
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}