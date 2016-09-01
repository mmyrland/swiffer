import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "swiffer")
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);

        for (String profile : ctx.getEnvironment().getActiveProfiles()) {
            System.out.println(profile);
        }

    }
}
