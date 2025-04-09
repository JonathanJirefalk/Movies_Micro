package com.example.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WebClient reviewClient;

    public UserController(UserService userService, WebClient.Builder reviewClientBuilder) {
        this.userService = userService;
        this.reviewClient = reviewClientBuilder.baseUrl("http://localhost:8082").build();
    }




    //Read______________________________________________________________________________________________________________

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<UserResponse> getReviewsByUserId(@PathVariable Long id) {

        User user = userService.getUserById(id);
        Flux<Review> reviewFlux = getReview(id);

        return ResponseEntity.ok(new UserResponse(user, reviewFlux.collectList().block()));
    }




    //Create____________________________________________________________________________________________________________

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.newUser(user));
    }




    //Edit______________________________________________________________________________________________________________

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {

        return  ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }




    //Delete____________________________________________________________________________________________________________

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }




    //Other Functions___________________________________________________________________________________________________

    private Flux<Review> getReview(Long id){

        return reviewClient.get().uri("/reviews/" + id + "/user").retrieve().bodyToFlux(Review.class);
    }
}