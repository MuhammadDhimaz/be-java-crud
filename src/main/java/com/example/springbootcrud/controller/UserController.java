package com.example.springbootcrud.controller;

import com.example.springbootcrud.entity.User;
import com.example.springbootcrud.service.NotificationService;
import com.example.springbootcrud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Management", description = "APIs for managing users in the system")
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private UserService userService;
    
    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the user",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "ID of the user to retrieve", required = true, example = "1")
            @PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @Operation(summary = "Create a new user", description = "Create a new user in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "User with this email already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<User> createUser(
            @Parameter(description = "User object to be created", required = true)
            @Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        
        notificationService.sendWelcomeEmail(createdUser.getEmail(), createdUser.getName());
        
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @Operation(summary = "Update an existing user", description = "Update an existing user's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "409", description = "Email already exists for another user"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user to update", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated user object", required = true)
            @Valid @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete a user", description = "Delete a user from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete", required = true, example = "1")
            @PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @Operation(summary = "Search users by name", description = "Search for users by name (case-insensitive partial match)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved matching users",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByName(
            @Parameter(description = "Name to search for", required = true, example = "John")
            @RequestParam String name) {
        List<User> users = userService.searchUsersByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the user",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(
            @Parameter(description = "Email address of the user", required = true, example = "john.doe@example.com")
            @PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
