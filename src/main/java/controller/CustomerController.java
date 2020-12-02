package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repository.MyCustomerRepository;
import service.MyCustomerService;
import service.province.MyProvinceService;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private MyCustomerService customerService;
    @Autowired
    private MyProvinceService provinceService;


    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping("/customers")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, @PageableDefault(8) Pageable pageable)   {
        Page<Customer> customers ;
        if (s.isPresent()){
            customers = customerService.findAllByFirstNameContaining(s.get(),pageable);

        }else {
            customers =  customerService.findAll(pageable);
        }
            ModelAndView modelAndView = new ModelAndView("/customer/list");
            modelAndView.addObject("customers", customers);
            return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        if (!bindingResult.hasFieldErrors()) {
            customerService.save(customer);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message", "New customer created successfully");
        }
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        if (customer != null) {
            modelAndView.addObject("customer", customer);
        } else {
            modelAndView.addObject("message", "unknown customer ");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", " customer updated successfully");
        return modelAndView;
    }
}
