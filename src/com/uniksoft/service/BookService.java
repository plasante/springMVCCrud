package com.uniksoft.service;

import java.util.List;

import com.uniksoft.form.Book;

public interface BookService {

	public void addBook(Book book);
    public void updateBook(Book book);
    public Book getBookById(Integer bookId);
    public List<Book> listBooks();
    public void removeBook(Integer id);
}
