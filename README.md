### Short description of the application

#### BookApplications is a small project that retrives information from either googleBooksApi or the json file (which is mapped like json from GoogleBookApi) and return the endpoints which are mapped to json file. The application has 5 endpoints, which are :
#### Google Books API can return maximum of 40 items with my own API key. Without API key, Google Books API returns only 10 items. It retrives 40 books which are published by Penguin

##### -{serverURI}/isbn:{id} - retrive information about book by given isbn id
##### -{serverURI}/category:{category} - retrive list of books in given category
##### -{serverURI}/authors-rating - retrive information about Authors and their average books rating 
##### -{serverURI}/pages:{maxPages} - retrive information about first book which is longer than given pages
##### -{serverURI}/best-pace:{pace}/daily:{daily} - retrive the list of books with highest average rating which can be read in a month. The user have to specify the average pages per hour pace and daily time spent on reading.
##### -{serverURI}/recently-viewed-books - retrive the list of recently viewed books which were search by isbn

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

##### // TO get books from given json file (has to be mapped like Google Books Api json file)
##### java -jar target/homework-2.4.0.jar -Ddatasource={custom path to json file, has to be absolut}
