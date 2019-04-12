package top.dogtcc.test.server2.tcctest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import top.dogtcc.database.spring.jpa.DogJpaRepository;
import top.dogtcc.test.server2.dao.Orderdao;
import top.dogtcc.test.server2.dao.OrderdaoN;

@Component
public  class OrderRepositoryN extends DogJpaRepository<OrderdaoN, Integer> {

    @Autowired
    IOrderNRepository repository;


    @Override
     public JpaRepository<OrderdaoN, Integer> repository(){
         return      repository;
     }

}


