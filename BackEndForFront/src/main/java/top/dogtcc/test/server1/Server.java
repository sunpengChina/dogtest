package top.dogtcc.test.server1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.test.server1.client.GoodServerClient;
import top.dogtcc.test.server1.client.OrderServerClient;
import top.dogtcc.test.server1.dao.Good;
import top.dogtcc.test.server1.dao.OrderDao;

@Component
public class Server {

    @Autowired
    OrderServerClient orderServerClient;

    @Autowired
    GoodServerClient goodServerClient;

    @DogTccAnnotation(Name ="buyTest")
    public void buyTest(OrderDao orderDao, Good good) throws Exception{

        /**
         * 插入的是mysql
         */
        OrderDao ret = orderServerClient.insert(orderDao);

        /**
         * 插入mongo
         */
        goodServerClient.insert(good);

    }

}
