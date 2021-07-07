package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository transactionRepo;


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
        addTransactionToUserAccount(oldUser, balance, "deposit");
    }

    public void subtractDepositToUserAccount(String userID, int balance) {
        User oldUser = userRepo.findUserByUserID(userID);
        if (balance > oldUser.getBalance()) {
            balance = 0;
            oldUser.setBalance(balance);
        }
        oldUser.setBalance(oldUser.getBalance() - balance);
        userRepo.save(oldUser);
        addTransactionToUserAccount(oldUser, balance, "withdrawal");
    }
    private void addTransactionToUserAccount(User user, int amount, String type){
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(type);
        transaction.setTimestamp(new java.util.Date().toString());
        transaction.setUser(user);
        transactionRepo.save(transaction);
    }


}
