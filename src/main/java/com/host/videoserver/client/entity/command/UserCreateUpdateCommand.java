package com.host.videoserver.client.entity.command;

import com.host.videoserver.client.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateUpdateCommand {

    private Long id;
    private String email;
    private String password;
    private String surname;
    private String lastname;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setSurname(this.surname);
        user.setLastname(this.lastname);
        return user;
    }
}
