# Qabas Reality Status

This revision enforces a strict rule: the app must not claim external success without evidence from the external provider.

## Fixed in this revision
- Gemini service references use the application context correctly.
- Gemini prompts interpolate variables instead of sending literal `$apiKey` / `$systemPrompt` text.
- Social access tokens use Android Keystore-backed `SecureSecretStore`.
- Social analytics no longer fabricate follower/like/view growth.
- Social publishing no longer invents post URLs or returns `isSuccess=true` without a verified upload.
- Failed video overlays, color grading, watermarking, and ambient-audio mixing no longer copy the source and report success.
- Style scores are deterministic instead of random.
- Official-channel labels no longer claim third-party verification.

## Intentionally not faked
Direct publishing to YouTube/TikTok/Instagram/X/Facebook requires provider-specific OAuth scopes, app registration, backend/client credentials, and verified upload flows. The current app now reports this requirement instead of simulating it.

## Verification limitation
The supplied repository cannot be compiled in this offline environment because the Gradle wrapper distribution is not cached and `services.gradle.org` is unreachable. Static checks were performed after the edits.
