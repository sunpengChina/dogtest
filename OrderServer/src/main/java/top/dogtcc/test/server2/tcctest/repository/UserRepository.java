package top.dogtcc.test.server2.tcctest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import top.dogtcc.database.spring.jpa.DogJpaRepository;
import top.dogtcc.test.server2.dao.User;

@Component
public   class UserRepository extends DogJpaRepository<User, UserKey> {

    @Autowired
    IUserRepository repository;

    @Override
    public JpaRepository<User, UserKey> repository() {
        return repository;
    }
}
