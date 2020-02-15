package mateuszek.zadaniesylwiacredit.controller;

import lombok.RequiredArgsConstructor;
import mateuszek.zadaniesylwiacredit.entity.Credit;
import mateuszek.zadaniesylwiacredit.entity.Customer;
import mateuszek.zadaniesylwiacredit.entity.Product;
import mateuszek.zadaniesylwiacredit.entity.Testowanie;
import mateuszek.zadaniesylwiacredit.repository.CreditRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/credit")
@RequiredArgsConstructor
public class CreditController {
    private final RestTemplate restTemplate;
    private final CreditRepository repository;


    @GetMapping("/all")
    public List<Testowanie> getAll(){
        ResponseEntity<List<Testowanie>> listaTestow =
               restTemplate.exchange("http://localhost:8300/customer/alltests",
                       HttpMethod.GET, null, new ParameterizedTypeReference<List<Testowanie>>() {
                       });
        return listaTestow.getBody();
    }

    @PostMapping("/addtest")
    private void addTestowanie(@RequestBody  final Testowanie testowanie) {
        restTemplate.postForObject("http://localhost:8300/customer/addtest",
                testowanie, Testowanie.class);
        System.out.println("Zadziałało addtest");
    }

    @PostMapping("/createcredit")
    public int createCredit(@RequestBody final Credit credit){
        restTemplate.postForObject("http://localhost:8300/customer/addcustomer",
                credit.getCustomer(), Customer.class);
        repository.save(credit);
//        restTemplate.postForObject("http://localhost:8300XXXX/customer/addproduct",
//                credit.getProduct(), Product.class);
        return repository.getId();
    }
}
