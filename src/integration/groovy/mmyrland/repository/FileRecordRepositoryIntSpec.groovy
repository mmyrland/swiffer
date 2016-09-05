package mmyrland.repository

import mmyrland.Application
import mmyrland.IntegrationTestAppConfig
import mmyrland.domain.NumberRecord
import mmyrland.domain.StringRecord
import mmyrland.domain.TextFile
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(value = 'integrationtest')
@SpringApplicationConfiguration(classes = [Application,IntegrationTestAppConfig])
class FileRecordRepositoryIntSpec extends Specification {

    @Autowired
    TextFileRepository textFileRepository
    @Autowired
    FileRecordRepository repository

    def "save persists valid number record, and findOne retrieves correct object type."(){
        given:
        def fileId = UUID.randomUUID()
        def fileText = "Line 1" +
                "Line 2"
        def textFile = new TextFile(textFileId: fileId, textFileName: "test1file.txt", content: fileText.bytes,dateCreated: DateTime.now())
        def object = new NumberRecord(textFileId: fileId, numberVal: 42.2d)

        when:
        textFileRepository.save(textFile)

        repository.save(object)
        def getResponse = repository.findOne(object.getFileRecordId())

        then:
        getResponse != null
        getResponse instanceof NumberRecord
    }

    def "save persists valid string record, and findOne retrieves correct object type."(){
        given:
        def fileId = UUID.randomUUID()
        def fileText = "Line 1" +
                "Line 2"
        def textFile = new TextFile(textFileId: fileId, textFileName: "test1file.txt", content: fileText.bytes,dateCreated: DateTime.now())
        def object = new StringRecord(textFileId: fileId, recordText: "this is a string")

        when:
        textFileRepository.save(textFile)

        repository.save(object)
        def getResponse = repository.findOne(object.getFileRecordId())

        then:
        getResponse != null
        getResponse instanceof StringRecord
    }
}
