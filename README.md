### Short description of the application

#### BookApplications is a small project that retrives information from either googleBooksApi or the json file (which is mapped like json from GoogleBookApi) and return the jsonFile. The application has 5 endpoints, which are :
#### Google Books API can return maximum of 40 items with my own API key. Without API key, Google Books API returns only 10 items. It retrives 40 books which are published by Penguin

##### -isbn:{id} - retrive information about book by given isbn id
##### -getBooksByCategory- retrive list of books in given category
##### -getBooksByAuthorRatings- retrive information about Authors and their average books rating 
##### -getBookByPages- retrive information about book which is no longer than given pages
##### -getBooksByReadingSkills - retrive the list of books with highest average rating given the average pages per hour pace and daily time spent on reading
##### -getRecentlyViewedBooks - retrive the list of recently viewed books through isbn search 

#### The app also contains unit and integration tests

### Frameworks

##### -SpringBoot 2.4.0 - for simplier application setup and make it runnable without external server
##### -JUnit -for unit tests
##### -RestAssured - for integration tests

### Design Patterns
##### Builder - for unit tests
##### Singleton - BookCacheImpl.class(storing books retrived by isbn up to 5) and ParamInitializer.class (parameters passed by terminal arguments)

### Instruction how to run application

##### To build the project use following command:
##### mvn clean package

##### After building the application run one of the following command to start it:

##### // To get books from books.json:  
##### java -jar target/homework-2.4.0.jar -Ddatasource=misc/books.json

##### // To get books from Google Books Api:  
##### java -jar target/homework-2.4.0.jar -Ddatasource=googleApi

