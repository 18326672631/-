package com.boen.mail.controller;


import com.boen.commonUtils.R;
import com.boen.mail.service.mailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edumail/mail")
@CrossOrigin
public class mailcontroller {

    @Autowired
    private mailservice mailservice;



    @GetMapping("/sendcode/{mail}")
    public R sendCodeofMail(@PathVariable String mail){

        mailservice.senmail(mail);
        return R.ok();

    }
}
