package com.duongtai.estore.entities;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
public class Order {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long id;
	
	@Column(name = "order_note", length = 255)
	private String order_note;
	
	@ManyToMany
	@JoinTable(name = "order_details", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
	
	private double total;
	
	private double tax;
	
	private double discount;
	
	@Column(updatable = false)
	private String created_at;
	
	private String last_edited_at;
	
	private boolean isFinish;

	@ManyToOne
	@JoinColumn(name = "orders", referencedColumnName = "user_id")
	private User customer;
	
	public Order() {
		
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getOrder_note() {
		return order_note;
	}



	public void setOrder_note(String order_note) {
		this.order_note = order_note;
	}



	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getTotal() {
	
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getFinal_total() {
		double tax = (this.total * this.tax)/100;
		double discount = (this.total * this.discount)/100; 
		return this.total + tax - discount;
	}

	public void setFinal_total(double final_total) {
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

	public int getProducts_quantity() {
		return products.size();
	}

	public void setProducts_quantity(int products_quantity) {
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}




	public String getLast_edited_at() {
		return last_edited_at;
	}




	public void setLast_edited_at(String last_edited_at) {
		this.last_edited_at = last_edited_at;
	}

	

	
}
