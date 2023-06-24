package models;

import javax.persistence.*;

@Entity
@Table(name = "BankTransaction")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankTransaction_id")
    int id;

    double amount;

    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "card")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "merchant")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BankTransaction() {
        // TODO Auto-generated constructor stub
    }

    public BankTransaction(int id, double amount, String date, User user, Card card, Merchant merchant, Product product, Account account) {
        super();
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.user = user;
        this.card = card;
        this.merchant = merchant;
        this.product = product;
        this.account = account;
    }
}
