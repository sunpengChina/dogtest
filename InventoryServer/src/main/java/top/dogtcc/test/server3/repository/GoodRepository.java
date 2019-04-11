package top.dogtcc.test.server3.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import top.dogtcc.database.spring.mongo.DogMongoRepository;
import top.dogtcc.test.server3.dao.Good;

@Component
public class GoodRepository extends DogMongoRepository<Good, Integer> {

    @Autowired
    IGoodRepository repository;

    @Override
    public MongoRepository<Good, Integer> repository() {
        return repository;
    }
}
