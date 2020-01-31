package error.controller.error;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {

    private String message;
    private Integer status;
    private String path;

    public ApiError(String message, Integer status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
    }
}
