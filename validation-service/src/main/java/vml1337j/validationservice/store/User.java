package vml1337j.validationservice.store;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    private Long id;
    private String username;
    private String roles;
    private String password;
}
