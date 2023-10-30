package com.example.asm.Repository;

import com.example.asm.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a FROM Account a WHERE a.username = :username and a.password = :password ")
    public Account checkLogin(@Param("username") String username, @Param("password") String password);

}
