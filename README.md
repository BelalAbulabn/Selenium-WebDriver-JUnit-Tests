# Selenium-WebDriver-JUnit-Tests

This project is designed to perform automated testing on a web application using Selenium WebDriver and JUnit5 in Java.

## About the tests

These automated tests are designed to ensure that our web application performs as expected in a variety of scenarios. Here are some of the key functionalities that our tests verify:

1. **User Registration:** The tests ensure that the user registration process is working correctly, verifying that validation is properly applied on all fields.

2. **User Login:** The tests check that the login process is secure and works correctly, handling both valid and invalid login scenarios.

3. **Navigation:** The tests verify that all menu links and buttons navigate to the correct pages when clicked.

4. **Search functionality:** The tests verify that the search bar returns correct and relevant results.

5. **Form Submission:** The tests validate form fields and ensure that the form submissions are successful.

7. **LoadMore:** This test checks the "Load More" functionality. It verifies whether more items are loaded when the "Load More" button is clicked, by comparing the count of items before and after the button click.

8. **SucheFilter & SucheFilter2:** These tests check the filter functionality. It verifies that applying a filter reduces the number of items displayed, by comparing the count of items before and after applying a filter.

9. **RandomFilterSuche:** This test checks the functionality of a randomly selected filter. Similar to "SucheFilter", it compares the count of items before and after applying a randomly chosen filter.

10. **setRemovebutton:** This test checks the functionality of the remove button on a filter. It verifies that removing a filter increases the number of items displayed to the original count before the filter was applied.

11. **setTwoFilterRemoveButton:** This test is similar to "setRemovebutton", but applies two filters before removing them.

12. **TwoClickOnOneFilter:** This test checks the idempotency of applying a filter twice. It verifies that clicking on a filter twice (which should equate to applying and then removing the filter) does not change the count of items displayed.

Of course, the specifics of these tests will vary depending on the exact nature of the web application being tested.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
``
You will need the following tools:

- Java Development Kit (JDK) 11 or later
- Maven (or other project management tool such as Gradle)
- Your preferred IDE (like IntelliJ IDEA or Eclipse)
- ChromeDriver (if you're using Google Chrome as your browser for testing)

### Running Tests
To run the tests, you'll need to set the SERVER_URL environment variable to the URL of your web server. You can do this in your IDE's run configuration.
Once the environment variable is set, you can run the tests with:
### Built With
Java
Selenium WebDriver
JUnit5
### Authors
Belal Abulabn   
### Email 
Belal.abulabn@gmail.com
``
