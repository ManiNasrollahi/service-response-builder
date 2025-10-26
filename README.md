# 🌱 Service Response Builder

A lightweight utility for building **consistent API responses** in Spring Boot.  
with **ServiceResponseBuilder** class, Simplify your controllers with a clean and standard response format.

---

## 🚀 Features
- Easy builder pattern
- Standard codes: success, fail, no value, logical error
- Simple integration & full test coverage

---

## ⚙️ Example Usage

### 💡 Controller
```java
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
```
