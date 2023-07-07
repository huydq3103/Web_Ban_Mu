package com.example.demo.Assignment.Responsitory;

import com.example.demo.Assignment.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRespon extends JpaRepository<Account,Integer> {
    Account findByUserNameAndPassword(String user,String pass);

    Account findByUserName(String user);

    Account findByMail(String mail);
}
