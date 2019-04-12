package top.dogtcc.test.server2.dao;

import lombok.Data;
import top.dogtcc.database.core.annotation.DogTable;
import top.dogtcc.database.core.annotation.QueryArg;
import top.dogtcc.test.server2.tcctest.repository.UserKey;

import javax.persistence.*;
import java.io.Serializable;

@Data
@DogTable(tableName = "User",dbName = "dbname")
@Entity
@Table(name="User")
public class User implements Serializable {

    @EmbeddedId
    @QueryArg(argName="userkey")
    private UserKey key;

    private String something;

    public UserKey getKey() {
        return key;
    }

    public void setKey(UserKey key) {
        this.key = key;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

}
