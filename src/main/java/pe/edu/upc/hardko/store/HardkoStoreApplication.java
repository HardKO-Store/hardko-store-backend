package pe.edu.upc.hardko.store;

import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableRelMongo
public class HardkoStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardkoStoreApplication.class, args);
	}

}
