package com.example.asm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.asm.domain.Category;
import com.example.asm.dto.CategoryDto;
import com.example.asm.service.CategoryService;

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
public class CategoriesController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("categories")
    public String categories(Model model) {

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categorys", categories);

        return "admin/custom/categories";
    }

    @GetMapping("categories/create")
    public String createCategories(Model model) {
        System.out.println("go here create");

        Category categorys = new Category();
        model.addAttribute("category", categorys);
        return "admin/form/createCategories";

    }

    // edit categories get
    @GetMapping("categories/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {

        if (id != 0) {
            Optional<Category> cateDetail = categoryService.findById(id);
            if (cateDetail.isPresent()) {
                model.addAttribute("category", cateDetail.get());
                return "admin/form/editCategories";
            }
        }
        return "redirect:/dashboard/editCategories";
    }

    // edit categories post
    @PostMapping("categories/edit")
    public String editRole(Model model,
            @Valid @ModelAttribute("category") CategoryDto dto,
            BindingResult result,
            RedirectAttributes redirAttrs) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "admin/form/editCategories";

        }
        Category categories = new Category();
        // account.setUsername(dto.getUsername());
        BeanUtils.copyProperties(dto, categories);
        categoryService.save(categories);
        redirAttrs.addFlashAttribute("success", "Edit success");
        return "redirect:/dashboard/categories"; // Return tên của View, model sẽ tự động pass vào view
    }

    // create categories
    @PostMapping("categories/create")
    public String createCategoriesPost(Model model, @Valid @ModelAttribute("category") CategoryDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "admin/form/createCategories";
        }

        Category copy = new Category();
        BeanUtils.copyProperties(dto, copy);
        System.out.println("copy: " + copy);
        categoryService.save(copy);
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/dashboard/categories";

    }

    // @PostMapping("categories/create")
    // public String createCategoriesPost(Model model, @Valid @ModelAttribute("category") Category category,
    //         BindingResult result, RedirectAttributes redirectAttributes) {
    //     if (result.hasErrors()) {
    //         // đẩy lại view và đưa ra thông báo lỗi
    //         System.out.println("CO EROR");
    //         return "admin/form/createCategories";
    //     } else {

    //     // Category copy = new Category();
    //     // BeanUtils.copyProperties(dto, copy);
    //     // System.out.println("copy: " + copy);
    //     categoryService.save(category);
    //     redirectAttributes.addFlashAttribute("success", "Add succsess");
    //     return "redirect:/dashboard/categories";
    //     }
    // }

    // delete categories
    @GetMapping("categories/remove/{id}")
    public String delete(
            @PathVariable("id") int id,
            RedirectAttributes redirAttrs) {
        if (id != 0) {
            Optional<Category> detail = categoryService.findById(id);
            if (detail.isPresent()) {
                categoryService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/dashboard/categories";
            }
        }
        return "redirect:/dashboard/categories";
    }
}
