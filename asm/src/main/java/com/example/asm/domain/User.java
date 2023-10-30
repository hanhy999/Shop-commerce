// package com.example.asm.domain;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.validation.constraints.NotEmpty;

// import lombok.Data;

// @Data
// @Entity
// @Table(name = "users")
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(length = 50, nullable = false)
//     private int id;
    
//     @NotEmpty(message = " username is required")
//     @Column(length = 200, nullable = false)
//     private String username;

//     @NotEmpty(message = "password is required")
//     @Column(length = 200, nullable = false)
//     private String password;

//     @NotEmpty(message = "role is required")
//     @Column(length = 200, nullable = false)
//     private String role;
    
// }
