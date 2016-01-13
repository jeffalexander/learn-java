package com.jaa.games.mastermind;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber"}, 
		features={"classpath:features/"})
public class RunCukesTest {
}
