package org.hackathon.controller;

import java.util.List;

import org.hackathon.ngo.pojo.Book;
import org.hackathon.ngo.pojo.HealthStatus;
import org.hackathon.service.BookService;
import org.hackathon.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/search" , consumes={MediaType.APPLICATION_JSON_VALUE} , produces={MediaType.APPLICATION_JSON_VALUE})
public class BookController {

	@Autowired
	private HealthService service;
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public HealthStatus getHealth() {
		return service.getStatus();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{keyword}")
	public List<Book> getSearchResults(@PathVariable("keyword") String keyword) {
		return bookService.getSearchResult(keyword);
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public void createBook(@RequestBody Book book) {
		bookService.createBook(book);
	}
}
