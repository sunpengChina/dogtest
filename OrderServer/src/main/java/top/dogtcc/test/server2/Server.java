package top.dogtcc.test.server2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dogtcc.core.annotation.DogCallAnnotation;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.core.annotation.TccHandler;
import top.dogtcc.core.util.SpringContextUtil;
import top.dogtcc.database.core.DbTccHandler;
import top.dogtcc.test.server2.dao.Good;
import top.dogtcc.test.server2.dao.Orderdao;
import top.dogtcc.test.server2.dao.OrderdaoN;
import top.dogtcc.test.server2.tcctest.client.GoodServerClient;
import top.dogtcc.test.server2.tcctest.repository.OrderRepository;
import top.dogtcc.test.server2.tcctest.repository.OrderRepositoryN;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;

@Component
public class Server extends TccHandler {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderRepositoryN orderRepositoryN;


    Random random = new Random();

    @Autowired
    private GoodServerClient goodServerClient;

    @DogTccAnnotation
    public void insertGood(Good good)throws  Exception{

        if(random.nextBoolean()){

            /**
             * 事务中直接调用
             */
            goodServerClient.insert(good);

        }else {

            /**
             * 事务中调用本地Call
             */
            Server self = SpringContextUtil.getApplicationContext().getBean(top.dogtcc.test.server2.Server.class);

            self.insert(good);
        }
    }

    @DogCallAnnotation( TccHandlerClass = Server.class)
    public void insert(Good good)throws  Exception{

        goodServerClient.insert(good);
    }

    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao create(Orderdao orderdao,boolean error) throws OrderException{

        Orderdao ret = orderRepository.create(orderdao);

        if(error){

            Orderdao neworderdao = new Orderdao();

            neworderdao.setUserId(ret.getUserId());

            neworderdao.setNums(ret.getNums());

            neworderdao.setId(ret.getId());

            throw  new   OrderException(neworderdao);

        }

        return  ret;
    }

    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao delete(Orderdao orderdao,boolean error) throws OrderException{

        orderRepository.delete(orderdao);

        if(error){

            throw  new   OrderException(orderdao);

        }
        return  orderdao;
    }

    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao deleteById(Orderdao orderdao,boolean error) throws OrderException{

        orderRepository.deleteById(orderdao.getId());

        if(error){

            throw  new   OrderException(orderdao);

        }
        return  orderdao;
    }

    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao insert(OrderdaoN orderdao, boolean error) throws OrderException{

        Orderdao orderdao1  = new Orderdao();

        orderRepositoryN.insert(orderdao);

        if(error){

            orderdao1.setId(orderdao.getId());

            throw  new   OrderException(orderdao1);

        }

        orderdao1.setId(orderdao.getId());

        return orderdao1;
    }

    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao save(Orderdao orderdao,boolean error) throws OrderException{

        orderRepository.save(orderdao);

        if(error){

            Orderdao newO = new Orderdao();

            newO.setUserId(orderdao.getUserId());

            newO.setId(orderdao.getId());

            throw  new   OrderException(newO);

        }
        return  orderdao;
    }


    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao saveAndFlush(Orderdao orderdao,boolean error) throws OrderException{

        orderRepository.saveAndFlush(orderdao);

        if(error){

            throw  new   OrderException(orderdao);

        }
        return  orderdao;
    }

    @DogTccAnnotation
    @DogCallAnnotation(TccHandlerClass = DbTccHandler.class)
    public Orderdao saveAll(Orderdao orderdao,boolean error) throws OrderException{

        List<Orderdao> orderdaos = new ArrayList<>();

        orderdaos.add(orderdao);

        orderRepository.saveAll(orderdaos);

        if(error){

            throw  new   OrderException(orderdao);

        }
        return  orderdao;
    }


}
