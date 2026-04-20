package fr.isep.projectweb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "JWT debug endpoints for Supabase integration")
public class AuthController {

    private final String supabaseUrl;
    private final String supabasePublishableKey;

    public AuthController(@Value("${supabase.url}") String supabaseUrl,
                          @Value("${supabase.publishable-key:}") String supabasePublishableKey) {
        this.supabaseUrl = supabaseUrl;
        this.supabasePublishableKey = supabasePublishableKey;
    }

    @GetMapping("/config")
    @Operation(summary = "Get public Supabase configuration for local test pages")
    public Map<String, Object> getPublicConfig() {
        Map<String, Object> config = new LinkedHashMap<>();
        config.put("supabaseUrl", supabaseUrl);
        config.put("supabasePublishableKey", supabasePublishableKey);
        config.put("configured", !supabasePublishableKey.isBlank());
        return config;
    }

    @GetMapping("/debug")
    @Operation(summary = "Inspect the verified JWT claims from the current bearer token")
    public Map<String, Object> debugJwt(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("subject", jwt.getSubject());
        payload.put("email", jwt.getClaimAsString("email"));
        payload.put("role", jwt.getClaimAsString("role"));
        payload.put("issuer", jwt.getIssuer() != null ? jwt.getIssuer().toString() : null);
        payload.put("expiresAt", jwt.getExpiresAt());
        payload.put("claims", jwt.getClaims());
        return payload;
    }
}
