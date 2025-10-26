package com.maninasrolahi.service.response.builder.serviceResponseBuilder.test;

import com.maninasrolahi.service.response.builder.serviceResponseBuilder.serviceResponseBuilder.ServiceResponseBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceResponseBuilderTest {

    @Test
    @DisplayName("ok() should return success response with given data")
    void testOk() {
        String data = "Hello";
        ServiceResponseBuilder response = ServiceResponseBuilder.ok(data);

        assertEquals(1, response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    @DisplayName("fail() should return failure response with given message")
    void testFail() {
        ServiceResponseBuilder response = ServiceResponseBuilder.fail("error occurred");

        assertEquals(-1, response.getCode());
        assertEquals("error occurred", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("noValue() should return no-value response")
    void testNoValue() {
        ServiceResponseBuilder response = ServiceResponseBuilder.noValue();

        assertEquals(2, response.getCode());
        assertEquals("no value", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("logicalError() should return logical-exception response")
    void testLogicalError() {
        ServiceResponseBuilder response = ServiceResponseBuilder.logicalError("invalid state");

        assertEquals(3, response.getCode());
        assertEquals("invalid state", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    @DisplayName("createServiceResponse() should return fail for null data")
    void testCreateServiceResponseWithNull() {
        ServiceResponseBuilder response = ServiceResponseBuilder.createServiceResponse(null);

        assertEquals(-1, response.getCode());
        assertEquals("null data", response.getMessage());
    }

    @Test
    @DisplayName("createServiceResponse() should return noValue for empty list")
    void testCreateServiceResponseWithEmptyList() {
        ServiceResponseBuilder response = ServiceResponseBuilder.createServiceResponse(Collections.emptyList());

        assertEquals(2, response.getCode());
        assertEquals("no value", response.getMessage());
    }

    @Test
    @DisplayName("createServiceResponse() should return ok for non-empty list")
    void testCreateServiceResponseWithList() {
        List<String> data = List.of("A");
        ServiceResponseBuilder response = ServiceResponseBuilder.createServiceResponse(data);

        assertEquals(1, response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    @DisplayName("createServiceResponse() should return noValue for empty map")
    void testCreateServiceResponseWithEmptyMap() {
        ServiceResponseBuilder response = ServiceResponseBuilder.createServiceResponse(Collections.emptyMap());

        assertEquals(2, response.getCode());
        assertEquals("no value", response.getMessage());
    }

    @Test
    @DisplayName("createServiceResponse() should return ok for non-empty map")
    void testCreateServiceResponseWithMap() {
        Map<String, Object> data = Map.of("key", "value");
        ServiceResponseBuilder response = ServiceResponseBuilder.createServiceResponse(data);

        assertEquals(1, response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    @DisplayName("createServiceResponse() should return ok for generic object")
    void testCreateServiceResponseWithObject() {
        Object data = new Object();
        ServiceResponseBuilder response = ServiceResponseBuilder.createServiceResponse(data);

        assertEquals(1, response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    @DisplayName("Builder should correctly build custom response")
    void testManualBuilder() {
        ServiceResponseBuilder response = ServiceResponseBuilder.builder()
                .code(999)
                .message("custom")
                .data("data123")
                .build();

        assertEquals(999, response.getCode());
        assertEquals("custom", response.getMessage());
        assertEquals("data123", response.getData());
    }
}
