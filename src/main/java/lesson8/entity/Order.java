package lesson8.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    private long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    public Order() {
    }

    public Order(User user, Room room, Date dateTo) {
        this.user = user;
        this.room = room;
        this.dateTo = dateTo;
    }

    @Id
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "O_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ORDER_SEQ")
    @Column(name = "ORDER_ID")
    public long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Room_ID", referencedColumnName = "Room_ID")
    public Room getRoom() {
        return room;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_FROM" )
    public Date getDateFrom() {
        return dateFrom;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_TO")
    public Date getDateTo() {
        return dateTo;
    }

    @Column(name = "MONEY_PAID")
    public double getMoneyPaid() {
        return moneyPaid;
    }
    //--Setters


    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }
}

