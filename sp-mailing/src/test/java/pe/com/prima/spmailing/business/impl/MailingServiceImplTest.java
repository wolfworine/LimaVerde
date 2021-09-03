package pe.com.prima.spmailing.business.impl;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import pe.com.prima.apolo.tracing.exception.RetrofitCallException;
import pe.com.prima.spmailing.business.MailingService;
import pe.com.prima.spmailing.expose.dto.MailingDto;
import pe.com.prima.spmailing.proxy.thirdparty.dana.DanaConnectSendClient;
import pe.com.prima.spmailing.proxy.thirdparty.dana.DanaConnectUploadClient;
import pe.com.prima.spmailing.util.Constants;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MailingServiceImplTest {

    private static final String REQUEST_DATA_ERROR = "An error occurred when retrieving data.";
    private static final String conversationId = "237697";
    private static final String name = "Giselle Yanet";
    private static final String email = "gprieto@soaint.com";
    private static final String gender = "FEMALE";

    @MockBean
    DanaConnectSendClient mockedApiInterface;
    @MockBean
    DanaConnectUploadClient danaConnectUploadClientProxy;
    @Autowired
    MailingService service;

    @Test
    void sendEmailSuccess() throws IOException {

        Map<String, String> aditionalValues = new HashMap<>();
        aditionalValues.put("name", name);
        aditionalValues.put("gender", gender);

        List<MailingDto.Request> request = new ArrayList<MailingDto.Request>();
        request.add(
                MailingDto.Request.builder()
                        .email(email)
                        .additionalValues(aditionalValues)
                        .build());

        DanaConnectSendClient.SendMailResponse responseBody = DanaConnectSendClient.SendMailResponse.builder()
                .wsResult(
                        DanaConnectSendClient.WsResult
                                .builder()
                                .idResult(0)
                                .resultDescription("OK")
                                .build()
                )
                .build();

        Response<List<DanaConnectSendClient.SendMailResponse>> response = Response.success(Collections.singletonList(responseBody));


        retrofit2.Call<List<DanaConnectSendClient.SendMailResponse>> callable = mock(retrofit2.Call.class);
        when(mockedApiInterface.sendMail(any(), any())).thenReturn(callable);
        when(callable.execute()).thenReturn(response);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List<Map<String, String>>> listMapArgumentCaptor = ArgumentCaptor.forClass(List.class);

        service.sendEmail(request, conversationId);

        verify(mockedApiInterface, times(1)).sendMail(any(), any());
        verify(mockedApiInterface).sendMail(listMapArgumentCaptor.capture(), stringArgumentCaptor.capture());

        assertEquals(conversationId, stringArgumentCaptor.getValue());

        List<Map<String, String>> listMapCaptured = listMapArgumentCaptor.getValue();

        assertEquals(3, listMapCaptured.get(0).size());
        listMapCaptured.get(0).forEach((key, value) -> {
            if (key.equals(Constants.ParamSendMail.EMAIL)) assertEquals(request.get(0).getEmail(), value);
            if (key.equals(Constants.ParamSendMail.GENDER)) assertEquals(gender, value);
            if (key.equals(Constants.ParamSendMail.NAME)) assertEquals(name, value);
        });
    }

    @Test
    void sendEmailFailure() throws IOException {

        Map<String, String> aditionalValues = new HashMap<>();
        aditionalValues.put("name", name);

        List<MailingDto.Request> request = new ArrayList<MailingDto.Request>();
        request.add(
                MailingDto.Request.builder()
                        .email(email)
                        .additionalValues(aditionalValues)
                        .build());

        retrofit2.Call<List<DanaConnectSendClient.SendMailResponse>> callable = mock(retrofit2.Call.class);
        when(mockedApiInterface.sendMail(any(), any())).thenReturn(callable);
        when(callable.execute()).thenThrow(new RetrofitCallException(REQUEST_DATA_ERROR));

        Assertions.assertThrows(RetrofitCallException.class, () -> {
            service.sendEmail(request, conversationId);
        });

        verify(mockedApiInterface, times(1)).sendMail(any(), any());
    }

    @SneakyThrows
    @Test
    void fileUploadException() throws IOException {

        MockMultipartFile multiPartFile = new MockMultipartFile("file", "test.pdf", org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE, "test any argument".getBytes());
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "test any argument".getBytes());
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", "test.pdf", requestBody);

        DanaConnectUploadClient.FileUploadProxyDto responseProxy = new DanaConnectUploadClient.FileUploadProxyDto();
        responseProxy.setFileID("s3://WS/2021/6/cc4cc8a89aff5d325a752d715430ea65");
        responseProxy.setIdCompany("prima");
        responseProxy.setFileName("test.pdf");

        Response<DanaConnectUploadClient.FileUploadProxyDto> responseOk = Response.success(responseProxy);

        Call<DanaConnectUploadClient.FileUploadProxyDto> callable = mock(Call.class);
        when(this.danaConnectUploadClientProxy.upload(file)).thenThrow(NullPointerException.class);
        when(callable.execute()).thenReturn(responseOk);

        assertThrows(NullPointerException.class, () -> {
            this.service.fileUpload(multiPartFile);
        });
    }
}