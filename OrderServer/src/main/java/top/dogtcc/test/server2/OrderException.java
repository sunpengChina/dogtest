package top.dogtcc.test.server2;

import top.dogtcc.test.server2.dao.Orderdao;

public class OrderException extends  Exception{

    private Orderdao orderdao;

    public Orderdao getOrderdao() {
        return orderdao;
    }

    public void setOrderdao(Orderdao orderdao) {
        this.orderdao = orderdao;
    }

    public OrderException(Orderdao orderdao) {
        this.orderdao = orderdao;
    }
}
