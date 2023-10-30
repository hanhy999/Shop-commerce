package com.example.asm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.asm.domain.Role;
import com.example.asm.dto.AccountDto;
import com.example.asm.dto.RoleDto;
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
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    AccountDto accountDto;

    @GetMapping("roles")
    public String index(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "/admin/custom/role";

    }

    @GetMapping("roles/create")
    public String create(Model model) {
        Role roles = new Role();
        model.addAttribute("roles", roles);
        return "admin/form/createRole";
    }

    @PostMapping("roles/create")
    public String createRoles(Model model,
            @Valid @ModelAttribute("roles") RoleDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.err.println("có lỗi");
            return "admin/form/createRole";
        }
        Role role = new Role();
        // role.setRoleId(dto.getName());
        BeanUtils.copyProperties(dto, role);
        roleService.save(role);
        redirectAttributes.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/dashboard/roles"; // Return tên của View, model sẽ tự động passvàoview
    }

    @GetMapping("roles/edit/{roleId}")
    public String edit(Model model, @PathVariable("roleId") int roleId) {

        if (roleId != 0) {
            Optional<Role> roleDetail = roleService.findById(roleId);
            if (roleDetail.isPresent()) {
                model.addAttribute("roles", roleDetail.get());
                return "admin/form/editRoles";
            }

        }
        return "redirect:/dashboard/roles";
    }

    @PostMapping("roles/edit")
    public String editRole(Model model,
            @Valid @ModelAttribute("role") RoleDto dto,
            BindingResult result,
            RedirectAttributes redirAttrs) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "admin/form/editRoles";
        }

        System.out.println("idddđ "+dto.getRoleId());
        Role role = new Role();
        role.setRoleId(dto.getRoleId());
        BeanUtils.copyProperties(dto, role);

        roleService.save(role);
        redirAttrs.addFlashAttribute("success", "Edit thành công");
        return "redirect:/dashboard/roles"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("roles/delete/{roleId}")
    public String delete(
            @PathVariable("roleId") int roleId,
            RedirectAttributes redirAttrs) {
        if (roleId != 0) {
            Optional<Role> detail = roleService.findById(roleId);
            if (detail.isPresent()) {
                roleService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/dashboard/roles";
            }
        }
        return "redirect:/dashboard/roles";
    }

}
