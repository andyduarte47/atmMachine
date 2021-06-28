package com.example.demo;
import javax.persistence.*;



    @Entity
    @Table(name = "UserInformation")
    public class User {

        public long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public int getBalance(){
            return balance;
        }
        public void setBalance(int balance){
            this.balance = balance;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false, unique = true, length = 45)
        private String userID;

        @Column(nullable = false, length = 64)
        private String password;

        @Column(name = "first_name", nullable = false, length = 20)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 20)
        private String lastName;

        @Column(nullable = false,length = 64)
        private int balance;

        // getters and setters are not shown
    }
