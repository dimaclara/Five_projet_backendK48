package com.marieteck.gestionstock_backend.handlers;

import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer HttpCode;
    private ErrorCodes errorCode;
    private String message;
    List<String> errors = new ArrayList<>();
}
