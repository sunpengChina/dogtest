package top.dogtcc.test.server1.dao;



import top.dogtcc.database.core.annotation.DogTable;
import top.dogtcc.database.core.annotation.QueryArg;


import java.io.Serializable;

public class OrderDao implements Serializable {


    private int id;

    private String userId;

    private int goodId;


    private int nums;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }


}
