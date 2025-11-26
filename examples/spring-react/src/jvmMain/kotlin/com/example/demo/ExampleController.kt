package com.example.demo

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/create")
    public fun create(@Valid @RequestBody req: CreateUserRequest): UserResponse {
        return UserResponse("123", req.username)
    }
}

public data class CreateUserRequest(
    @field:NotNull
    @field:Size(min = 3, max = 20)
    val username: String,

    @field:Email
    val email: String,

    @field:Min(18)
    val age: Int? = null
)

public data class UserResponse(
    val id: String,
    val username: String
)
