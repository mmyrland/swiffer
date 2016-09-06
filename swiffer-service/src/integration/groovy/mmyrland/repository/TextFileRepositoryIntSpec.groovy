package mmyrland.repository

import mmyrland.Application
import mmyrland.IntegrationTestAppConfig
import mmyrland.domain.TextFile
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(value = 'integrationtest')
@SpringApplicationConfiguration(classes = [Application,IntegrationTestAppConfig])
class TextFileRepositoryIntSpec extends Specification {


    @Autowired
    TextFileRepository repository

    def "save persists valid object, and findOne retrieves valid object given valid textFileId."(){
        given:
        def fileId = UUID.randomUUID()
        def fileText = "Line 1" +
                "Line 2"
        def textFile = new TextFile(textFileId: fileId, textFileName: "test1file.txt", content: fileText.bytes,dateCreated: DateTime.now())

        when:
        repository.save(textFile)
        def getResponse = repository.findOne(textFile.getTextFileId())

        then:
        getResponse != null
    }



}
