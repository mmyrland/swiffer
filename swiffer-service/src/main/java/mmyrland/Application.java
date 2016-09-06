package mmyrland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "mmyrland")
@EnableJpaAuditing
@EnableJpaRepositories("mmyrland.repository")
@EntityScan("mmyrland.domain")
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);

        for (String profile : ctx.getEnvironment().getActiveProfiles()) {
            System.out.println(profile);
        }

    }

    //add to spring.profiles.active=[] set upon bootRun to nuke and pave database
    @Bean
    @Profile("cleaninitmigrate")
    public FlywayMigrationStrategy cleanInitMigrate() {
        return m -> {
            m.clean();
            m.migrate();
        };
    }
}
