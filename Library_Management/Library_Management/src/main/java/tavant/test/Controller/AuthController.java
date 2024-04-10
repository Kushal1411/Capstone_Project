package tavant.test.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import tavant.test.model.UserDetails;
import tavant.test.model.AuthRequest;
import java.util.Map;

    @RestController
    public class AuthController {

        // Dummy database to store user details
        private static Map<String, UserDetails> userDatabase = new HashMap<>();

        // Dummy initialization of user details
        static {
            userDatabase.put("user01", new UserDetails("password", "user"));
            userDatabase.put("admin01", new UserDetails("password", "admin"));
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
            String username = authRequest.getUsername();
            String password = authRequest.getPassword();

            // Check if username exists in the database
            if (!userDatabase.containsKey(username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
            }

            UserDetails userDetails = userDatabase.get(username);

            // Check if the password matches
            if (!userDetails.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }

            // Create JWT token
            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("role", userDetails.getRole())
                    .signWith(SignatureAlgorithm.HS256, "secretKey") // Replace "secretKey" with your secret key
                    .compact();

            return ResponseEntity.ok(token);
        }

    }


