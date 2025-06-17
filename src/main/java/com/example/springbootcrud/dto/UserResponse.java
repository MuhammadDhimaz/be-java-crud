package com.example.springbootcrud.dto;

import com.example.springbootcrud.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Response object containing user information")
public class UserResponse {
    
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;
    
    @Schema(description = "Name of the user", example = "John Doe")
    private String name;
    
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;
    
    @Schema(description = "Phone number of the user", example = "1234567890")
    private String phone;
    
    @Schema(description = "Timestamp when the user was created")
    private LocalDateTime createdAt;
    
    @Schema(description = "Timestamp when the user was last updated")
    private LocalDateTime updatedAt;
    
    // Constructors
    public UserResponse() {}
    
    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
