package com.example.bookstore2.demo.Controller;

import com.example.bookstore2.demo.Entity.Book;
import com.example.bookstore2.demo.Entity.Customer;
import com.example.bookstore2.demo.Repository.BookRepository;
import com.example.bookstore2.demo.Repository.CustomerRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/new")
    public ModelAndView customerForm() {
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("CustomerRegisterForm");

        return modelAndView;
    }

    @PostMapping("/customer/new")
    public String customerSubmit(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "resultCustomer";
    }

    @GetMapping("customer/login")
    public ModelAndView customerLogin() {
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("CustomerLoginForm");
        return modelAndView;
    }

    @PostMapping("/customer/login")
    public String customerLogin(@ModelAttribute Customer customer) {
        Customer newCustomer = customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
        if(newCustomer != null)
            return "Welcome";
        else
            return "CustomerLoginForm";
    }
}
