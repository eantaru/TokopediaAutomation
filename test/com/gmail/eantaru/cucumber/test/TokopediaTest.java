package com.gmail.eantaru.cucumber.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features", glue= {"com.gmail.eantaru.cucumber.page.tokopedia"})
public class TokopediaTest {

}
