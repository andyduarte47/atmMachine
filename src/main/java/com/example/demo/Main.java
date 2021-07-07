package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import org.springframework.ui.Model;

import java.text.AttributedString;
import java.util.List;
import java.util.function.Predicate;


@Controller

public class Main {



    @GetMapping("")
     public String main(){
         return "index";
     }

     @Autowired
     private UserRepository userRepo;
    @Autowired
    private CustomUserDetailsService service;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }
    @GetMapping("/users")
    public String listUsers(Model model, @AuthenticationPrincipal CustomUserDetails detail) {
        List<User> listUsers = userRepo.findAll();
        listUsers.removeIf(user -> user.getId() != detail.getId());
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {

        return "redirect:depositPage";
    }

    @GetMapping("/depositPage")
    public String finalPage(Model model,@AuthenticationPrincipal CustomUserDetails detail) {
        model.addAttribute("userID",detail.getUsername());
        User user = userRepo.findUserByUserID(detail.getUsername());
        model.addAttribute("user", user);
        return "deposit";
    }

    @RequestMapping(value = "/redirect2", method = RequestMethod.GET)
    public String redirect2() {

        return "redirect2:withdrawPage";
    }

    @RequestMapping(value = "/withdrawPage", method = RequestMethod.GET)
    public String withdrawPage(Model model,@AuthenticationPrincipal CustomUserDetails detail) {
        model.addAttribute("userID",detail.getUsername());
        User user = userRepo.findUserByUserID(detail.getUsername());
        model.addAttribute("user", user);
        return "withdraw";
    }

    @RequestMapping(value = "/redirect3", method = RequestMethod.GET)
    public String redirect3() {

        return "redirect3:listPage";
    }

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "listPage";
    }

    @RequestMapping(value = "/redirect4", method = RequestMethod.GET)
    public String redirect4() {

        return "redirect4:detailPage";
    }

    @RequestMapping(value = "/detailPage", method = RequestMethod.GET)
    public String detailPage(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "detailPage";
    }

    @RequestMapping(value="/process_transfer/{userID}", method= RequestMethod.POST)
    public String addingDeposit(@PathVariable("userID") String userID, @ModelAttribute("user") User user, Model model,@AuthenticationPrincipal CustomUserDetails detail) {
        int balance = user.getBalance();
        service.addDepositToUserAccount(userID, balance);
        List<User> listUsers = userRepo.findAll();
        listUsers.removeIf(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() != detail.getId();
            }
        });
        model.addAttribute("listUsers", listUsers);
        return "transfer_success";
    }
    @RequestMapping(value="/process_withdraw/{userID}", method= RequestMethod.POST)
    public String subtractDeposit(@PathVariable("userID") String userID, @ModelAttribute("user") User user,Model model,@AuthenticationPrincipal CustomUserDetails detail) {
        int balance = user.getBalance();
        service.subtractDepositToUserAccount(userID, balance);
        List<User> listUsers = userRepo.findAll();
        listUsers.removeIf(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() != detail.getId();
            }
        });
        model.addAttribute("listUsers", listUsers);
        return "transfer2_success";
    }

}
