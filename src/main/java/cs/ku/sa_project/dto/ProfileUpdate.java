package cs.ku.sa_project.dto;

import lombok.Data;

@Data
public class ProfileUpdate {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
}
