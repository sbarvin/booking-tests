package booking.model;

import lombok.Data;

import java.util.List;

@Data
public class ErrorMessage {
    private Integer errorCode;
    private String error;
    private String errorMessage;
    private List<String> fieldErrors;
}