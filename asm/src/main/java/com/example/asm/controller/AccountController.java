package com.example.asm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.asm.domain.Account;

import com.example.asm.domain.Role;
import com.example.asm.dto.AccountDto;
import com.example.asm.service.AccountService;
import com.example.asm.service.RoleService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("dashboard")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

 

    @GetMapping("accounts")
    public String categories(Model model) {
        System.out.println("go here");

        List<Account> accounts = accountService.findAll();
        // System.out.println("List account: "+accounts);
        model.addAttribute("accountss", accounts);
        return "admin/custom/accounts";
    }

    @GetMapping("accounts/create")
    public String createCategories(Model model) {
        System.out.println("go here create");

        Account account = new Account();

        model.addAttribute("accountss", account);
        return "admin/form/createAccounts";

    }

    // create accounts
    @PostMapping("accounts/create")
    public String createCategoriesPost(Model model, @Valid @ModelAttribute("accountss") AccountDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "admin/form/createAccounts";
        }

        Account copy = new Account();
        // copy.setActivated(dto.get);
        BeanUtils.copyProperties(dto, copy);
        System.out.println("copy: " + copy);
        accountService.save(copy);
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/dashboard/accounts";

    }

    // remove accounts
    @GetMapping("accounts/remove/{username}")
    public String delete(
            @PathVariable("username") String username,
            RedirectAttributes redirAttrs) {
        if (username != null) {
            Optional<Account> detail = accountService.findById(username);
            if (detail.isPresent()) {
                accountService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/dashboard/accounts";
            }
        }
        return "redirect:/dashboard/accounts";
    }

    @GetMapping("accounts/editAcc/{username}")
    public String edit(Model model, @PathVariable("username") String username) {

        if (username != null) {
            Optional<Account> accountDetail = accountService.findById(username);
            if (accountDetail.isPresent()) {
                model.addAttribute("account", accountDetail.get());
                return "admin/form/editAccount";
            }
        }
        return "redirect:/dashboard/accounts";
    }

    @PostMapping("accounts/editAcc")
    public String editRole(Model model,
            @Valid @ModelAttribute("account") AccountDto dto,
            BindingResult result,
            RedirectAttributes redirAttrs) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "admin/form/editAccount";
        }
        Account account = new Account();
        // account.setUsername(dto.getUsername());
        BeanUtils.copyProperties(dto, account);
        accountService.save(account);
        redirAttrs.addFlashAttribute("success", "Edit success");
        return "redirect:/dashboard/accounts"; // Return tên của View, model sẽ tự động pass vào view
    }
}
