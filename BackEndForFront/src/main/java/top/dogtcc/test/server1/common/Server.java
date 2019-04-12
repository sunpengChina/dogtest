package top.dogtcc.test.server1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.core.common.Pair;
import top.dogtcc.test.server1.common.client.GoodServerClient;
import top.dogtcc.test.server1.common.client.OrderServerClient;
import top.dogtcc.test.server1.dao.Good;
import top.dogtcc.test.server1.dao.OrderDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class Server {

    public OrderServerClient getOrderServerClient() {
        return orderServerClient;
    }

    public GoodServerClient getGoodServerClient() {
        return goodServerClient;
    }

    @Autowired
    OrderServerClient orderServerClient;

    @Autowired
    GoodServerClient goodServerClient;


    @DogTccAnnotation(Name ="normalInsert1")
    public void insert(OrderDao orderDao, Good good)throws Exception{

        orderServerClient.insert(orderDao);

        goodServerClient.insert(good);

    }


    @DogTccAnnotation(Name ="normalInsert2")
    public Pair<Integer,Integer> insert(OrderDao orderDao, Good good, boolean error) throws Exception{

           Random random = new Random();

           OrderDao orderDao1 =  orderServerClient.insertchain(orderDao);

           goodServerClient.insert(good);

            if(error) {

                try {


                    if (random.nextBoolean()) {

                        orderServerClient.error();

                    } else {

                        goodServerClient.error();
                    }

                }catch (Exception e){

                    throw  new CustomerException(orderDao1.getId(),good.getId());
                }

            }

            return  new Pair<>(orderDao1.getId(),good.getId());

    }


    public void clear() throws Exception{

        orderServerClient.clear();

        goodServerClient.clear();

    }




}
