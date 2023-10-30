package com.example.asm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.asm.domain.Account;
import com.example.asm.domain.Order;
import com.example.asm.domain.OrderDetail;
import com.example.asm.domain.OrderDetailKey;
import com.example.asm.domain.Products;
import com.example.asm.dto.OrderDetailDto;
import com.example.asm.dto.OrderDto;
import com.example.asm.service.AccountService;
import com.example.asm.service.OrderDetailService;
import com.example.asm.service.OrderService;

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
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

    @Autowired
    OrderDetailService orderDetailService;

    @ModelAttribute("listAccounts")
    public List<Account> listAcc(Model model) {
        List<Account> accounts = accountService.findAll();
        return accounts;
    }

    @GetMapping("orders")
    public String categories(Model model) {

        List<Order> order = orderService.findAll();
        // System.err.println("list order "+order);
        model.addAttribute("orders", order);

        return "admin/custom/orders";
    }

    @GetMapping("orders/create")
    public String createCategories(Model model) {
        System.out.println("go here create");

        Order orders = new Order();
        model.addAttribute("orders", orders);
        List<Account> accounts = accountService.findAll();
        model.addAttribute("listAccounts", accounts);
        return "admin/form/createOrders";

    }

    // create order
    @PostMapping("orders/create")
    public String createCategoriesPost(Model model, @Valid @ModelAttribute("orders") OrderDto dto,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // List<Account> accounts = accountService.findAll();
            // model.addAttribute("listAccounts", accounts);
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "admin/form/createOrders";
        }

        Order copy = new Order();

        BeanUtils.copyProperties(dto, copy);
        System.out.println("copy: " + copy);
        orderService.save(copy);
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/dashboard/orders";

    }

    @GetMapping("orders/detail/{idOrder}")
    public String detail(Model model,
            @PathVariable("idOrder") int id) {

        if (id != 0) {

            List<OrderDetailDto> detail = orderDetailService.getListOrderDetail(id);
            model.addAttribute("orderDetail", detail);

            System.err.println("detail: " + detail);

            return "admin/custom/orderDetail";
        }
        return "redirect:dashboard/orders"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("orders/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {

        if (id != 0) {
            Optional<Order> prd = orderService.findById(id);
            if (prd.isPresent()) {
                model.addAttribute("orders", prd.get());
                return "admin/form/editOrder";
            }
        }
        return "redirect:/dashboard/editOrder";
    }

    @GetMapping("orders/remove/{id}")
    public String removeDetail(Model model, @PathVariable("id") int id, RedirectAttributes redirAttrs) {

        if (id != 0) {
            Optional<Order> prd = orderService.findById(id);
            if (prd.isPresent()) {
                orderService.delete(prd.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/dashboard/orders";
            }
        }
        return "redirect:/dashboard/editOrder";
    }

    @PostMapping("orders/edit")
    public String update(Model model, @Valid @ModelAttribute("orders") OrderDto dto,
            RedirectAttributes redirectAttributes,
            BindingResult result) {
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.err.println("CO EROR  " + result);
            return "admin/form/editOrder";
        }

        Account account = new Account();
        account.setUsername(dto.getAccount());
        Order order = new Order();

        BeanUtils.copyProperties(dto, order);

        order.setAccount(account);

        System.err.println("update order: " + dto.getAccount());
        System.err.println("order : " + order.getAccount());
        System.err.println("ỏder : " + order);

        orderService.save(order);
        redirectAttributes.addFlashAttribute("success", "Edit success");
        return "redirect:/dashboard/orders"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("ordersDetail/remove/{idOrder}/{idProduct}")
    public String editOrdersDetail(Model model, @PathVariable("idOrder") int idOrder, RedirectAttributes redirAttrs,
            @PathVariable("idProduct") int idProduct) {

        if (idOrder != 0 && idProduct != 0) {
            // Order order = new Order();
            OrderDetailKey key = new OrderDetailKey(idOrder, idProduct);
            Optional<OrderDetail> prd = orderDetailService.findById(key);
            if (prd.isPresent()) {
                orderDetailService.delete(prd.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/dashboard/orders";
            }
        }
        return "redirect:/dashboard/orders";
    }

    // @PostMapping("cut/edit")
    // public String updateOrdersDetail(Model model, @Valid
    // @ModelAttribute("orderDetails") OrderDetailDto dto,
    // RedirectAttributes redirectAttributes,
    // BindingResult result) {
    // if (result.hasErrors()) {
    // // đẩy lại view và đưa ra thông báo lỗi
    // System.err.println("CO EROR " + result);
    // return "admin/form/editOrderDetail";
    // }

    // // Products products = new Products();
    // Order order = new Order();
    // // products.setId(dto.getProductId());
    // order.setId(dto.getOrderId());
    // order.setCustomerName("cccc");
    // order.setNote("note");
    // order.setPhone("phone");

    // OrderDetail orderDetail = new OrderDetail();

    // BeanUtils.copyProperties(dto, orderDetail);
    // orderDetail.setOrder(order);
    // // orderDetail.setProduct(products);

    // orderDetailService.save(orderDetail);
    // redirectAttributes.addFlashAttribute("success", "Edit succsess");
    // return "redirect:/dashboard/orders"; // Return tên của View, model sẽ tự động
    // pass vào view
    // }

}
