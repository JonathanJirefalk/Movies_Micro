package com.example.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User newUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser){

        User targetUser = getUserById(id);

        if (targetUser != null) {

            targetUser = updatedUser;
            return newUser(targetUser);
        }else{

            throw new EntityNotFoundException("User not found");
        }
    }
}