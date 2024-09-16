import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

// Response class for token (based on OAuth2 token response structure)
public class TokenResponse {
    private String access_token;
    private String token_type;
    private int expires_in;

    // Getters and setters
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getToken_type() {
        return token_type;
    }
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public int getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}

// Request class for the PUT request payload
public class UpdateRequest {
    private String field1;
    private String field2;

    // Getters and setters
    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }
}

// Response class for the PUT request
public class UpdateResponse {
    private String status;
    private String message;

    // Getters and setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

public class ApiService {

    private RestTemplate restTemplate = new RestTemplate();

    // Step 1: Method to call the authorization API and get the token
    public String getAuthToken(String authUrl, String clientId, String clientSecret) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Set body parameters
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);

        // Create the request entity
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            // Make the POST request to get the token
            ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
                authUrl, 
                request, 
                TokenResponse.class
            );

            // Check if token is received successfully
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getAccess_token(); // Return access token
            } else {
                throw new Exception("Failed to retrieve access token, status code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("Error during token retrieval: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Step 2: Method to call the PUT API with the authorization token
    public UpdateResponse callPutApi(String apiUrl, UpdateRequest updateRequest, String authToken) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authToken);  // Add the token to the Authorization header

        // Create request entity
        HttpEntity<UpdateRequest> requestEntity = new HttpEntity<>(updateRequest, headers);

        try {
            // Make the PUT request
            ResponseEntity<UpdateResponse> response = restTemplate.exchange(
                apiUrl,                                  // API URL
                HttpMethod.PUT,                          // HTTP method
                requestEntity,                           // Request entity
                UpdateResponse.class                     // Response type
            );

            // Check if the response is OK
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody(); // Return the response body
            } else {
                throw new Exception("PUT API call failed, status code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("Error during PUT API call: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Main method to perform both steps
    public void executeApiFlow(String authUrl, String clientId, String clientSecret, String apiUrl) {
        // Step 1: Get the token
        String token = getAuthToken(authUrl, clientId, clientSecret);
        if (token == null) {
            System.out.println("Failed to obtain token, aborting API call.");
            return;
        }

        // Step 2: Create the request payload for PUT API
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setField1("value1");
        updateRequest.setField2("value2");

        // Step 3: Call the PUT API with the token
        UpdateResponse response = callPutApi(apiUrl, updateRequest, token);
        if (response != null) {
            System.out.println("PUT API call successful: " + response.getMessage());
        } else {
            System.out.println("PUT API call failed.");
        }
    }
}
