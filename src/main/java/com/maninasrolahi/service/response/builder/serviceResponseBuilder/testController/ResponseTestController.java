package com.maninasrolahi.service.response.builder.serviceResponseBuilder.testController;

import com.maninasrolahi.service.response.builder.serviceResponseBuilder.serviceResponseBuilder.ServiceResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class ResponseTestController {

    @GetMapping("/ok")
    public ResponseEntity<ServiceResponseBuilder> getOkResponse() {
        var response = ServiceResponseBuilder.ok(List.of("A", "B", "C"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/fail")
    public ResponseEntity<ServiceResponseBuilder> getFailResponse(@RequestParam String msg) {
        var response = ServiceResponseBuilder.fail(msg);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @GetMapping("/logical")
    public ResponseEntity<ServiceResponseBuilder> getLogicalError() {
        var response = ServiceResponseBuilder.logicalError("Invalid logic");
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

    @GetMapping("/map")
    public ResponseEntity<ServiceResponseBuilder> getMapResponse() {
        var response = ServiceResponseBuilder.createServiceResponse(Map.of("name", "Mani"));
        return ResponseEntity
                .ok(response);
    }
}
