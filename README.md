### Short description of the application

BookApplications is a small project that retrives information from either googleBooksApi or the json file (which contract is simnilar to the GoogleBookApis). The application has 5 endpoints, which are :

* {serverURI}/isbn:{id} - retrive information about book by given isbn id
* {serverURI}/category:{category} - retrive list of books in given category
* {serverURI}/authors-rating - retrive information about Authors and their average books rating 
* {serverURI}/pages:{maxPages} - retrive information about first book which is longer than given pages
* {serverURI}/best-pace:{pace}/daily:{daily} - retrive the list of books with highest average rating which can be read in a month. The user have to specify the average pages per hour pace and daily time spent on reading.
* {serverURI}/recently-viewed-books - retrive the list of recently viewed books which were search by isbn

When using Google Books API as data resource, I was limited to following constraints:
* Google Books API can return maximum of 40 items with my own API key or 10 without it.
* API doesn't allow to retrive non parametrized query thus I had to create my own criteria which are based on publisher (Penguin).

### Frameworks

* SpringBoot 2.4.0 - for simplier application setup and make it runnable without external server
* JUnit -for unit tests
* RestAssured - for integration tests
* Hamcrest - for custom assertions

### Design Patterns

* Builder - for unit tests
* Singleton * BookCacheImpl.class - storing books retrived by isbn up to 5) and ParamInitializer.class (parameters passed by terminal arguments)

### Instruction how to run application

##### To build the project use following command:
* mvn clean package

##### After building the application run one of the following command to start it:

// To get books from books.json:  
* java -jar target/homework-2.4.0.jar -Ddatasource=misc/books.json

// To get books from Google Books Api:  
* java -jar target/homework-2.4.0.jar -Ddatasource=googleApi

// TO get books from given json file (has to be mapped like Google Books Api json file)
* java -jar target/homework-2.4.0.jar -Ddatasource={custom path to json file, has to be absolut}
