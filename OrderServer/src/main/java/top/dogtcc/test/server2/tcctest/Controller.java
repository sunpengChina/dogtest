package top.dogtcc.test.server2.tcctest;

import org.springframework.web.bind.annotation.*;
import top.dogtcc.core.annotation.DogCallAnnotation;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.core.annotation.TccHandler;
import top.dogtcc.database.core.DbTccHandler;
import org.springframework.beans.factory.annotation.Autowired;
import top.dogtcc.test.server2.OrderException;
import top.dogtcc.test.server2.Server;
import top.dogtcc.test.server2.dao.Good;
import top.dogtcc.test.server2.dao.Orderdao;
import top.dogtcc.test.server2.dao.OrderdaoN;
import top.dogtcc.test.server2.tcctest.client.GoodServerClient;
import top.dogtcc.test.server2.tcctest.repository.OrderRepository;
import top.dogtcc.test.server2.tcctest.repository.OrderRepositoryN;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@RestController
public class Controller extends TccHandler {


    Random random = new Random();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderRepositoryN orderRepositoryN;

    @Autowired
    private Server server;

    @RequestMapping("/hello")
    public String greeting() {
        return "hello/server2";
    }


    @Autowired
    private GoodServerClient goodServerClient;

    @RequestMapping("/insert")
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao insert(@RequestBody Orderdao order) {

        orderRepository.create(order);

        return order;
    }


    @RequestMapping("/insertchain")
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao insertchain(@RequestBody Orderdao order) {

        orderRepository.create(order);

        Good good = new Good();

        good.setId(order.getId());

        good.setName(UUID.randomUUID().toString());

        good.setAmount(0);

        try {

            if (random.nextBoolean()) {

                if (random.nextBoolean()) {

                    /**
                     * 嵌套事务
                     */
                    server.insertGood(good);


                } else {

                    /**
                     * 嵌套Call
                     */
                    server.insert(good);
                }

            } else {

                /**
                 * 直接远程调用
                 */
                goodServerClient.insert(good);

            }


        } catch (Exception e) {

        }

        return order;
    }


    @RequestMapping("/clear")
    public void clear() {

        orderRepository.deleteAll();

    }

    @RequestMapping("/servererror")
    @DogCallAnnotation(TccHandlerClass = Controller.class)
    public void error() {

        int i = 10 / 0;

    }

    @RequestMapping(value = "/findone", method = RequestMethod.GET)
    public Boolean findone(@RequestParam("id") Integer id) {

        return orderRepository.findById(id).isPresent();

    }

    @RequestMapping("/test")
    public String test() {

        /**
         * 测试 create
         */

        orderRepository.deleteAll();

        Orderdao orderdao = new Orderdao();

        orderdao.setUserId(UUID.randomUUID().toString());

        orderRepository.deleteAll();

        try {

            server.create(orderdao, true);


        } catch (OrderException e) {
            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            if (orderRepository.findAll().size() != 0) {

                return "error";

            }

        }


        /**
         * 测试 delete
         */

        orderRepository.deleteAll();

        try {

            Orderdao orderdao1 = new Orderdao();

            orderdao1.setUserId(UUID.randomUUID().toString());

            orderRepository.insert(orderdao1);

            server.delete(orderdao1, true);


        } catch (OrderException e) {

            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            if (orderRepository.findAll().size() == 0) {

                return "error";

            }

        }


        /**
         * 测试 deleteById
         */


        orderRepository.deleteAll();

        try {

            Orderdao orderdao1 = new Orderdao();

            orderdao1.setUserId(UUID.randomUUID().toString());

            orderRepository.insert(orderdao1);

            server.deleteById(orderdao1, true);


        } catch (OrderException e) {

            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            if (orderRepository.findAll().size() == 0) {

                return "error";

            }

        }


        /**
         * 测试insert
         */
//
        orderRepositoryN.deleteAll();

        try {

            OrderdaoN orderdao1 = new OrderdaoN();

            orderdao1.setUserId(UUID.randomUUID().toString());

            server.insert(orderdao1, true);


        } catch (OrderException e) {

            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            if (orderRepository.findAll().size() == 0) {

                return "error";

            }

        }


        /**
         * 测试save
         */

        orderRepository.deleteAll();

        final String uuid1 = UUID.randomUUID().toString();

        final Orderdao orderdao2 = new Orderdao();

        try {

            orderdao2.setUserId(uuid1);

            orderRepository.create(orderdao2);


            Orderdao neworderdao = new Orderdao();

            neworderdao.setUserId(uuid1);

            neworderdao.setId(orderdao2.getId());

            neworderdao.setUserId(UUID.randomUUID().toString());

           server.save(neworderdao,true);


        } catch (OrderException e) {

            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            Callable<Boolean> resultcall = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if (orderRepository.findById(orderdao2.getId()).get().getUserId().equals(uuid1)) {


                        return true;

                    }

                    return false;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(resultcall);

            new Thread(resultfuture).start();

            try {

                if (!resultfuture.get()) {

                    return "error";
                }

            } catch (Exception k) {

            }

        }


        /**
         *   saveAndFlush
         */

        orderRepository.deleteAll();

        final String uuid3 = UUID.randomUUID().toString();

        final Orderdao orderdao3 = new Orderdao();

        try {

            orderdao3.setUserId(uuid3);

            orderRepository.create(orderdao3);

            Orderdao neworderdao = new Orderdao();

            neworderdao.setUserId(uuid3);

            neworderdao.setId(orderdao3.getId());

            neworderdao.setUserId(UUID.randomUUID().toString());

//
             server.saveAndFlush(neworderdao,true);

   //         server.saveAll(neworderdao, true);


        } catch (OrderException e) {

            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            Callable<Boolean> resultcall = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if (orderRepository.findById(orderdao3.getId()).get().getUserId().equals(uuid3)) {


                        return true;

                    }

                    return false;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(resultcall);

            new Thread(resultfuture).start();

            try {

                if (!resultfuture.get()) {

                    return "error";
                }

            } catch (Exception k) {

            }

        }


        /**
         * 测试saveAll
         */

        orderRepository.deleteAll();

        final String uuid4 = UUID.randomUUID().toString();

        final Orderdao orderdao4 = new Orderdao();

        try {

            orderdao4.setUserId(uuid4);

            orderRepository.create(orderdao4);

            Orderdao neworderdao = new Orderdao();

            neworderdao.setUserId(uuid4);

            neworderdao.setId(orderdao4.getId());

            neworderdao.setUserId(UUID.randomUUID().toString());

            server.saveAll(neworderdao, true);


        } catch (OrderException e) {

            try {

                Thread.sleep(1000);

            } catch (Exception f) {

            }

            Callable<Boolean> resultcall = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if (orderRepository.findById(orderdao4.getId()).get().getUserId().equals(uuid4)) {


                        return true;

                    }

                    return false;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(resultcall);

            new Thread(resultfuture).start();

            try {

                if (!resultfuture.get()) {

                    return "error";
                }

            } catch (Exception k) {

            }

        }

        return "OK";
    }

}