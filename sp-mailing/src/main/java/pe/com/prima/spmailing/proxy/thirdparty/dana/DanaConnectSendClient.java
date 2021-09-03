package pe.com.prima.spmailing.proxy.thirdparty.dana;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface DanaConnectSendClient {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class SendMailResponse implements Serializable {

        private static final long serialVersionUID = -1607695576074515548L;

        private WsResult wsResult;
        private WsError wsError;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class WsResult implements Serializable {

        private static final long serialVersionUID = -1607695576074515548L;

        private Integer idResult;
        private String resultDescription;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class WsError implements Serializable {

        private static final long serialVersionUID = 4238837776542557249L;

        private Integer idError;
        private String errorDescription;

    }

    @POST("/api/1.0/rest/conversation/{conversationId}/start/list/data")
    Call<List<SendMailResponse>> sendMail(@Body List<Map<String, String>> listMap, @Path("conversationId") String conversationId);

}
