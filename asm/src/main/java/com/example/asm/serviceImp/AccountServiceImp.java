package com.example.asm.serviceImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.asm.Repository.AccountRepository;
import com.example.asm.domain.Account;
import com.example.asm.service.AccountService;
import com.example.asm.utils.HashPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    Connection conn = DBProvider.getConnection();
    HashPassword hashPassword = new HashPassword();

    public Account checkLogin(String username, String password) {
        return accountRepository.checkLogin(username, hashPassword.encrypt(password));
    }

    @Autowired
    AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    public List<Account> findAllById(Iterable<String> ids) {
        return accountRepository.findAllById(ids);
    }

    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    public void flush() {
        accountRepository.flush();
    }

    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Account> entities) {
        accountRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<String> ids) {
        accountRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    public Account getOne(String id) {
        return accountRepository.getOne(id);
    }

    public Account getById(String id) {
        return accountRepository.getById(id);
    }

    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }

    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public <S extends Account> S save(S entity) {
        entity.setPassword(hashPassword.encrypt(entity.getPassword()));
        return accountRepository.save(entity);
    }

    public <S extends Account> S saveUpdate(S entity) {
        String decrypt = hashPassword.decrypt(entity.getPassword());
        entity.setPassword(hashPassword.encrypt(decrypt));
        return accountRepository.save(entity);
    }

    public <S extends Account> S saveLogin(S entity) {
        entity.setPassword(hashPassword.encrypt(entity.getPassword()));
        entity.setPhoto("https://www.w3schools.com/howto/img_avatar.png");
        entity.setActivated(true);
        return accountRepository.save(entity);
    }

    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    public boolean existsById(String id) {
        return accountRepository.existsById(id);
    }

    public long count() {
        return accountRepository.count();
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends String> ids) {
        accountRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }

    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }

    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }

    public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return accountRepository.findBy(example, queryFunction);
    }

    public boolean isExist(String username) {
        try {

            String sql = "SELECT * FROM accounts where username = ? ";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());

        }
        return false;
    }

    public boolean isExistAccountForget(String username, String email) {
        try {
            String sql = "SELECT * FROM accounts where username = ? and email = ? ";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, username);
            pst.setString(2, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());

        }
        return false;
    }

    public boolean updateAccountForget(String password, String username) {
        try {
            String sql = "UPDATE accounts SET password =  ? where username = ? ";
            PreparedStatement pst = conn.prepareCall(sql);
            // pst.setString(1, hashPassword.encrypt("123456"));
            pst.setString(1, password);
            pst.setString(2, username);

            int rs = pst.executeUpdate();
            if (rs > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateForget(String password, String username) {
        String sqlUpdate = "UPDATE accounts "
                + "SET password = ? "
                + "WHERE username = ?";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            // prepare data for update

            pstmt.setString(1, hashPassword.encrypt(password));
            pstmt.setString(2, username);

            int rowAffected = pstmt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

            pstmt.setString(1, hashPassword.encrypt(password));
            pstmt.setString(2, username);

            rowAffected = pstmt.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String codeRandom(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public String getPasswordForgot(String username, String email) {
        try {

            String sql = "SELECT * FROM accounts where username = ? and email = ?";
            PreparedStatement pst = conn.prepareCall(sql);
            pst.setString(1, username);
            pst.setString(2, email);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return hashPassword.decrypt(rs.getString("password"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());

        }
        return "";
    }

    // public String hasPass(String pass) {
    // try {
    // String passHass = bCryptPasswordEncoder.encode(pass);
    // return passHass;
    // } catch (Exception e) {
    // return null;
    // // TODO: handle exception
    // }
    // }

    // public boolean comparePass (String password,String hashPass){

    // }
    // @Override
    // public int hashCode() {
    // return accountRepository.hashCode();
    // }

    // @Override
    // public boolean equals(Object obj) {
    // return accountRepository.equals(obj);
    // }

    // @Override
    // public String toString() {
    // return accountRepository.toString();
    // }

}
