package com.example.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asm.domain.Category;
import com.example.asm.domain.Products;
import com.example.asm.service.CategoryService;
import com.example.asm.service.ProductService;

@Controller
@RequestMapping("")
public class HomePageController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categorys")
    public List<Category> listCategories() {
        List<Category> categories = categoryService.findAll();
        return categories;
    }

    @ModelAttribute("products")
    public List<Products> listProduct() {
        List<Products> products = productService.findAll();
        return products;
    }
    

    @GetMapping("")
    public String homePage() {
        return "index";
    }

    @GetMapping("shop")
    public String shop(Model model) {
        return "shop";
    }

    @GetMapping("checkout")
    public String checkout(Model model) {
        return "checkout";
    }
    
    @GetMapping("blog")
    public String blog(Model model) {
        return "blog";
    }
    
    @GetMapping("contact")
    public String contact(Model model) {
        return "contact";
    }
    
    @GetMapping("about")
    public String about(Model model) {
        return "about";
    }

}
