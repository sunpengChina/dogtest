package top.dogtcc.test.server3.tcctest.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import top.dogtcc.test.server3.dao.Good;

public interface IGoodRepository extends MongoRepository<Good, Integer> {
}
