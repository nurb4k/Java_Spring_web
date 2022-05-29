package kz.narxoz.nurbeksessionproject.sessionProject.services.impl;


import kz.narxoz.nurbeksessionproject.sessionProject.Model.Roles;
import kz.narxoz.nurbeksessionproject.sessionProject.Model.Users;
import kz.narxoz.nurbeksessionproject.sessionProject.repository.RoleRepository;
import kz.narxoz.nurbeksessionproject.sessionProject.repository.UsersRepository;
import kz.narxoz.nurbeksessionproject.sessionProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

    @Service
    public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users myUser = userRepository.findByEmail(username);
        if (myUser != null){
            User secUser = new User(myUser.getEmail(),myUser.getPassword(),myUser.getRoles());
            return secUser;

        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users createUser(Users user) {
       Users  checkUser = userRepository.findByEmail(user.getEmail());
       if (checkUser==null){
           Roles role =roleRepository.findByRole("ROLE_USER");
           if (role!=null){
               ArrayList<Roles> roles = new ArrayList<>();
               roles.add(role);
               user.setRoles(roles);
               user.setPassword(passwordEncoder.encode(user.getPassword()));
               return userRepository.save(user);
           }

       }
       return null;
    }
}
