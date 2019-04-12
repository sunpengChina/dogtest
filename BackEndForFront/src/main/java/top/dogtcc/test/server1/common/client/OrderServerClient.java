package top.dogtcc.test.server1.common.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.dogtcc.test.server1.dao.OrderDao;


@FeignClient(name = "orderserver",url = "127.0.0.1:8082")
public interface OrderServerClient {


    @RequestMapping("/insert")
    OrderDao insert(OrderDao order) throws Exception;

    @RequestMapping("/clear")
    void clear() ;

    @RequestMapping("/servererror")
    void error() ;

    @RequestMapping(value = "/findone",method = RequestMethod.GET)
    Boolean findone(@RequestParam("id") Integer id);

    @RequestMapping("/insertchain")
    OrderDao insertchain(OrderDao order) throws Exception;
}
