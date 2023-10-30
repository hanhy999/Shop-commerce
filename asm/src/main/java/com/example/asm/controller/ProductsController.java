package com.example.asm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.asm.domain.Category;
import com.example.asm.domain.Products;
import com.example.asm.dto.ProductDto;
import com.example.asm.dto.ProductMulDto;
import com.example.asm.dto.QuantityDto;
import com.example.asm.dto.SearchBox;
import com.example.asm.service.CategoryService;
import com.example.asm.service.FileService;
import com.example.asm.service.ProductService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("dashboard")
public class ProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("products")
    public String products(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/custom/products";
    }

    @ModelAttribute("categorys")
    public List<Category> list() {
        List<Category> categories = categoryService.findAll();
        return categories;
    }

    @GetMapping("products/create")
    public String createProducts(Model model) {

        ProductDto product = new ProductDto();
        model.addAttribute("products", product);
        // List<Categories> categories = categoryService.findAll();
        // model.addAttribute("categorys", categories);
        return "admin/form/createProducts";

    }

    @PostMapping("products/create")
    public String createProduct(Model model, @Valid @ModelAttribute("products") ProductMulDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR  " + result);
            return "admin/form/createProducts";
        }
        String fileName = StringUtils.cleanPath(dto.getImage().getOriginalFilename());

        Category category = new Category();
        category.setCategoryId(dto.getCategory());

        Products copy = new Products();
        BeanUtils.copyProperties(dto, copy);
        copy.setCategory(category);
        copy.setImage(fileName);

        // copy.setActivated(dto.get);

        productService.save(copy);
        try {
            FileService.saveFile("src/main/resources/static/saveImages", fileName, dto.getImage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/dashboard/products";

    }

    // edit products
    @GetMapping("products/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {

        if (id != 0) {
            Optional<Products> prd = productService.findById(id);
            if (prd.isPresent()) {
                model.addAttribute("products", prd.get());
                // model.addAttribute("categorys", categoryService.findAll());
                return "admin/form/editProducts";
            }
        }
        return "redirect:/dashboard/editProducts";
    }

    // edit products post
    @PostMapping("products/edit")
    public String update(Model model, @Valid @ModelAttribute("products") ProductMulDto dto, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR  " + result);
            return "admin/form/editProducts";
        }

        String fileName = StringUtils.cleanPath(dto.getImage().getOriginalFilename());

        Category category = new Category();
        category.setCategoryId(dto.getCategory());

        Products copy = new Products();
        BeanUtils.copyProperties(dto, copy);
        copy.setCategory(category);
        copy.setImage(fileName);

        System.err.println("idddddddddddddddddđ  : " + copy.getCategory());
        System.err.println("idddddddddddddddddđ  : " + dto.getCategory());

        System.err.println("copy product : " + copy);
        productService.save(copy);
        try {
            FileService.saveFile("src/main/resources/static/saveImages", fileName, dto.getImage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        redirectAttributes.addFlashAttribute("success", "Update success");
        return "redirect:/dashboard/products";
    }

    @GetMapping("products/delete/{id}")
    public String delete(
            @PathVariable("id") int id,
            RedirectAttributes redirAttrs) {
        if (id != 0) {
            Optional<Products> detail = productService.findById(id);
            if (detail.isPresent()) {
                productService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/dashboard/products";
            }
        }
        return "redirect:/dashboard/products";
    }

    @GetMapping("products/detail/{id}")
    public String detail(Model model,
            @PathVariable("id") int id) {

        if (id != 0) {
            Products detail = productService.getById(id);
            model.addAttribute("detail", detail);
            QuantityDto quantityDto = new QuantityDto();

            model.addAttribute("getQuantity", quantityDto);
            return "product-single";
        }
        return "redirect:dashboard/products"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("products/findCate/{idCat}")
    public String findCate(Model model,
            @PathVariable("idCat") int idCat) {

        if (idCat != 0) {
            List<ProductDto> detail = productService.getListCategory(idCat);
            System.err.println("list cat 123" + detail);
            model.addAttribute("catDetails", detail);
            return "shopCategory";
        }
        return "redirect:dashboard/products"; // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("products/search")
    public String search(Model model, @ModelAttribute("searchBox") SearchBox searchText) {

        System.err.println("Name search " + searchText.getSearchText());

        String search = "%" + searchText.getSearchText() + "%";

        if (searchText != null) {
            List<ProductDto> listSearch = productService.search(search);
            model.addAttribute("listSearch", listSearch);
            model.addAttribute("size", listSearch.size());
            model.addAttribute("nameSearch", "\"" + searchText.getSearchText() + "\"");
            return "shopSearch";
        }
        return "redirect:dashboard/products"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("products/sortAsc")
    public String sortAsc(Model model) {
        List<ProductDto> listSearch = productService.getListSortAsc();
        model.addAttribute("listSearch", listSearch);
        return "shopSort";
    }

    @GetMapping("products/sortDesc")
    public String sortDesc(Model model) {
        List<ProductDto> listSearch = productService.getListSortDesc();
        model.addAttribute("listSearch", listSearch);
        return "shopSort";
    }

    @GetMapping("products/sortName")
    public String sortName(Model model) {
        List<ProductDto> listSearch = productService.getListSortName();
        model.addAttribute("listSearch", listSearch);
        return "shopSort";
    }

}
