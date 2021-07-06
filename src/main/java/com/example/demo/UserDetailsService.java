package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        User user =  userRepo.findAll(userID);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);


    }

    public void savingUserInfo(User user) {
        userRepo.save(user);
    }

    public void addDepositToUserAccount(String userID, int balance){
        User oldUser = userRepo.findUserByUserID(userID);
        oldUser.setBalance(oldUser.getBalance() + balance);
        userRepo.save(oldUser);
    }

    public void subtractDepositToUserAccount(String userID, int balance) {
        User oldUser = userRepo.findUserByUserID(userID);
        if (balance > oldUser.getBalance()) {
            balance = 0;
            oldUser.setBalance(balance);
        }
        oldUser.setBalance(oldUser.getBalance() - balance);
        userRepo.save(oldUser);
    }
    public void addTransactionToUserAccount(long id, int amount){
        Transaction oldUser = userRepo.findById(id);
        oldUser.setAmount(oldUser.getAmount() + amount);
        userRepo.save(oldUser.getUser());
    }


}
