# String Reply Service

## Overview

The String Reply Service is a Java Spring Boot application that processes input strings based on rules and returns the result in a JSON object. The service includes features such as rule-based string manipulation, error handling, logging, and comprehensive testing.

## Features

### V2 Endpoint

A new version of the service (V2) has been introduced with additional features. The V2 endpoint allows users to apply rules to input strings. The supported rules are:

- **Rule 1:** Reverse the string.
- **Rule 2:** Encode the string using the MD5 hash algorithm and display as hex.

The numbers in the rule are applied in sequence, and the output of one rule serves as the input for the next rule. Rules can be repeated, providing flexibility in string manipulation.

#### Examples:

- `GET /v2/reply/11-kbzw9ru` returns `{"statusCode": 200,"message": "kbzw9ru"}` (reversing the string twice has no effect).
- `GET /v2/reply/12-kbzw9ru` returns `{"statusCode": 200, "message": "5a8973b3b1fafaeaadf10e195c6e1dd4"}` (reversing and then MD5 encoding).
- `GET /v2/reply/22-kbzw9ru` returns `{"statusCode": 200, "message": "e8501e64cf0a9fa45e3c25aa9e77ffd5"}` (MD5 encoding twice).
- `GET /v2/reply/33-kbzw9ru` returns `{"statusCode": 400,"message": "Invalid input"}` (rule Code is invalid).
  
### Error Handling

The service includes improved error handling:

- Invalid input to the V2 endpoint results in a `400 Bad Request` status code with an informative error message in the response body.
- Custom `InvalidInputException` is thrown for invalid input scenarios, providing a clear and consistent way to handle these exceptions.

### Logging

The service incorporates logging using SLF4J. Important events, errors, or information during the execution of the application are logged, making it easier to troubleshoot and monitor the service.


## Testing

The project includes a comprehensive testing suite covering both unit tests and integration tests.


### Unit Tests

Unit tests ensure the correctness of individual components. Run unit tests and integration tests both using the following command:

```bash
./gradlew test




