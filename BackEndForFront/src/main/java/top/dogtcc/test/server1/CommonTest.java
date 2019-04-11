package top.dogtcc.test.server1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dogtcc.core.annotation.DogTccAnnotation;
import top.dogtcc.test.server1.client.GoodServerClient;
import top.dogtcc.test.server1.client.OrderServerClient;
import top.dogtcc.test.server1.dao.OrderDao;
import top.dogtcc.test.server1.dao.TestEntry;

@RestController
public class CommonTest {

    @Autowired
    OrderServerClient orderServerClient;

    @Autowired
    GoodServerClient goodServerClient;


    @RequestMapping("/commontest")
    @DogTccAnnotation(Name = "commontest")
    public String commontest() throws Exception {


        /**
         *  简单调用： call order + good
         */
        //orderServerClient.test(TestEntry.OK());

        //goodServerClient.test(TestEntry.OK());



        /**
         *  循环调用： ( call [order -> good -> order ] + good  ) * n
         */

        for(int x = 0; x<10;x++) {

            orderServerClient.test(TestEntry.OK().next(TestEntry.OK().next(TestEntry.OK())));

            goodServerClient.test(TestEntry.OK());

        }

        return  "OK";

    }
}
