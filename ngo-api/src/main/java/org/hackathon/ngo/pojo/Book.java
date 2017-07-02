package org.hackathon.ngo.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity(name = "BOOK")
@Indexed
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@DocumentId
	private Integer id;
	
	@Field(index = Index.YES)
	@Column(name = "isbn")
	protected Integer isbnum;

	@Field(index = Index.YES)
	@Column(name = "name")
	protected String name;

	@Column(name = "org")
	protected String org;

	@Field(index = Index.YES)
	@Column(name = "author")
	protected String author;

	@IndexedEmbedded
	@OneToMany(mappedBy = "book", targetEntity = Chapters.class, cascade=CascadeType.ALL)
	protected List<Chapters> chapters;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIsbnum() {
		return isbnum;
	}

	public void setIsbnum(Integer isbnum) {
		this.isbnum = isbnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Chapters> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapters> chapters) {
		this.chapters = chapters;
	}

}
