package top.dogtcc.test.server1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import top.dogtcc.test.server1.dao.Good;
import top.dogtcc.test.server1.dao.TestEntry;

@FeignClient(name = "goodsserver",url = "127.0.0.1:8083")
public interface GoodServerClient {

    @RequestMapping("/insert")
    String insert(Good order) throws Exception;

    @RequestMapping("/update")
    String update(Good order) throws Exception;

    @RequestMapping("/test")
    String test(TestEntry entry) throws Exception;

    @RequestMapping("/clear")
    void clear() ;

}
