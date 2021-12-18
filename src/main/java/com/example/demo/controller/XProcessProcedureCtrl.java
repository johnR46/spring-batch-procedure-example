package com.example.demo.controller;

import com.example.demo.dto.BaseReq;
import com.example.demo.service.XProcessProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/batch")
public class XProcessProcedureCtrl {

    private final XProcessProcedureService xProcessProcedureService;


    @PostMapping("process")
    public ResponseEntity<String> xProcessBatch(@RequestBody @Valid BaseReq req) {
        return null;
    }

    @GetMapping("index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("hello spring-batch introduce");
    }


}
