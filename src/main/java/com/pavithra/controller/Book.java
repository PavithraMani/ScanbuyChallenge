package com.pavithra.controller;

public class Book {
	int barcode;
	String title;
	String author;
	int pages;
	int read;
	public Book(){
	}
	public Book(int barcode,String title,String author,int pages,int read){
		this.barcode=barcode;
		this.title=title;
		this.author=author;
		this.pages=pages;
		this.read=read;
	}
	public Book(int barcode){
		this.barcode=barcode;
	}
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	@Override
	    public String toString() {
		return "Book [ { Barcode : "+barcode+", Title : '" + title + "', Author : '" + author + "', Pages : " + pages + ", Read : " + read + " } ]";
	    }

}
