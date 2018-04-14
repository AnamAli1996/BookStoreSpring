package com.example.bookstore2.demo.Controller;
import com.example.bookstore2.demo.DAO.SearchOptionsDAO;
import com.example.bookstore2.demo.Entity.Role;
import com.example.bookstore2.demo.Entity.SearchOptions;
import com.example.bookstore2.demo.Entity.SearchPicked;
import com.example.bookstore2.demo.Entity.User;
import com.example.bookstore2.demo.Repository.UserRepository;
import com.example.bookstore2.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    SearchOptionsDAO searchOptionsDAO;
    @GetMapping("/customer/new")
    public ModelAndView customerForm() {
        ModelAndView modelAndView = new ModelAndView();
        User customer = new User();
        modelAndView.addObject("customer", customer);

        modelAndView.setViewName("CustomerRegisterForm");

        return modelAndView;
    }

    @PostMapping("/customer/new")
    public String customerSubmit(@ModelAttribute User customer) {
        Role role = new Role();
        role.setRole(customer.getRole());
        roleRepository.save(role);
        userRepository.save(customer);
        return "CustomerLoginForm";
    }

    @GetMapping("customer/login")
    public ModelAndView customerLogin() {
        ModelAndView modelAndView = new ModelAndView();
        User customer = new User();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("CustomerLoginForm");
        return modelAndView;
    }

    @PostMapping("/customer/login")
    public String customerLogin(@ModelAttribute User customer, HttpSession session) {
        session.setAttribute("loggedInUser", customer.getEmail());
        User newCustomer = userRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
        List<SearchOptions> searchOptions = SearchOptionsDAO.getOptions();
        ModelAndView modelAndView = new ModelAndView();
        SearchPicked searchPicked = new SearchPicked();
        modelAndView.addObject("searchPicked", searchPicked);
        modelAndView.addObject("searchList", searchOptions);
        modelAndView.addObject("customer", customer);
        if(newCustomer != null) {
            if (newCustomer.getRole().equalsIgnoreCase("CUSTOMER")) {
                return "Welcome";
            } else {
                return "AdminHome";
            }
        } else
            return "CustomerLoginForm";
    }
}