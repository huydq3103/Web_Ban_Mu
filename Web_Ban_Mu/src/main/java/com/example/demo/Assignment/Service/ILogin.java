package com.example.demo.Assignment.Service;


import com.example.demo.Assignment.Model.Account;
//import org.springframework.security.core.userdetails.UserDetails;

public interface ILogin {

     Account checkAccount(String user,String pass);

     void createAccount(Account account);

     Account getOne(String user);

     Account finbymail(String mail);




}
