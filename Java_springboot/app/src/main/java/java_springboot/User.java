import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.ID;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score;
    private Set<String> badges;

    public User() {
    }

    public User(String username) {
        this.username = username;
        this.score = 0;
        this.badges = new HashSet<>();
    }

}
