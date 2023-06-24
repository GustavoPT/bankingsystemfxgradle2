package models;

import javax.persistence.*;
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	int id;
	String name;

	@ManyToOne
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private BankTransaction bankTransactions;

	public Product() {
		super();
	}

	public Product(String name, Merchant merchant) {
		super();
		this.name = name;
		this.merchant = merchant;
	}

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

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public BankTransaction getBankTransactions() {
		return bankTransactions;
	}

	public void setBankTransactions(BankTransaction bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", merchant=" + merchant + ", bankTransactions=" + bankTransactions
				+ "]";
	}
}