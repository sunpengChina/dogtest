package top.dogtcc.test.server1.dao;


import top.dogtcc.database.core.annotation.DogTable;
import top.dogtcc.database.core.annotation.QueryArg;


@DogTable(tableName = "Good",dbName = "dbname")
public class Good {

    @QueryArg(argName = "ID")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private String name;

    private int amount;

}
