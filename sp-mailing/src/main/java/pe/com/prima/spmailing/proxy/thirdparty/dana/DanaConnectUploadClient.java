package pe.com.prima.spmailing.proxy.thirdparty.dana;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MultipartBody;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import java.io.Serializable;

public interface DanaConnectUploadClient {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Root(name = "result")
    class FileUploadProxyDto implements Serializable {

        private static final long serialVersionUID = -1607695576074515548L;

        @Element(name = "fileID")
        private String fileID;
        @Element(name = "fileName")
        private String fileName;
        @Element(name = "idCompany")
        private String idCompany;
    }

    @Multipart
    @POST(value = "dana/conversation/http/rest/file/upload")
    Call<FileUploadProxyDto> upload(@Part MultipartBody.Part file);
}
