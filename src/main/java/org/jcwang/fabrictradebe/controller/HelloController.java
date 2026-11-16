package org.jcwang.fabrictradebe.controller;

import java.net.URI;
import java.util.List;

import org.jcwang.fabrictradebe.model.TestCustomer;
import org.jcwang.fabrictradebe.servier.MyTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final MyTestService myTestService;

    public HelloController(MyTestService myTestService) {
        this.myTestService = myTestService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, this is Frbrid Trade company!";
    }

    @RequestMapping("/hello")
    public String welcomeHello() {
        return "Hello, this is Frbrid Trade company!";
    }

    @GetMapping("/api/test-customers")
    public List<TestCustomer> findAll() {
        return myTestService.findAll();
    }

    @GetMapping("/api/test-customers/{id}")
    public ResponseEntity<TestCustomer> findById(@PathVariable long id) {
        TestCustomer customer = myTestService.findById(id);
        return customer == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(customer);
    }

    @PostMapping("/api/test-customers")
    public ResponseEntity<TestCustomer> create(@RequestBody TestCustomer customer) {
        TestCustomer createdCustomer = myTestService.create(customer);
        return ResponseEntity.created(URI.create("/api/test-customers/" + createdCustomer.getId()))
                .body(createdCustomer);
    }

    @PutMapping("/api/test-customers/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody TestCustomer customer) {
        return myTestService.update(id, customer)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/test-customers/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        return myTestService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
