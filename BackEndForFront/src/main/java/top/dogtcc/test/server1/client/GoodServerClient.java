package top.dogtcc.test.server1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import top.dogtcc.test.server1.dao.Good;

@FeignClient(name = "goodsserver",url = "127.0.0.1:8083")
public interface GoodServerClient {

    @RequestMapping("/insert")
    String insert(Good order) throws Exception;

    @RequestMapping("/insert")
    String update(Good order) throws Exception;
}
