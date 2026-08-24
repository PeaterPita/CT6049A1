
package github.peaterpita.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import github.peaterpita.model.User;
import github.peaterpita.repository.UserRepository;
import github.peaterpita.security.JwtUtil;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    // ###########################################################
    // # If user by passed in username exists and the passwords
    // # match
    // # generate a new JWT token and return to the user
    // ###########################################################
    public String attemptLogin(String username, String password) {
        System.out.println("--- LOGIN ATTEMPT ---");
        System.out.println("Input Username: [" + username + "]");
        System.out.println("Input Password: [" + password + "]");

        Optional<User> userOpt = userRepo.findByUsername(username);

        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();

        if (user.getPassword() == null ||
                !user.getPassword().equals(password)) {
            return null;
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
