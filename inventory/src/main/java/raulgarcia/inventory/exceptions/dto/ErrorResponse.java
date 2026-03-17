package raulgarcia.inventory.exceptions.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    @Builder.Default
    private List<String> details = new ArrayList<>();
    private String path;
}