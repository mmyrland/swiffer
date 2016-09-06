package mmyrland.controller

import mmyrland.domain.TextFile
import mmyrland.repository.FileRecordRepository
import mmyrland.repository.TextFileRepository
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.PathResource
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TextFileControllerIntSpec extends BaseControllerIntSpec {

//    file/load
//    /
//    /{textfileid}

    @Autowired
    TextFileRepository fileRepository
    @Autowired
    FileRecordRepository recordRepository

    def "/file/load processes valid file and returns results."() {
        given:
        PathResource pr = new PathResource("src/integration/resources/testfiles/swifferTest2.txt");

        MockMultipartFile textFile = new MockMultipartFile(
                "file",
                "src/integration/resources/testfiles/swifferTest2.txt",
                "multipart/form-data",
                pr.inputStream.getBytes());
        when:
        ResultActions result = this.mockMvc.perform(fileUpload('/file/load')
                .file(textFile)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))


        then:
        result
                .andExpect(status().isCreated())
                .andDo(document("textfile-process-valid", preprocessResponse(prettyPrint()),

                responseFields(
                        fieldWithPath('fileName').type(JsonFieldType.STRING).description('The name of the processed file.'),
                        fieldWithPath('textFileId').type(JsonFieldType.STRING).description('Primary key (UUID) of text file in fileRepository.'),
                        fieldWithPath('totalRecordCount').type(JsonFieldType.STRING).description('Count of all processed records (String and Number).'),
                        fieldWithPath('numberRecordCount').type(JsonFieldType.STRING).description('Count of all numerical records.'),
                        fieldWithPath('numberRecordPercentage').type(JsonFieldType.STRING).description('Numerical record percentage of total records.'),
                        fieldWithPath('numberRecordSum').type(JsonFieldType.STRING).description('Sum of all numerical record values in file.'),
                        fieldWithPath('numberRecordMedian').type(JsonFieldType.STRING).description('Median of all numerical record values in file.'),
                        fieldWithPath('numberRecordAvg').type(JsonFieldType.STRING).description('Average of all numerical record values in file.'),
                        fieldWithPath('stringRecordCount').type(JsonFieldType.STRING).description('Count of all string records.'),
                        fieldWithPath('stringRecordPercentage').type(JsonFieldType.STRING).description('String record percentage of total records.'),
                        fieldWithPath('distinctStringRecords').type(JsonFieldType.STRING).description('Distinct set of all string records, with the number of occurrences indicated at far right (: 2).')
                )
        ))
    }

    def "/file/load processes invalid file and responds with BAD REQUEST."() {
        given:
        PathResource pr = new PathResource("src/integration/resources/testfiles/sifferTestImage.jpeg");

        MockMultipartFile textFile = new MockMultipartFile(
                "file",
                "src/integration/resources/testfiles/sifferTestImage.jpeg",
                "multipart/form-data",
                pr.inputStream.getBytes());
        when:
        ResultActions result = this.mockMvc.perform(fileUpload('/file/load')
                .file(textFile)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))


        then:
        result
                .andExpect(status().isBadRequest())
                .andDo(document("textfile-process-not-valid", preprocessResponse(prettyPrint())))
    }

    void "/{textFileId} returns TextFile given valid textFileId."() {
        given:
        def textFile = new TextFile(UUID.randomUUID(),DateTime.now(DateTimeZone.UTC), "findOneTest.txt","findOneTest.txt".bytes)

        when:
        TextFile savedFile = fileRepository.save(textFile);
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get('/file/{textFileId}', savedFile.getTextFileId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))


        then:
        result
                .andExpect(status().isOk())
                .andDo(document("text-file-find-one-valid", preprocessResponse(prettyPrint()),

                pathParameters(
                        parameterWithName("textFileId").description("The primary key (UUID) of the text file in the file database")),

                responseFields(
                        fieldWithPath('textFileId').type(JsonFieldType.STRING).description('The primary key (UUID) of the text file in the file database'),
                        fieldWithPath('textFileName').type(JsonFieldType.STRING).description('Name of file initially processed'),
                        fieldWithPath('fileRecords').type(JsonFieldType.STRING).description('File records processed from file'),
                        fieldWithPath('content').type(JsonFieldType.STRING).description('File records processed from file'),
                        fieldWithPath('dateCreated').type(JsonFieldType.STRING).description('File records processed from file')
                        )

        ))
    }

    void "/{textFileId} responds with NOT FOUND when invalid textFileId is sent."() {
        when:
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get('/file/{textFileId}', UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON_VALUE))


        then:
        result
                .andExpect(status().isNotFound())
                .andDo(document("text-file-find-one-not-valid", preprocessResponse(prettyPrint())

        ))
    }



}
