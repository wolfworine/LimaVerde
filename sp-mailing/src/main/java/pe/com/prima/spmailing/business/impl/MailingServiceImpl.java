package pe.com.prima.spmailing.business.impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.com.prima.apolo.tracing.exception.RetrofitCallException;
import pe.com.prima.spmailing.business.MailingService;
import pe.com.prima.spmailing.expose.dto.FileUploadDto;
import pe.com.prima.spmailing.expose.dto.MailingDto;
import pe.com.prima.spmailing.proxy.thirdparty.dana.DanaConnectSendClient;
import pe.com.prima.spmailing.proxy.thirdparty.dana.DanaConnectUploadClient;
import pe.com.prima.spmailing.util.Constants;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class MailingServiceImpl implements MailingService {
    private final DanaConnectSendClient proxy;
    private final DanaConnectUploadClient danaConnectUploadClientProxy;


    public MailingServiceImpl(DanaConnectSendClient proxy, DanaConnectUploadClient danaConnectUploadClientProxy) {
        this.proxy = proxy;
        this.danaConnectUploadClientProxy = danaConnectUploadClientProxy;
    }

    @Override
    public void sendEmail(List<MailingDto.Request> request, String conversationId) {
        List<Map<String, String>> listMap = request.stream()
                .map(mailingRequestDto -> {
                    Map<String, String> additionalValues = mailingRequestDto.getAdditionalValues();
                    Map<String, String> map = !isNull(additionalValues) ? additionalValues : new HashMap<>();
                    map.put(Constants.ParamSendMail.EMAIL, mailingRequestDto.getEmail());
                    return map;
                })
                .collect(Collectors.toList());
        Call<List<DanaConnectSendClient.SendMailResponse>> retrofitCall = this.proxy.sendMail(listMap, conversationId);
        try {
            Response<List<DanaConnectSendClient.SendMailResponse>> response = retrofitCall.execute();
            if (!response.isSuccessful())
                throw new RetrofitCallException("An error occurred when retrieving data.");
        } catch (IOException ex) {
            throw new RetrofitCallException(ex.getMessage(), ex);
        }
    }

    @Override
    public FileUploadDto.Response fileUpload(MultipartFile file) {

        DanaConnectUploadClient.FileUploadProxyDto fileUploadProxyDto;

        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file.getBytes());
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", getNameFile(file), requestBody);
            Call<DanaConnectUploadClient.FileUploadProxyDto> retrofitCall = this.danaConnectUploadClientProxy.upload(filePart);

            Response<DanaConnectUploadClient.FileUploadProxyDto> response = retrofitCall.execute();

            if (response.isSuccessful()) {
                fileUploadProxyDto = response.body();
            } else {
                throw new RetrofitCallException("An error occurred when retrieving data.");
            }
        } catch (IOException ex) {
            throw new RetrofitCallException(ex.getMessage(), ex);
        }
        return FileUploadDto.Response.builder().id(fileUploadProxyDto.getFileID()).build();
    }

    private String getNameFile(MultipartFile file) {
        String[] fileFrags = file.getOriginalFilename().split("\\.");
        String extension = fileFrags[fileFrags.length - 1];
        return UUID.randomUUID().toString() + "." + extension;
    }
}
