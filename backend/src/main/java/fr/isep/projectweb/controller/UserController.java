package fr.isep.projectweb.controller;

import fr.isep.projectweb.model.dto.request.UpdateMyProfileRequest;
import fr.isep.projectweb.model.dto.response.PublicUserResponse;
import fr.isep.projectweb.model.dto.response.UserProfileResponse;
import fr.isep.projectweb.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Business user profile endpoints")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @Operation(summary = "Get the current authenticated user's business profile")
    public UserProfileResponse getMyProfile(@AuthenticationPrincipal Jwt jwt) {
        return userService.getMyProfile(jwt);
    }

    @PutMapping("/me")
    @Operation(summary = "Update the current authenticated user's business profile")
    public UserProfileResponse updateMyProfile(@AuthenticationPrincipal Jwt jwt,
                                               @RequestBody UpdateMyProfileRequest request) {
        return userService.updateMyProfile(jwt, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a public user profile by id")
    public PublicUserResponse getUserById(@PathVariable UUID id) {
        return userService.getPublicUserById(id);
    }
}
