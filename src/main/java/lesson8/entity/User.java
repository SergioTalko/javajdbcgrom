package lesson8.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;

    private Set<Order> orders;

    @Id
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "U_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "USER_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Order> getOrders() {
        return orders;
    }

    //--SETTERS

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
