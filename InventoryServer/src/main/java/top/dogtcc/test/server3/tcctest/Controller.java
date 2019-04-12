package top.dogtcc.test.server3.tcctest;

import top.dogtcc.core.annotation.DogCallAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dogtcc.database.core.DbTccHandler;
import top.dogtcc.test.server3.tcctest.dao.Good;
import top.dogtcc.test.server3.tcctest.repository.GoodRepository;


@RestController
public class GoodsController {

    @Autowired
    GoodRepository repository;


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

}