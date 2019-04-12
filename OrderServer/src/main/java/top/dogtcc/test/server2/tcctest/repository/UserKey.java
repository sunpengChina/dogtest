package top.dogtcc.test.server2.tcctest.repository;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserKey implements Serializable {
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    private String firstname;

    private String secondname;

    @Override
    public String toString() {
        return firstname+secondname;
    }

}
