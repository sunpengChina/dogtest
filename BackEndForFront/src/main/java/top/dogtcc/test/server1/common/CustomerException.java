package top.dogtcc.test.server1.common;

public class CustomerException extends  Exception{

    public Integer getOrderid() {
        return orderid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    Integer orderid;


    Integer goodid;

    CustomerException(Integer orderid,Integer goodid){

        super();

        this.orderid = orderid;

        this.goodid = goodid;
    }
}
