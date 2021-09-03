package pe.com.prima.spmailing.business;

import org.springframework.web.multipart.MultipartFile;
import pe.com.prima.spmailing.expose.dto.FileUploadDto;
import pe.com.prima.spmailing.expose.dto.MailingDto;

import java.util.List;

public interface MailingService {

    void sendEmail(List<MailingDto.Request> request, String conversationId);

    FileUploadDto.Response fileUpload(MultipartFile file);
}