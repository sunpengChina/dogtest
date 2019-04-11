package top.dogtcc.test.server3;

import top.dogtcc.core.annotation.DogCallAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dogtcc.database.core.DbTccHandler;
import top.dogtcc.test.server3.dao.Good;
import top.dogtcc.test.server3.repository.GoodRepository;


@RestController
public class GoodsController {

    @Autowired
    GoodRepository  repository;


    @RequestMapping("/hello")
    public String greeting() {
        return "hello/server2";
    }


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

}