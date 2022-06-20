# QA Challenge Project

## Tech stack
- Java 17
- SpringBoot
- Cucumber
- Lombok
- Maven
- Allure reporting

### How to run

_By default, the tests run against Prod environment_

- Setup env with the parameter `-Dspring.profiles.active=env`. Options: local, test, stage, prod
- Run the all tests using the next command: `mvn clean test`
- Run the tests according to a user role: `mvn clean test -P <profile>` Options: tester, tester-owner, product-owner

### Reporting

- Allure test reports are saved by default after every test run
- Test reports directory: `/target/allure-reports`
- To create a readable html report: `mvn allure:report`
- To create a report and open it in a local web service: `mvn allure:serve`


#### Cross-browser support

- Setup browser with the parameter: `-Dcore.browser.type=browser`. Options: firefox, opera, chrome, edge, safari