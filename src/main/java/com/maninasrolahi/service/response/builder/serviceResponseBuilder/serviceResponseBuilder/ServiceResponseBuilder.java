package com.maninasrolahi.service.response.builder.serviceResponseBuilder.serviceResponseBuilder;

import java.util.List;
import java.util.Map;

public class ServiceResponseBuilder {

    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = -1;
    public static final int CODE_NO_VALUE = 2;
    public static final int CODE_LOGICAL_EXCEPTION = 3;
    public static final String MSG_SUCCESS = "success";

    private int code;
    private String message;
    private Object data;

    public static ServiceResponseBuilder ok(Object data) {
        return ServiceResponseBuilder.builder().code(CODE_SUCCESS).data(data).message(MSG_SUCCESS).build();
    }

    public static ServiceResponseBuilder fail(String message) {
        return ServiceResponseBuilder.builder().code(CODE_FAIL).message(message).build();
    }

    public static ServiceResponseBuilder noValue() {
        return ServiceResponseBuilder.builder().code(CODE_NO_VALUE).message("no value").build();
    }

    public static ServiceResponseBuilder logicalError(String message) {
        return ServiceResponseBuilder.builder().code(CODE_LOGICAL_EXCEPTION).message(message).build();
    }

    public static ServiceResponseBuilder createServiceResponse(Object data) {
        return switch (data) {
            case null -> ServiceResponseBuilder.fail("null data");
            case List<?> list -> list.isEmpty() ? ServiceResponseBuilder.noValue() : ServiceResponseBuilder.ok(list);
            case Map<?,?> map -> map.isEmpty() ? ServiceResponseBuilder.noValue() : ServiceResponseBuilder.ok(map);
            default -> ServiceResponseBuilder.ok(data);
        };
    }

    private ServiceResponseBuilder(){}

    private ServiceResponseBuilder(Builder builder){
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static class Builder{

        private int code;
        private String message;
        private Object data;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ServiceResponseBuilder build(){
            return new ServiceResponseBuilder(this);
        }
    }
}
