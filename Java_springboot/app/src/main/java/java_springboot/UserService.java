package java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setScore(score);
        updateBadges(user);
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private void updateBadges(User user) {
        if (user.getScore() >= 60 && !user.getBadges().contains("Code Master")) {
            user.getBadges().add("Code Master");
        } else if (user.getScore() >= 30 && !user.getBadges().contains("Code Champ")) {
            user.getBadges().add("Code Champ");
        } else if (user.getScore() >= 1 && !user.getBadges().contains("Code Ninja")) {
            user.getBadges().add("Code Ninja");
        }
    }
}