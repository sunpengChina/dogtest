package top.dogtcc.test.server1;


import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/insertorder/{userid}/{goodid}/{num}")
    @DogTccAnnotation(Name = "insertorder")
    public String insertorder(@PathVariable String userid, @PathVariable int goodid, @PathVariable int num) throws Exception {

        OrderDao orderDao = new OrderDao();
        orderDao.setNums(num);
        orderDao.setUserId(userid);
        orderDao.setGoodId(goodid);


        orderServerClient.insert(orderDao);

        return  orderDao.toString();

    }

    @RequestMapping("/insertgood/{id}/{name}/{mount}")
    @DogTccAnnotation(Name = "insertgood")
    public String insertgood(@PathVariable int id,@PathVariable String name, @PathVariable int mount) throws Exception {

        Good good = new Good();
        good.setId(id);
        good.setName(name);
        good.setAmount(mount);

        goodServerClient.insert(good);

        return  good.toString();
    }



    @RequestMapping("/buy/{userid}/{goodid}/{num}")
    @DogTccAnnotation(Name = "insertorder")
    public String buy(@PathVariable String userid, @PathVariable int goodid, @PathVariable int num) throws Exception {

        OrderDao orderDao = new OrderDao();
        orderDao.setNums(num);
        orderDao.setUserId(userid);
        orderDao.setGoodId(goodid);

        orderServerClient.insert(orderDao);


        Good good = new Good();
        good.setName("testGood");
        good.setAmount(num);
        good.setId(goodid);

       goodServerClient.update(good);

        return  "OK";

    }






}