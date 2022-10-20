# MyTutor
MyTutor Code Challenge

### How to compile application
`mvn clean install`

### How to run the tests
`mvn test`

### How to run the application
`mvn spring-boot:run`

### Docker

Exists a Dockerfile prepared to download a Centos OS, install the **openjdk11** and install the application.

- Run the command: `docker build -t mytutor/interview-test:release .`
- Run the command: `docker run -p port:port <IMG_TAG>`
- Example: `docker run -p 8080:8080 8fb870f41548`

### APIs:

* POST: http://localhost:8080/mytutor/book-sell/sell
> JSON Body example:

```javascript{
{
  "type": "A",
  "quantity": "6"
}
```

_Output expected:_ Thank you for your purchase!

```javascript{
{
  "type": "D",
  "quantity": "19"
}
```
_Output expected:_ Sorry, we are out of stock!


> Show the book selling report.
* GET: http://localhost:8080/mytutor/book-shop/report

_Output expected:_ (example)

MyTutor Bookshop Balance: £1225.2

Book B | 10 Copies Sold | £140.0 Total Profit 

Book A | 9 Copies Sold | £157.5 Total Profit 

Book C | 7 Copies Sold | £112.7 Total Profit 

Book E | 5 Copies Sold | £94.5 Total Profit 

Book D | 3 Copies Sold | £63.0 Total Profit
