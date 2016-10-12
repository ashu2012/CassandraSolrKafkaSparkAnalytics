package atppath.configs;

import com.datastax.driver.core.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })

public class AppBeans {
	private static final Log LOGGER = LogFactory.getLog(AppBeans.class);
	
	@Autowired
    Environment environment;
	
    @Bean
    public Session session() {
        return sessionManager().getSession();
    }

    @Bean
    public CassandraSessionManager sessionManager() {
    	LOGGER.info(environment.getProperty("cassandra.keyspace"));
        return new CassandraSessionManager(environment.getProperty("cassandra.contactPoint"),environment.getProperty("cassandra.keyspace"));
    }
}
