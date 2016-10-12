package atppath.Controller;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import atppath.model.Book;
import atppath.model.Vote;
import atppath.repository.BookRepository;
import ch.qos.logback.classic.Logger;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Configuration
@Controller
public class WelcomeController {

	private static final Log LOGGER = LogFactory.getLog(WelcomeController.class);
	
    @Autowired
    Session session;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {

    	BookRepository br = new BookRepository(session);
        br.createTable();
        br.alterTablebooks("publisher", "text");
        
        br.createTableBooksByTitle();
        
        Book book = new Book(UUIDs.timeBased(), "Effective Java", "Joshua Bloch", "Programming");
        br.insertBookBatch(book);
        
        br.selectAll().forEach(o -> LOGGER.info("Title in books: " + o.getTitle()));
        br.selectAllBookByTitle().forEach(o -> LOGGER.info("Title in booksByTitle: " + o.getTitle()));
        /*
        br.deletebookByTitle("Effective Java");
        br.deleteTable("books");
        br.deleteTable("booksByTitle");
        //model.put("results", results);
		*/
        return "welcome";
    }

}
