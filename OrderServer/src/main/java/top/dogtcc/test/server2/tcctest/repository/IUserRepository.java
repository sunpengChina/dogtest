package top.dogtcc.test.server2.tcctest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import top.dogtcc.test.server2.dao.User;

public interface IUserRepository extends JpaRepository<User, UserKey> {


}

