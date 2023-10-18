package com.basic2.controller;

import com.basic2.dto.CustomerReqDTO;
import com.basic2.dto.CustomerReqForm;
import com.basic2.dto.CustomerResDTO;
import com.basic2.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customerspage")
@RequiredArgsConstructor
public class CustomerController {

        private final CustomerService customerService;

@GetMapping("/first")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }

//leaf는 테스트용임

    @GetMapping("/index")
    public ModelAndView index() {
        List<CustomerResDTO> customerResDTOList = customerService.getCustomers();
        return new ModelAndView("index","customers",customerResDTOList); // 타임리프의인덱스라는html파일로보낸다
    }

    @GetMapping("/signup")
    public String showSignUpForm(CustomerReqDTO customer) { // add-customer.html로갈때 Cus~~매개변수를 넘겨라
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@Valid CustomerReqDTO customer, BindingResult result, Model model) {
        //입력항목 검증 오류가 발생했나요?
        if (result.hasErrors()) {
            return "add-customer";
        }
        //등록 요청
        customerService.saveCustomer(customer);
//        model.addAttribute("users", userService.getUsers());
//        return "index";
        return "redirect:/customerspage/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        CustomerResDTO customerResDTO = customerService.getCustomerById(id);
        model.addAttribute("customer",customerResDTO);
        return "update-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, @Valid CustomerReqForm customer,
                             BindingResult result, Model model) { //id를써야해서 폼을씀
        if (result.hasErrors()) {
            System.out.println(">>> hasErros customer "  + customer);
            model.addAttribute("customer",customer);
//            user.setId(id);
            return "update-customer";
//        return "redirect:/customerspage/edit/id(id=${customer.id})";
        }
        customerService.updateCustomerForm(customer);

        return "redirect:/customerspage/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customerspage/index";
    }

}
