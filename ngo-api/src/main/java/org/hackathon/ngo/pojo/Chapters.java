package org.hackathon.ngo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

@Entity(name="CHAPTER")
@Indexed
public class Chapters {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	protected Integer id;
	
	@Column(name="name")
	@Field(index=Index.YES)
	protected String cname;
	
	@Column(name="description")
	@Field(index=Index.YES)
	protected String description;
	
	@OneToOne(targetEntity=Book.class)
	@JoinColumn(name="isbn")
	private Book book;
	
	public Chapters() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
