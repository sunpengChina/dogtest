package top.dogtcc.test.server1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dogtcc.core.annotation.DogTccAnnotation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dogtcc.test.server1.client.GoodServerClient;
import top.dogtcc.test.server1.client.OrderServerClient;
import top.dogtcc.test.server1.dao.Good;
import top.dogtcc.test.server1.dao.OrderDao;


@RestController
public class BffController {

    @Autowired
    OrderServerClient orderServerClient;

    @Autowired
    GoodServerClient goodServerClient;

    @Autowired
    Server server;



    @RequestMapping("/user")
    @DogTccAnnotation(Name ="user")
    public  void user(){

        orderServerClient.user();
    }


    @RequestMapping("/buytest")
    public String buy() throws Exception {

         goodServerClient.clear();

         orderServerClient.clear();

        /**
         * 前面是个事务是成功的
         */
        for(int i = 0 ; i< 2; i++){

            Good good = new Good();
            good.setId(i);
            good.setName("");
            good.setAmount(i);

            OrderDao orderDao = new OrderDao();
            orderDao.setId(i);
            orderDao.setNums(i);
            orderDao.setGoodId(i);
            orderDao.setUserId("");

            try {

                server.buyTest(orderDao,good);

            }catch (Exception e){

                System.out.println(e);
            }

        }

        /**
         *  因为不可重入插入mongo;mysql是自生成主键，mongo不是
         */
        for(int i = 0 ; i< 2; i++){

            Good good = new Good();
            good.setId(i);
            good.setName("");
            good.setAmount(i);

            OrderDao orderDao = new OrderDao();
            orderDao.setId(i);
            orderDao.setNums(i);
            orderDao.setGoodId(i);
            orderDao.setUserId("");

            try {

                server.buyTest(orderDao,good);

            }catch (Exception e){

                System.out.println(e);
            }

        }




        return  "OK";

    }







}