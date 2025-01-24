package com.example.api.util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    private static final String HEADER = Base64.getUrlEncoder().encodeToString("{\"alg\":\"RS256\",\"typ\":\"JWT\"}".getBytes());
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDjFbLmgJDkHLqGfYzeYbpZIpsoGmquerBcKTMS60k80M6HisTdHEMOR9QFEvFrpie2gT6WGdCSYTeaKIZrtzyKZvOKkExrWz3IRyg5Nu+8HmVJKbO2iLAYvxIIkz1VypG0PnJg8UV1Mm79KgbIYIiCFfbkasYbuc2g2eb3BskfSI2QDzUVEWkoZlaGbBfytb1ZUP+MuPYWbuiHTR3ZPku1uBauewU1i47JRVloq6IvJnhqT/ZcEmI9VY6WCG9vmaK1FqOfr14CXkm2IQ/J5n967Yk2t8uh7vMs7hw6TJW4XCzLuMN7uVchAIA2hd0+NAgQyiQrD/fV3BAMMjw1TgD9AgMBAAECggEAM/uKX7okENfjufhBGEIKWK+zkV9Utvq2Zu52dtj+A6ov8ParAIug7QKzzfKLac9YDW7yHwWrSWrwQb2PodZJCUyuaPSFzdiN4MwrMMe64tcZbAFcFoedOhj5HZXpBzvPFVHrOHZapkykfBcUBOgWL6MsCJdJoNysbesbtEYtIZf5ivYIstiZRIgJXxcJTNSdc8yfJ77GuZv+qIJJIeRCaEvqQ+Bsb/j8PtxocPJsXN7Q125xJC86A0zVTO2mOgfL3STSVaYMqYFfIniLh5U9BqPfSkQ+NCqGWiDMy0jcgFe4EoYiJZe0fd47X/OuVp5rjhCPJGLu4ZlrHEoxvtUYqQKBgQD1E7TGIG1Ie6fP6ssTs1z82TuRyd5Ns8hxlR9iTxKT8byju8OaG249yo2t8aLm0PKBFVdBrkx/x2pEM3HTo4r8DwSG+tPhRwOEu24ncayyVBYGHfj9AlJwyAzH3a0nRKuYnhAolJrkncTavGiqwGZsREDOr+/AS5qCnAFbYcfyFQKBgQDtNLQ7BeepQghth7IF2XeFsdHutVYVPCd14AtyyKyW4uaxmSgUKpqhp8cI/daujm1oqcm/lbpHFCPfmQT30h1WIOQI73jfarr4YQrDG8r+ZYQKOZbDLv0Wbl2AihIELWyupADS1/fx6pCtTVHfmgbsiKYz7fl7KziPdge6P4VVSQKBgCmpePC5cHqC8usoie9GbYdaUoMdIxpodqnAZP5sXMcjsCLRd5YKFbqRZXZtfnjoimEhRJ0YyU5rkwgXCsGkqqrgzq83kq9YSCPXTQmNjIMgQkI19C7mpycfCvUrHRVw2g7ux+ZuJMq6e02H++8TGBZNoArC4+n3sEOFy8FCJSBlAoGBALMPVf44NuhOMI5+IKTJxK/ny9GmkZT2E9OnjPzY/2/nWL44H0kZ4CCnxlfDRov+eBjKEsHNBREi4vGnUVjCA+NAcONN4rDEoN9eYgFmuZXQjBwr3OL6VZLZPTwK/g6pO6GZrAV9gm3b49ruwx9H5nIntU7+FZYkyk2Bi9PMn5oBAoGAJfRmMiDKBtQu2CC/knNWhEVta5homVpbfHhWwc4GqM3lMC5N/iXZHySOJELoM7jXxAKGzXstob8jyj3NoN47Jbrm4QnQRF7LiNn+Nb11utB9eIU/CHInuyQZuY3QSV2MCCaot/UQ4FaqMwX9va7Zoa/EaXK+IByLhsy5Zgz36dU=";

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4xWy5oCQ5By6hn2M3mG6WSKbKBpqrnqwXCkzEutJPNDOh4rE3RxDDkfUBRLxa6YntoE+lhnQkmE3miiGa7c8imbzipBMa1s9yEcoOTbvvB5lSSmztoiwGL8SCJM9VcqRtD5yYPFFdTJu/SoGyGCIghX25GrGG7nNoNnm9wbJH0iNkA81FRFpKGZWhmwX8rW9WVD/jLj2Fm7oh00d2T5LtbgWrnsFNYuOyUVZaKuiLyZ4ak/2XBJiPVWOlghvb5mitRajn69eAl5JtiEPyeZ/eu2JNrfLoe7zLO4cOkyVuFwsy7jDe7lXIQCANoXdPjQIEMokKw/31dwQDDI8NU4A/QIDAQAB";

    // Generate Token
    public String generateToken(String username) {
        try {
            String payload = Base64.getUrlEncoder().encodeToString((
                "{\"sub\":\"" + username + "\",\"iat\":" + System.currentTimeMillis() +
                ",\"exp\":" + (System.currentTimeMillis() + EXPIRATION_TIME) + "}"
            ).getBytes(StandardCharsets.UTF_8));

            String unsignedToken = HEADER + "." + payload;

            // Sign the token
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(getPrivateKey());
            signature.update(unsignedToken.getBytes(StandardCharsets.UTF_8));

            String signedToken = Base64.getUrlEncoder().withoutPadding().encodeToString(signature.sign());
            return unsignedToken + "." + signedToken;
        } catch (Exception e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    // Validate Token
    public boolean validateToken(String token, String username) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false; // Invalid token format
            }
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            System.out.println("Payload JSON: " + payloadJson); // Debugging

            String unsignedToken = parts[0] + "." + parts[1];
            String signature = parts[2];

            // Verify the signature
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(getPublicKey());
            publicSignature.update(unsignedToken.getBytes(StandardCharsets.UTF_8));

            boolean isSignatureValid = publicSignature.verify(Base64.getUrlDecoder().decode(signature));
            if (!isSignatureValid) {
                return false; // Invalid signature
            }

            // Verify expiration
            payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            long exp = Long.parseLong(payloadJson.split("\"exp\":")[1].split(",")[0].replace("}", ""));
            return exp > System.currentTimeMillis();

        } catch (Exception e) {
            throw new RuntimeException("Error while validating token", e);
        }
    }

    // Parse Username from Token
    public String getUsernameFromToken(String token) {
        String[] parts = token.split("\\.");
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        return payloadJson.split("\"sub\":\"")[1].split("\"")[0];
    }

    // Get Private Key
    private PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(PRIVATE_KEY);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // Get Public Key
    private PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(PUBLIC_KEY);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}
