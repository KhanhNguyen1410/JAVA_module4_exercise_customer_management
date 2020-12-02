package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.MyCustomerService;
import service.province.MyProvinceService;

import java.util.Optional;

@RestController
public class ProvinceRestController {
    @Autowired
    private MyCustomerService customerService;
    @Autowired
    private MyProvinceService provinceService;

    @RequestMapping(value = "/provinces/",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Province>> listAllCustomers(){
        Iterable<Province> provinces = provinceService.findAll();
        if (provinces== null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);// có thể để là NOT_FOUND
        }
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }
}
