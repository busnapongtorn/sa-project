package cs.ku.sa_project.entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String password;

    public User(String username) {
        this.username = username;
    }
}
