package edu.trin.aggregator.Aggregator.services;



import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.trin.aggregator.Aggregator.models.User;

@Service
public class UserService {
    
    public List<User> findAll() {
        String urlName = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
        String port = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
        String api = System.getenv("Users_api_get") != null ? System.getenv("Users_api_get") : "users";
        String url = String.format("http://%s:%s/%s", urlName, port, api);
        RestTemplate restTemplate = new RestTemplate();
        List<User> users = restTemplate.getForObject(url,List.class);
   
        return users;
    }

    public User findUser(String username){
        String urlName = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
        String port = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
        String api = System.getenv("Users_api_get") != null ? System.getenv("Users_api_get") : "users";
        String url = String.format("http://%s:%s/%s/%s", urlName, port, api, username);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(url,User.class);
        
        return user;
    }

    public Boolean checkUser(String username, String password){
        String urlName = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
        String port = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
        String api = System.getenv("Check_User") != null ? System.getenv("Check_User") : "check-user";
        String url = String.format("http://%s:%s/%s/%s/%s", urlName, port, api, username, password);
        RestTemplate restTemplate = new RestTemplate();
        Boolean response = restTemplate.getForObject(url,Boolean.class);

        return response;
    }

    public String addUser(User user){
        RestTemplate restTemplate = new RestTemplate();
        String urlName = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
        String port = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
        String api = System.getenv("Users_api_add") != null ? System.getenv("Users_api_add") : "add-user";
        String url = String.format("http://%s:%s/%s", urlName, port, api);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity(user, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if(findUser(user.getUsername()) == null){
            return "OPERATION FAILED";
        }
        if(response.getBody().equals("USERNAME ALREADY EXISTS. PICK ANOTHER USERNAME")){
            return response.getBody();
        }
        return "USER ADDED SUCCESSFULY";
    }

    public ResponseEntity<String> updatePassword(User user, String password){
        RestTemplate restTemplate = new RestTemplate();
        String urlName = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
        String port = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
        String api = System.getenv("Users_api_update") != null ? System.getenv("Users_api_update") : "update-user";
        String url = String.format("http://%s:%s/%s/%s", urlName, port, api, password);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

        return response;
    }

    public ResponseEntity<String> deleteUser(String username, String password){
        RestTemplate restTemplate = new RestTemplate();
        String urlName = System.getenv("Users") != null ? System.getenv("Users") : "localhost";
        String port = System.getenv("Users_Port") != null ? System.getenv("Users_Port") : "8083";
        String api = System.getenv("Users_api_delete") != null ? System.getenv("Users_api_delete") : "delete-user";
        String url = String.format("http://%s:%s/%s/%s/%s", urlName, port, api, username, password);
        
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

        return response;
    }
}
