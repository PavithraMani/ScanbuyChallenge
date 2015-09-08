# ScanbuyChallenge
ScanbuyChallenge

Table :
======
A table books has attributes barcode,title,author,pages,flag
flag 1 indicates that the book is read
flag 0 indicates that the book is not read
barcode is the primary key.

To insert a book into the table books :
======================================
http://localhost:8080/SpringMVC/books/insert/barcode/1/title/harrypotter/author/rowling/pages/100/flag/1
This inserts a record into the books table with values as specified and also displays the record in JSON format. 
If the barcode already exists, then it displays a message "Book already exists".

To delete a book from the table books :
======================================
http://localhost:8080/SpringMVC/books/delete/barcode/1
This deletes a record from the books table with the given barcode.

To retrieve a book from the table books :
========================================
http://localhost:8080/SpringMVC/books/retrieve/barcode/1
This selects a record from the books table with the given barcode and also displays the record in JSON format. 
If the barcode already exists, then it displays a message "No book exists with specified barcode".
