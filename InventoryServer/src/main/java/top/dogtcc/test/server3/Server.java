package top.dogtcc.test.server3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dogtcc.core.annotation.DogCallAnnotation;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.core.annotation.TccHandler;
import top.dogtcc.database.core.DbTccHandler;
import top.dogtcc.test.server3.dao.Good;
import top.dogtcc.test.server3.tcctest.repository.GoodRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class Server {


    @Autowired
    GoodRepository repository;


    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    @DogTccAnnotation
    public void TestInsert( Good good){

        repository.insert(good);

        int i = 10/0;

    }


    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    @DogTccAnnotation
    public void TestSave(Good good){

        repository.save(good);

        int i = 10/0;

    }

    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    @DogTccAnnotation
    public void TestdeleteAll(Good good){

        List<Good> goodList = new ArrayList<>();
        goodList.add(good);

        repository.deleteAll(goodList);

        int i = 10/0;

    }


    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    @DogTccAnnotation
    public void TestSaveALL(Good good){

        List<Good> goodList = new ArrayList<>();
        goodList.add(good);

        repository.saveAll(goodList);

        int i = 10/0;

    }

    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    @DogTccAnnotation
    public void TestDeletByid(Good good){

        repository.deleteById(good.getId());

        int i = 10/0;

    }


    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    @DogTccAnnotation
    public void TestDelete(Good good){

        repository.delete(good);

        int i = 10/0;

    }


}
