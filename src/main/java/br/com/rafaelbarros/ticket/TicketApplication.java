package br.com.rafaelbarros.ticket;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.rafaelbarros.ticket.*" })
@Configuration
@EnableJpaRepositories("br.com.rafaelbarros.ticket.*")
@EntityScan("br.com.rafaelbarros.ticket.*")
public class TicketApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
			final JpaProperties jpaProperties) {
		final var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("br.com.rafaelbarros.ticket.**");
		entityManagerFactoryBean.setJpaProperties(hibernateProperties(jpaProperties));
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return entityManagerFactoryBean;
	}

	private Properties hibernateProperties(final JpaProperties jpaProperties) {
		final var properties = new Properties();
		properties.putAll(jpaProperties.getProperties());
		return properties;
	}

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		String databaseDriver = this.env.getProperty("spring.datasource.driver-class-name");
		String databaseUrl = this.env.getProperty("spring.datasource.url");
		String username = this.env.getProperty("spring.datasource.username");
		String password = this.env.getProperty("spring.datasource.password");
		String schema = this.env.getProperty("pring.jpa.properties.hibernate.schemas");

		dataSource.setDriverClassName(databaseDriver);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setSchema(schema);

		return dataSource;
	}

	@Bean(name = "openApiCustomiser")
	public OpenApiCustomizer openApiCustomiser() {
		return openApi -> extracted(openApi);
	}

	private void extracted(OpenAPI openApi) {
		openApi.getInfo().setTitle("Ticket API");
		openApi.getInfo().setVersion("1.0");
		openApi.getComponents().getSchemas().values().forEach(schema -> {
		});
		openApi.setServers(
				// put localhos on my machine
				List.of(new Server().url("http://localhost:8082").description("Local server"),
						new Server().url("https://api.rafaelbarros.com").description("Production server")));
	}
}
