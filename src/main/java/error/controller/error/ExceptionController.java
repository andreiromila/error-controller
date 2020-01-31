package error.controller.error;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
public class ExceptionController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public ExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * If we have an Authentication error (401) this will not get called
     */
    @RequestMapping("/error")
    public ApiError handleError(WebRequest webRequest) {

        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, true);

        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        Integer status = (Integer) attributes.get("status");

        return new ApiError(message, status, path);
    }

}
