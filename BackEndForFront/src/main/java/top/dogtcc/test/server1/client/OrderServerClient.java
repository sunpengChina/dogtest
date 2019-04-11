package top.dogtcc.test.server1.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import top.dogtcc.test.server1.dao.OrderDao;
import top.dogtcc.test.server1.dao.TestEntry;


@FeignClient(name = "orderserver",url = "127.0.0.1:8082")
public interface OrderServerClient {


    @RequestMapping("/insert")
    OrderDao insert(OrderDao order) throws Exception;

    @RequestMapping("/test")
    String test(TestEntry entry) throws Exception;

    @RequestMapping("/clear")
    void clear() ;



}
