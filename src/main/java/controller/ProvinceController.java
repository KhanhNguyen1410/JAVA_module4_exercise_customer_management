package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.MyCustomerService;
import service.province.MyProvinceService;

@Controller
public class ProvinceController {
    @Autowired
    private MyProvinceService provinceService;
    @Autowired
    private MyCustomerService customerService;

    @GetMapping("/province")
    public ModelAndView listProvince(){
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("province/listProvince");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }
    @GetMapping("/province/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }
    @PostMapping("/province/create")
    public ModelAndView createProvince(Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/create", "province", new Province());
        modelAndView.addObject("message", "new province created successfully");
        return modelAndView;
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Province province = provinceService.findById(id);
        if(province == null){
            return new ModelAndView("customer/list");
        }

        Iterable<Customer> customers = customerService.findAllByProvince(province);

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
