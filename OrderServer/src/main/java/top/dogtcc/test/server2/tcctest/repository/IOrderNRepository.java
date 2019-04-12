package top.dogtcc.test.server2.tcctest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.dogtcc.test.server2.dao.Orderdao;
import top.dogtcc.test.server2.dao.OrderdaoN;

public interface IOrderNRepository extends JpaRepository<OrderdaoN, Integer> {

}

