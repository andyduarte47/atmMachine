package com.example.demo;
import javax.persistence.*;

    @Entity
    @Table(name="UserInformation")
    public class User{
        public long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "first_name", nullable = false, length = 20)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 20)
        private String lastName;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        @Column(name = "balance",nullable = false,length = 1000000000)
        private int balance;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Column(name = "password",nullable = false, length = 64)
        private String password;


        public String getUserid() {
            return userID;
        }

        public void setUserid(String userID) {
            this.userID = userID;
        }

        @Column(name = "userID",nullable = false, unique = true, length = 45)
        private String userID;

    }
