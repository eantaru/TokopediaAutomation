# Tokopedia Automation Test
## _Web scrapping with Selenium Web Driver and Cucumber_

All the libraries are:

- Cobertura-2.1.1
- Common-csv-1.8
- Cucumber-core-1.2.2
- Cucumber-java-1.2.2
- Cucumber-junit-1.2.2
- Cucumber-jvm-deps-1.0.3
- Cucumber-reporting-0.1.0.
- gherkin-2.12.2
- junit-4.11
- ✨Mselenium-server-standalone.3.141.49✨M

## Features

- Automation test or scrapping Tokopedia mobile phone page section
- Store to csv formated

## Setup

Install the chrome browser or other with brew

```sh
brew install chromedriver
```

Include all libraries to the project manually or update gradle or maven accordingly

## Assignment

As the test quite wide open therefore I follow BDD (Behavioral Driven Development) with Selenium web driver as automation test and Cucumber Gherkin as BDD tools.
Even though I the method I was used is not best, I was tried to solve it within the time limit. I still use the procedural method with the following steps:
- Open the Tokopedia for the mobile phone page
- Save the mobile phone list into the List
- Open the detail page one by one to store the items needed
- Scroll down to the bottom of the page
- If haven't met the limit (100 items), go to the next page
- repeat steps above (from save to list and scroll down)
- If have met the limit, save it to CSV

## License

Free 
**[Test purposes]**
