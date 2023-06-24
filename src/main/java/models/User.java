package models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String phone;

    private boolean enabled = true;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<BankTransaction> bankTransactions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Card> cards;


    public int getUserId() {
        return userId;
    }

    public List<BankTransaction> getTransactions() {
        return bankTransactions;
    }

    public void setBankTransactions(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username='" + username + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + '}';
    }

    public User(String username, String password, String firstName, String lastName, String email, String phone, boolean enabled) {
        super();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
    }

    public User() {
        // TODO Auto-generated constructor stub
    }

    public boolean checkEmail(String email) {
        if (this.email.equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEmailExists(String email) {
        if (this.email.equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public List<BankTransaction> getBankTransactions() {
        return bankTransactions;
    }

    public int getId() {
        return this.userId;
    }
}
