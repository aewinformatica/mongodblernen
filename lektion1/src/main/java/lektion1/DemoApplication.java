package lektion1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("MongoDB lernen - Lektion 1 gestartet mit folgenden Parameter : {}", args.getOptionNames());

        // Alle Datenbanken und aktuell verwendete Datenbank loggen
        mongoClient.listDatabaseNames().forEach(dbName -> LOG.info("Database {}", dbName));
        LOG.info("Current Database {}", mongoTemplate.getDb().getName());

        // Ein Document einfügen
        MongoCollection<Document> personenCollection = mongoTemplate.getDb().getCollection("personen");
        Document johndoe = new Document();
        johndoe.append("name", "John Doe");
        personenCollection.insertOne(johndoe);
    }

}
