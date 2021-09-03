package pe.com.prima.spmailing.expose;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pe.com.prima.spmailing.business.impl.MailingServiceImpl;
import pe.com.prima.spmailing.expose.dto.FileUploadDto;
import pe.com.prima.spmailing.expose.dto.MailingDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MailingController.class)
public class MailingControllerTest {

    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private MailingServiceImpl mailingService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @SneakyThrows
    @Test
    void sendMailing() {
        Map<String, String> aditionalValues = new HashMap<>();
        aditionalValues.put("name", "name");
        aditionalValues.put("gender", "gender");
        List<MailingDto.Request> request = new ArrayList<MailingDto.Request>();
        request.add(
                MailingDto.Request.builder()
                        .email("email")
                        .additionalValues(aditionalValues)
                        .build());

        doNothing().when(mailingService).sendEmail(request, "235689");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/mailing/v1/{conversationId}", "235689")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(request))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @SneakyThrows
    @Test
    void fileUpload() {
        MockMultipartFile multiPartFile = new MockMultipartFile("file", "test.pdf", org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE, "test any argument".getBytes());
        FileUploadDto.Response responseFileId = new FileUploadDto.Response();
        responseFileId.setId("s3://WS/2021/6/01ccdd5d9a707c6e8fb79a1fc452732e");
        given(mailingService.fileUpload(multiPartFile)).willReturn(responseFileId);

        mockMvc.perform(multipart("/mailing/v1/upload")
                .file(multiPartFile)
                .contentType("multipart/form-data"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn();
    }
}

