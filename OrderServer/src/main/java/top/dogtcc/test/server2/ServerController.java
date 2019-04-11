package top.dogtcc.test.server2;

import top.dogtcc.core.annotation.DogCallAnnotation;
import top.dogtcc.database.core.DbTccHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dogtcc.test.server2.dao.Orderdao;
import top.dogtcc.test.server2.repository.IOrderRepository;
import top.dogtcc.test.server2.repository.OrderRepository;

@RestController
public class ServerController {

    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping("/hello")
    public String greeting() {
        return "hello/server2";
    }


    @RequestMapping("/insert")
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public String insert(@RequestBody Orderdao order) {

        Orderdao ret = orderRepository.create(order);

       // int i = 10/0;

        return String.valueOf(ret.getId());
    }


}