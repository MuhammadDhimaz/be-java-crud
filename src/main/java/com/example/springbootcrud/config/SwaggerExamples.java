package com.example.springbootcrud.config;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.stereotype.Component;

@Component
public class SwaggerExamples {
    
    public static final String USER_CREATE_EXAMPLE = """
        {
          "name": "John Doe",
          "email": "john.doe@example.com",
          "phone": "1234567890"
        }
        """;
    
    public static final String USER_UPDATE_EXAMPLE = """
        {
          "name": "John Smith",
          "email": "john.smith@example.com",
          "phone": "0987654321"
        }
        """;
    
    public static final String USER_RESPONSE_EXAMPLE = """
        {
          "id": 1,
          "name": "John Doe",
          "email": "john.doe@example.com",
          "phone": "1234567890",
          "createdAt": "2024-01-15T10:30:00",
          "updatedAt": "2024-01-15T10:30:00"
        }
        """;
    
    public static final String USERS_LIST_EXAMPLE = """
        [
          {
            "id": 1,
            "name": "John Doe",
            "email": "john.doe@example.com",
            "phone": "1234567890",
            "createdAt": "2024-01-15T10:30:00",
            "updatedAt": "2024-01-15T10:30:00"
          },
          {
            "id": 2,
            "name": "Jane Smith",
            "email": "jane.smith@example.com",
            "phone": "0987654321",
            "createdAt": "2024-01-15T11:00:00",
            "updatedAt": "2024-01-15T11:00:00"
          }
        ]
        """;
    
    public static final String ERROR_RESPONSE_EXAMPLE = """
        {
          "status": 404,
          "message": "User not found with id: 999",
          "timestamp": "2024-01-15T12:00:00"
        }
        """;
    
    public static final String VALIDATION_ERROR_EXAMPLE = """
        {
          "name": "Name is required",
          "email": "Email should be valid"
        }
        """;
}
