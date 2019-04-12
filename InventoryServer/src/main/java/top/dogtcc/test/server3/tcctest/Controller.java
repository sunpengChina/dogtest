package top.dogtcc.test.server3.tcctest;

import org.springframework.web.bind.annotation.*;
import top.dogtcc.core.annotation.DogCallAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.core.annotation.TccHandler;
import top.dogtcc.database.core.DbTccHandler;
import top.dogtcc.database.core.annotation.DogDb;
import top.dogtcc.database.core.annotation.OperationType;
import top.dogtcc.database.core.annotation.QueryArg;
import top.dogtcc.test.server3.Server;
import top.dogtcc.test.server3.dao.Good;
import top.dogtcc.test.server3.tcctest.repository.GoodRepository;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


@RestController
public class Controller extends TccHandler {

    @Autowired
    GoodRepository repository;

    @Autowired
    Server server;

    @RequestMapping("/insert")
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public String insert(@RequestBody Good good) {

        repository.insert(good);

        return "OK";
    }



    @RequestMapping("/update")
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public String update(@RequestBody Good good) {

        repository.save(good);

        return "OK";
    }


    @RequestMapping("/insertraw")
    public String insertraw(@RequestBody Good good) {

        repository.insert(good);
        return "OK";
    }


    @RequestMapping("/clear")
    public void clear() {

        repository.deleteAll();

    }

    @RequestMapping("/servererror")
    @DogCallAnnotation(TccHandlerClass = Controller.class)
    public void error()  {

        int i = 10/0;

    }

    @RequestMapping(value = "/findone",method = RequestMethod.GET)
    public Boolean findone(@RequestParam("id") Integer id){

         return repository.findById(id).isPresent();

    }

    @RequestMapping("/test")
    public  String test() throws Exception{


        /**
         * 测试insert
         */
        repository.deleteAll();

        Good good = new Good();
        good.setId(0);

        try {

            server.TestInsert(good);

        }catch (Exception e){

            Thread.sleep(1000);

            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if(repository.findById(0).isPresent()){

                        return  false;

                    }
                    return  true;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(callable);

            new Thread(resultfuture).start();

            if(!resultfuture.get()){

                return  "error";
            }



        }

        /**
         * 测试save
         */
        repository.deleteAll();

        Good good1 = new Good();
        good1.setId(1);
        String str1 = UUID.randomUUID().toString();
        good1.setName(str1);

        repository.insert(good1);

        try {

            good1.setName(UUID.randomUUID().toString());

            server.TestSave(good1);

        }catch (Exception e){

            Thread.sleep(1000);

            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if(!repository.findById(1).get().getName().equals(str1)){

                        return  false;

                    }
                    return  true;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(callable);

            new Thread(resultfuture).start();

            if(!resultfuture.get()){

                return  "error";
            }

        }


        /**
         * deleteAll
         */
        repository.deleteAll();

        Good good2 = new Good();
        good2.setId(2);

        repository.insert(good2);

        try {

            server.TestdeleteAll(good2);

        }catch (Exception e){

            Thread.sleep(1000);

            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if(!repository.findById(2).isPresent()){

                        return  false;

                    }
                    return  true;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(callable);

            new Thread(resultfuture).start();

            if(!resultfuture.get()){

                return  "error";
            }

        }




        /**
         * 测试saveAll
         */
        repository.deleteAll();

        Good good3 = new Good();
        good3.setId(3);
        String str3 = UUID.randomUUID().toString();
        good3.setName(str3);

        repository.insert(good3);

        try {

            good3.setName(UUID.randomUUID().toString());

            server.TestSaveALL(good3);

        }catch (Exception e){

            Thread.sleep(1000);

            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if(!repository.findById(3).get().getName().equals(str3)){

                        return  false;

                    }
                    return  true;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(callable);

            new Thread(resultfuture).start();

            if(!resultfuture.get()){

                return  "error";
            }

        }




        /**
         * deleteid
         */
        repository.deleteAll();

        Good good4 = new Good();
        good4.setId(4);

        repository.insert(good4);

        try {

            server.TestDeletByid(good4);

        }catch (Exception e){

            Thread.sleep(1000);

            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if(!repository.findById(4).isPresent()){

                        return  false;

                    }
                    return  true;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(callable);

            new Thread(resultfuture).start();

            if(!resultfuture.get()){

                return  "error";
            }

        }




        /**
         * delete
         */
        repository.deleteAll();

        Good good5 = new Good();
        good5.setId(5);

        repository.insert(good5);

        try {

            server.TestDelete(good5);

        }catch (Exception e){

            Thread.sleep(1000);

            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    if(!repository.findById(5).isPresent()){

                        return  false;

                    }
                    return  true;
                }
            };

            FutureTask<Boolean> resultfuture = new FutureTask<>(callable);

            new Thread(resultfuture).start();

            if(!resultfuture.get()){

                return  "error";
            }

        }



        return  "OK";
    }

}