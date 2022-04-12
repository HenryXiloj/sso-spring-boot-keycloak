package com.henry.ssospringbootkeycloak.controller;

import com.henry.ssospringbootkeycloak.model.Customer;
import com.henry.ssospringbootkeycloak.repository.CustomerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
public class WebController {

    private final CustomerDAO customerDAO;

    public WebController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping(path = "/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping(path = "/home")
    public String home(Model model) {
        model.addAttribute("date", new Date());
        return "home";
    }

    @GetMapping(path = "/info")
    public String info(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("birthday", -1);
        return "user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        addCustomers();
        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public void addCustomers() {

        Customer customer1 = new Customer();
        customer1.setAddress("123");
        customer1.setName("pharmaceutical Industries");
        customer1.setServiceRendered("Important services");
        customerDAO.save(customer1);

        Customer customer2 = new Customer();
        customer2.setAddress("1234");
        customer2.setName("technologies Industries");
        customer2.setServiceRendered("Important services");
        customerDAO.save(customer2);


    }
}
