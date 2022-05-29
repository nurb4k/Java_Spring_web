package kz.narxoz.nurbeksessionproject.sessionProject.services;


import kz.narxoz.nurbeksessionproject.sessionProject.Model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);

    Users createUser(Users user);
}
