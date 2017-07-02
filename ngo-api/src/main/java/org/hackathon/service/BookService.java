package org.hackathon.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hackathon.data.BookRepo;
import org.hackathon.ngo.pojo.Book;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepo repo;

	@PersistenceContext
	private EntityManager entityManager;

	public void createBook(Book book) {
		repo.save(book);
	}

	@Transactional
	public List<Book> getSearchResult(String keyWord) {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

		QueryBuilder builder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();

		org.apache.lucene.search.Query query = builder.keyword().onFields("name", "chapters.cname" ,"author")
				.matching(keyWord).createQuery();

		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);

		@SuppressWarnings("unchecked")
		List<Book> results = jpaQuery.getResultList();
		
		return results;
	}
}
