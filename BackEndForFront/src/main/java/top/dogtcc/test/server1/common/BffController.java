package top.dogtcc.test.server1.common;


import org.springframework.beans.factory.annotation.Autowired;
import top.dogtcc.core.annotation.DogTccAnnotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dogtcc.test.server1.dao.Good;
import top.dogtcc.test.server1.dao.OrderDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
public class BffController {


    Random random = new Random();


    @Autowired
    Server server;

    @RequestMapping("/test2")
    public String test2() throws Exception{

        server.clear();

        String randomString = UUID.randomUUID().toString();


        OrderDao orderDao = new OrderDao();
        orderDao.setUserId(randomString);
        orderDao.setGoodId(0);

        Good good = new Good();
        good.setId(0);
        good.setAmount(0);
        good.setName(randomString);

        server.insert(new OrderDao(),new Good());

        return  "OK";
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/test1")
    public String test1() throws Exception{

        server.clear();

        for(Integer i =0 ;i<10;i++){

            String randomString = UUID.randomUUID().toString();

            OrderDao orderDao = new OrderDao();
            orderDao.setUserId(randomString);
            orderDao.setGoodId(i);

            Good good = new Good();
            good.setId(i);
            good.setAmount(i);
            good.setName(randomString);


            Integer orderid =null ;

            try {

                orderid = server.insert(orderDao,good,random.nextBoolean()).getKey();

            }catch (Exception e){

                if( e instanceof  CustomerException){

                    CustomerException customerException = (CustomerException)e;

                    /**
                     * 需要时间回滚
                     */
                    Thread.sleep(1000);


                    if(server.getOrderServerClient().findone(customerException.getOrderid())){

                        throw  new Exception();

                    }

                    if(server.getGoodServerClient().findone(customerException.getOrderid())){

                        throw  new Exception();

                    }


                    if(server.getGoodServerClient().findone(customerException.getGoodid())){


                        throw  new Exception();

                    }

                    continue;
                }

            }

            if(!server.getOrderServerClient().findone(orderid)){

                throw  new Exception();

            }

            if(!server.getGoodServerClient().findone(orderid)){

                throw  new Exception();

            }

            if(!server.getGoodServerClient().findone(i)){

                throw  new Exception();

            }

        }


        return  "OK";
    }



}