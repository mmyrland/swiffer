package mmyrland.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    //Adds correct serialization for joda time and other components.
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new Jdk8Module()); //retained for Optional handling
        om.registerModule(new JodaModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return om;
    }
}
