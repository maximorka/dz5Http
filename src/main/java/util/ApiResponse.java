package util;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    private int code;
    private String type;
    private String message;
}
