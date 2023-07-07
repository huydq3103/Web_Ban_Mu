package com.example.demo.Assignment.Service.Iplm;

import com.example.demo.Assignment.Model.Account;
import com.example.demo.Assignment.Responsitory.IAccountRespon;
import com.example.demo.Assignment.Service.ILogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginIplm implements ILogin {

    @Autowired
    private IAccountRespon respon;

    @Override
    public Account checkAccount(String user, String pass) {
        return respon.findByUserNameAndPassword(user, pass);
    }

    @Override
    public void createAccount(Account account) {
        respon.save(account);
    }

    @Override
    public Account getOne(String user) {
        return respon.findByUserName(user);
    }

    @Override
    public Account finbymail(String mail) {
        return respon.findByMail(mail);
    }


}
