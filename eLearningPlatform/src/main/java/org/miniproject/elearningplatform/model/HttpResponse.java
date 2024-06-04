package org.miniproject.elearningplatform.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

@Builder
@Data
public class HttpResponse {
    private Date timeStamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private Map<?,?> data;
}
