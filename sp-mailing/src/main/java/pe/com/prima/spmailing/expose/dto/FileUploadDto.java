package pe.com.prima.spmailing.expose.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class FileUploadDto implements Serializable {

    @ApiModel(value = "FileUploadResponseDto")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String id;
    }
}