package com.example.asm.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.asm.domain.Account;
import com.example.asm.dto.AccountDto;
import com.example.asm.dto.AccountForgetDto;
import com.example.asm.dto.AccountLoginDto;
import com.example.asm.dto.ConfirmAccountDto;
import com.example.asm.service.AccountService;
import com.example.asm.service.MailService;

@Controller
@RequestMapping("")
public class AuthAdminController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpSession session;

    private String codeRandom;
    private String saveUserName;

    // @Autowired
    // private JavaMailSender javaMailSender;

    @Autowired
    private MailService mailService;

    @ModelAttribute("accountRegister")
    public AccountDto accountRegister() {
        AccountDto accountDto = new AccountDto();
        return accountDto;
    }

    @ModelAttribute("accountLogin")
    public AccountLoginDto AccountLoginDto() {
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        return accountLoginDto;
    }

    @GetMapping("login")
    public String login(Model model) {
        // AccountDto accountRegisterDto = new AccountDto();
        // AccountLoginDto accountLoginDto = new AccountLoginDto();
        // model.addAttribute("accountLogin", accountLoginDto);
        // model.addAttribute("accountRegister", accountRegisterDto);
        // try {
        // SimpleMailMessage msg = new SimpleMailMessage();
        // msg.setTo("hanhy222000@gmail.com");

        // msg.setSubject("Test send mail in spring boot ");
        // msg.setText("Hello <h1>Han Hy </h1> ");
        // javaMailSender.send(msg);
        // } catch (Exception e) {
        // //TODO: handle exception
        // System.err.println("error send mail "+e.getMessage());
        // e.printStackTrace();
        // }

        return "login";
    }

    @PostMapping("login/register")
    public String createCategoriesPost(Model model, @Valid @ModelAttribute("accountRegister") AccountDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "login";
        }

        System.err.println(dto.getUsername() + dto.getPassword());

        String mailTo = dto.getEmail();

        if (accountService.isExist(dto.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Username is exist");
            return "redirect:/login";
        } else {

            try {
                mailService.sendAsHtml(mailTo,
                        "Welcome to Dawn: " + mailTo,
                        "<div >\n"
                                + "        <h2 style=\"color: black;\">Welcome to Dawn: </h2>\n"
                                + "        <p style=\"width: 1000px;color: black;\">We're glad you're here! Check out our getting started guide to start building your\n"
                                + "            first\n"
                                + "            cluster.\n"
                                + "            If you need any help, you can reach out through chat in the Atlas UI or by filing a support ticket.\"\n"
                                + "            We can't wait to see what you build!.</p>\n"
                                + "\n"
                                + "        <a style=\"text-decoration: none;background-color: green;padding: 10px 20px 10px 20px;color: white;\n"
                                + "            margin-left: 470px; border-radius: 5px;\n"
                                + "    \" href=\"http://localhost:8080/login\">Here</a>\n"
                                + "\n"
                                + "    <footer style=\"margin-top: 40px;background-color: rgb(34, 34, 34);color: white;padding: 5px;text-align: center;\n"
                                + "    font-size: 14px;font-weight: 10;width: 1000px;\n"
                                + "    \"\n"
                                + "    \n"
                                + "    >Copyright © 2023 All rights reserved | This web is made with  by Han Hy</footer>\n"
                                + "    </div>");
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Account copy = new Account();
            // copy.setActivated(dto.get);
            BeanUtils.copyProperties(dto, copy);
            System.out.println("copy: " + copy);
            accountService.saveLogin(copy);
            redirectAttributes.addFlashAttribute("registerSuccess", "Register succsess");
            return "redirect:/login";
        }
    }

    @GetMapping("logout")
    public String logout(Model model) {
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        if (session.getAttribute("username") != null) {
            session.removeAttribute("username");
            session.removeAttribute("role");
            session.removeAttribute("mess");
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    @PostMapping("login")
    public String loginPost(Model model, @Valid @ModelAttribute("accountLogin") AccountLoginDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        if (result.hasErrors()) {
            return "login";
        }
        Account check = accountService.checkLogin(dto.getUsername(), dto.getPassword());
        if (check != null) {
            String role = "";

            // mailService.sendEmail("truongvan6322@gmail.com", "test sendmail", "<h1> hello
            // </h1>");

            if (check.isAdmin()) {
                role = "admin";
                System.err.println("adminnnnn " + check);
                System.out.println(check.getFullName());
                session.setAttribute("username", check.getUsername());
                session.setAttribute("showName", check.getFullName());
                session.setAttribute("email", check.getEmail());
                session.setAttribute("role", role);
                return "redirect:/dashboard/home";
            } else {
                role = "user";
                session.setAttribute("username", check.getUsername());
                session.setAttribute("email", check.getEmail());
                session.setAttribute("showName", check.getFullName());
                session.setAttribute("role", role);
                return "redirect:/shop";
            }

            // session.setAttribute("username", check.getUsername());
            // session.setAttribute("role", role);
            // if (session.getAttribute("request-url") != null) {
            // return "redirect:" + session.getAttribute("request-url");
            // }
            // return "redirect:/dashboard/home";
        } else if (check == null) {
            redirectAttributes.addFlashAttribute("error", "Username or password incorrect");
        }
        return "redirect:/login";
    }

    @GetMapping("forgotPassword")
    public String forgot(Model model) {
        AccountDto accountLoginDto = new AccountDto();
        model.addAttribute("accounts", accountLoginDto);
        return "forgotPassword";
    }

    @PostMapping("login/forgotPassword")
    public String forgotPassword(Model model, @Valid @ModelAttribute("accounts") AccountForgetDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "forgotPassword";
        }
        System.err.println(dto.getUsername() + dto.getEmail());

        if (accountService.isExistAccountForget(dto.getUsername(), dto.getEmail())) {
            System.err.println("co tai khoan");
            String code = accountService.codeRandom(10);
            codeRandom = code;
            saveUserName = dto.getUsername();
            try {
                mailService.sendAsHtml(dto.getEmail(),
                        "Confirm Password : " + dto.getEmail(),
                        " <p style=\"color: black;font-size: 13.9px;\"> This is your verify code please don't share anyone:  </p>"
                                + "\n" + "<h3> " + code + " </h3>"
                                + "    <footer style=\"margin-top: 40px;background-color: rgb(34, 34, 34);color: white;padding: 5px;text-align: center;\n"
                                + "    font-size: 14px;font-weight: 10;width: 1000px;\n"
                                + "    \"\n"
                                + "    >Copyright © 2022 All rights reserved | This web is made with  by xuanquy</footer>\n");
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "redirect:/updatePassword";
        } else {
            redirectAttributes.addFlashAttribute("error", "Username or email is not exist");
            return "redirect:/forgotPassword";
        }

    }

    @GetMapping("updatePassword")
    public String updatePassword(Model model) {
        ConfirmAccountDto accountLoginDto = new ConfirmAccountDto();
        model.addAttribute("accounts", accountLoginDto);
        return "updatePassword";
    }

    @PostMapping("updatePassword")
    public String updatePasswordPost(Model model, @Valid @ModelAttribute("accounts") ConfirmAccountDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "updatePassword";
        }
        System.err.println("this is code and username send   " + codeRandom + " " + saveUserName + " password is: "
                + dto.getPassword() + "" + "code " + dto.getCode());
        if (dto.getCode().equals(codeRandom)) {

            accountService.updateForget(dto.getPassword(), saveUserName);

            redirectAttributes.addFlashAttribute("registerSuccess", "Update password success");
            return "redirect:/login";

        } else {
            redirectAttributes.addFlashAttribute("error", "The authentication code is incorrect");
            return "redirect:/updatePassword";
        }

    }

    @PostMapping("sendPassword")
    public String sendPassword(Model model, @Valid @ModelAttribute("accounts") AccountForgetDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "sendPassword";
        }
        if (accountService.isExistAccountForget(dto.getUsername(), dto.getEmail())) {

            String passwordForgot = accountService.getPasswordForgot(dto.getUsername(), dto.getEmail());

            System.err.println("password send" + passwordForgot);
            System.err.println("username and email send " + dto.getUsername() + " " + dto.getEmail());
            try {
                mailService.sendAsHtml(dto.getEmail(),
                        "Send Password forgot : " + dto.getEmail(),
                        " <p style=\"color: black;font-size: 13.9px;\"> This is your password please don't share anyone:  </p>"
                                + "\n" + "<h3> " + passwordForgot + " </h3>"
                                + "    <footer style=\"margin-top: 40px;background-color: rgb(34, 34, 34);color: white;padding: 5px;text-align: center;\n"
                                + "    font-size: 14px;font-weight: 10;width: 1000px;\n"
                                + "    \"\n"
                                + "    >Copyright © 2023 All rights reserved | This web is made with  by xuanquy</footer>\n");
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            redirectAttributes.addFlashAttribute("registerSuccess", "Send password success");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Username or email is not exist");
            return "redirect:/forgotPassword";
        }

    }

}
