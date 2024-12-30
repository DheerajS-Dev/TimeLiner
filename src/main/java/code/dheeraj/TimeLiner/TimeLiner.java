package code.dheeraj.TimeLiner;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class TimeLiner {

    public static void main(String[] args) {
        SpringApplication.run(TimeLiner.class, args);
    }

}