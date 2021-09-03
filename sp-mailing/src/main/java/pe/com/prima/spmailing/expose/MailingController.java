package pe.com.prima.spmailing.expose;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.com.prima.spmailing.business.MailingService;
import pe.com.prima.spmailing.config.ApiDoc;
import pe.com.prima.spmailing.expose.dto.FileUploadDto;
import pe.com.prima.spmailing.expose.dto.MailingDto;

import java.util.List;

@Tag(
        name = "Mailing Controller",
        description = "."
)
@RestController
@RequestMapping("/mailing/v1")
public class MailingController {
    private final MailingService service;

    public MailingController(MailingService service) {
        this.service = service;
    }

    @Operation(
            summary = "Mailing Service",
            description = "Send an email to one or a list of users.",
            responses = {
                    @ApiResponse(responseCode = ApiDoc.RESPONSE_CODE_OK, description = "Successful operation."),
                    @ApiResponse(responseCode = ApiDoc.RESPONSE_CODE_BAD_REQUEST, description = "Missed or invalid request data."),
                    @ApiResponse(responseCode = ApiDoc.RESPONSE_CODE_NOTFOUND, description = "Don't exists available info for the provided data.")
            }
    )
    @PostMapping("/{conversationId}")
    public ResponseEntity<Void> sendEmail(@RequestBody List<MailingDto.Request> request, @PathVariable("conversationId") String conversationId) {
        this.service.sendEmail(request, conversationId);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Operation(
            summary = "Upload Service",
            description = "Upload file in the document containers Dana."
    )
    @PostMapping(value ="/upload", consumes = {"application/octet-stream", "multipart/form-data"})
    public ResponseEntity<FileUploadDto.Response> fileUpload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(this.service.fileUpload(file));
    }
}