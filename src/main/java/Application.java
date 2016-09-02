import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "mmyrland")
@EnableJpaAuditing
@EnableJpaRepositories("mmyrland.repository")
@EntityScan("mmyrland.repository")
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);

        for (String profile : ctx.getEnvironment().getActiveProfiles()) {
            System.out.println(profile);
        }

    }
}
