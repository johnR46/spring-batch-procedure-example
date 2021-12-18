package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BaseReq {
    
    @NotBlank
    @Schema(description = "batchDate", type = "string", example = "20211218")
    private String batchDate;
}
