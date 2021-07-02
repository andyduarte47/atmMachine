//package com.example.demo;
//import javax.persistence.*;
//
//@Entity
//@Table(name="transactions")
//public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne
//    private User user;
//
//    @Column(name = "transaction_type", nullable = false, length = 20)
//    private String transactionType; // deposit, withdrawal
//
//    @Column(name = "amount", nullable = false)
//    private int amount;
//
//    @Column(name = "timestamp")
//    private String timestamp;
//
//}
