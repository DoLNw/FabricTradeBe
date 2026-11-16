package org.jcwang.fabrictradebe.servier;

import java.util.List;

import org.jcwang.fabrictradebe.dao.MyTestMapper;
import org.jcwang.fabrictradebe.model.TestCustomer;
import org.springframework.stereotype.Service;

@Service
public class MyTestService {
    private final MyTestMapper myTestMapper;

    public MyTestService(MyTestMapper myTestMapper) {
        this.myTestMapper = myTestMapper;
    }

    public List<TestCustomer> findAll() {
        return myTestMapper.findAll();
    }

    public TestCustomer findById(long id) {
        return myTestMapper.findById(id);
    }

    public TestCustomer create(TestCustomer customer) {
        myTestMapper.insert(customer);
        return customer;
    }

    public boolean update(long id, TestCustomer customer) {
        return myTestMapper.update(id, customer) > 0;
    }

    public boolean deleteById(long id) {
        return myTestMapper.deleteById(id) > 0;
    }
}
