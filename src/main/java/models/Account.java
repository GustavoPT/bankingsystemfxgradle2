package models;

import javax.persistence.*;

@Entity
public  class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    int id;

    double balance;

    String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Account() {
        // TODO Auto-generated constructor stub
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account(int id, double balance, String type, User user) {
        super();
        this.id = id;
        this.balance = balance;
        this.type = type;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
