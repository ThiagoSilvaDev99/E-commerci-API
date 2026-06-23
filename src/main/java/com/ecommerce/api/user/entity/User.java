package com.ecommerce.api.user.entity;

import com.ecommerce.api.shared.exception.UserValidationException;
import com.ecommerce.api.user.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
public class User {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id", columnDefinition = "VARCHAR(36)")
    private UUID userId;

    @NotBlank
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @Column(name = "user_email", unique = true)
    private String userEmail;

    @NotBlank
    @Column(name = "user_password")
    private String userPassword;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "active", nullable = false)
    private boolean active;

    private User(String userName, String userEmail, String userPassword, UserRole userRole, boolean active) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.active = active;
    }

    public static User createUser(String userName, String userEmail, String userPassword, UserRole userRole){

        if(userName == null || userName.isBlank()){
            throw new UserValidationException("User name is null or blank");
        }

        if(userEmail == null || userEmail.isBlank()){
            throw new UserValidationException("User email is null or blank");
        }

        if(userPassword == null || userPassword.isBlank()){
            throw new UserValidationException("User password is null or blank");
        }

        if(userRole == null){
            throw new UserValidationException("User role is null");
        }

        return new User(userName, userEmail, userPassword, userRole, true);
    }

    public void updateName(String newName){

        if(newName == null || newName.isBlank()){
            throw new UserValidationException("User name is null or blank");
        }

        this.userName = newName;
    }

    public void updateEmail(String newEmail){

        if(newEmail == null || newEmail.isBlank()){
            throw new UserValidationException("User email is null or blank");
        }

        this.userEmail = newEmail;
    }

    public void updatePassword(String newPassword){

        if(newPassword == null || newPassword.isBlank()){
            throw new UserValidationException("User password is null or blank");
        }

        this.userPassword = newPassword;
    }

    public void deactivate(){
        this.active = false;
    }
}
