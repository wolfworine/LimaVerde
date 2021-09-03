package pe.com.prima.spmailing.expose.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

public class MailingDto implements Serializable {

    private static final long serialVersionUID = -1846217577244110366L;


    @ApiModel(value = "MailingRequestDto")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String email;
        private Map<String, String> additionalValues;
    }
}
