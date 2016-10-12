package atppath.configs;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;

import atppath.repository.KeyspaceRepository;

//import atppath.configs.CassandraConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;


public class CassandraSessionManager {

	private static final Log LOGGER = LogFactory.getLog(CassandraSessionManager.class);
    private Session session;
    private Cluster cluster;

    
    
    
    
    private String contactPoint ;

   
    private String keyspace ;

    public CassandraSessionManager(String contactPoint, String keyspace ) {
    	this.contactPoint = contactPoint;
    	this.keyspace =keyspace	;
    	LOGGER.info(contactPoint);
    }

    public Session getSession() {
        return session;
    }

    @PostConstruct
    public void initIt() {
    	LOGGER.info(contactPoint);
        cluster = Cluster.builder().withProtocolVersion(ProtocolVersion.V4).addContactPoint(contactPoint).build();
        session = cluster.connect();
        KeyspaceRepository keySpace = new KeyspaceRepository(session);
        keySpace.createKeyspace(keyspace,"SimpleStrategy",1);
        session = cluster.connect(keyspace);
        ProtocolVersion myCurrentVersion = cluster.getConfiguration()
        	    .getProtocolOptions()
        	    .getProtocolVersion();
        LOGGER.info(myCurrentVersion.toString());
        
    }

    @PreDestroy
    public void destroy() {
        if (session != null) {
            session.close();
        }
        if (cluster != null) {
            cluster.close();
        }
    }
}
