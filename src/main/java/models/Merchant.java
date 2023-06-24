package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="merchant")
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="merchant_id")
	private int id;
	String name;
	// each product 
	
	@ManyToMany 
	@JoinTable(
			name="product_copy",
			joinColumns=@JoinColumn(name="merchant_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
			)
	private List<Product> products;
	
//	@OneToMany(fetch = FetchType.EAGER,mappedBy="merchant")
//	private Set<Product> products = new HashSet<>();
//	
	public Merchant() {
		super();
	}
	public Merchant(String name) {
		super();
		this.name = name;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Merchant [id=" + id + ", name=" + name + ", products=" + products + "]";
	}
	
	
}
