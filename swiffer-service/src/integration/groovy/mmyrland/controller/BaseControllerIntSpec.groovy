package mmyrland.controller

import mmyrland.Application
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.restdocs.RestDocumentation
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration


@ContextConfiguration(loader = SpringApplicationContextLoader, classes = [Application])
@ActiveProfiles(value = 'integrationtest')
@WebIntegrationTest(randomPort = true)
class BaseControllerIntSpec extends Specification {
    @Rule
    RestDocumentation restDocumentation = new RestDocumentation('build/generated-snippets')

    @Value('${local.server.port}')
    int port

    private String url = "http://localhost"

    private String baseUrl

    protected MockMvc mockMvc

    @Autowired
    private WebApplicationContext context

    def setup(){
        baseUrl = url + port

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build()

    }
}
