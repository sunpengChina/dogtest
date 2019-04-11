package top.dogtcc.test.server3.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import top.dogtcc.database.core.annotation.DogTable;
import top.dogtcc.database.core.annotation.QueryArg;

@Data
@DogTable(tableName = "Good",dbName = "dbname")
public class Good {

    @QueryArg(argName = "ID")
    private int id;

    private String name;

    private int amount;

}
