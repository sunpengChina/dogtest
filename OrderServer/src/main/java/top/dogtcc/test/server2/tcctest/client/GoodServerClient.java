package top.dogtcc.test.server2.tcctest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.dogtcc.test.server2.dao.Good;


@FeignClient(name = "goodsserver",url = "127.0.0.1:8083")
public interface GoodServerClient {

    @RequestMapping("/insert")
    String insert(Good order) throws Exception;

    @RequestMapping("/update")
    String update(Good order) throws Exception;

    @RequestMapping("/insertraw")
    String insertraw(Good order) throws Exception;

    @RequestMapping("/clear")
    void clear() ;

    @RequestMapping("/servererror")
    void error() ;

    @RequestMapping(value = "/findone",method = RequestMethod.GET)
    Boolean findone(@RequestParam("id") Integer id);

}
