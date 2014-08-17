package com.uniksoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniksoft.dao.BookDao;
import com.uniksoft.form.Book;

@Service
public class BookServiceImpl {

	@Autowired
	private BookDao bookDao;
	
	@Transactional
	public void addBook(Book book) {
		bookDao.addBook(book);
	}
	
	@Transactional
    public void removeBook(Integer id) {
        bookDao.removeBook(id);
    }
 
    @Transactional
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }
 
    @Transactional
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
 
    @Transactional
    public Book getBookById(Integer bookId) {
        return bookDao.getBookById(bookId);
    }
}
