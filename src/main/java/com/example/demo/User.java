package com.example.demo;
import javax.persistence.*;



    @Entity
    @Table(name = "UserInformation")
    public class User {

        public Long getId() {
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
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
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
        private Long id;

        @Column(nullable = false, unique = true, length = 45)
        private String userID;

        @Column(nullable = false, length = 64)
        private String Password;

        @Column(name = "first_name", nullable = false, length = 20)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 20)
        private String lastName;

        // getters and setters are not shown
    }
