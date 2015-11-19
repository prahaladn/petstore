package petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 */
@SpringBootApplication
public class BaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(new Object[] { BaseApplication.class }, args);
	}
}
