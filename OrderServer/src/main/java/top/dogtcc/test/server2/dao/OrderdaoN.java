package top.dogtcc.test.server2.dao;


import lombok.Data;
import top.dogtcc.database.core.annotation.DogTable;
import top.dogtcc.database.core.annotation.QueryArg;

import javax.persistence.*;
import java.io.Serializable;


@Data
@DogTable(tableName = "Order",dbName = "dbname")
@Entity
public class OrderdaoN implements Serializable {

    @QueryArg(argName = "ID")
    @Column
    @Id
    private int id;

    @Column
    private String userId;

    @Column
    private int goodId;

    @Column
    private int nums;

}
