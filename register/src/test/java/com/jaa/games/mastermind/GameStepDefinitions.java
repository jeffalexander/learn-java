package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLUE;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GameStepDefinitions {
	private List<CodePin> secretCode;
	private MasterMindGame game;
	private List<KeyPin> actualResponse;
	
	@Given("^a secret code$")
	public void a_secret_code() {
		secretCode = Arrays.asList(new CodePin[] {BLUE, BLUE, BLUE, BLUE});
	}

	@When("^a new game is started$")
	public void a_new_game_is_started() {
		game = new MasterMindGame();
		game.newGame(secretCode);
	}

	@Then("^the secret code is stored$")
	public void the_secret_code_is_stored() {
		assertTrue("Secret code must have at least one pin", game.getSecretCodeLength() > 0);
	}
	
	@Then("^the game should have no history$")
	public void the_game_should_have_no_history() {
		assertTrue("", game.getHistory().getMoves().size() == 0);
	}
	
	@Given("^the secret code ((?:[A-Z]+(?:, )?)+)$")
	public void the_secret_code(List<CodePin> secret) {
		secretCode = secret;
		game.newGame(secretCode);
	}

	@When("^the attempt is ((?:[A-Z]+(?:, )?)+)$")
	public void the_attempt_is(List<CodePin> attempt) {
		actualResponse = game.submitAttempt(attempt);
	}

	@Then("^the response is ((?:[A-Z]?(?:, )?)+)$")
	public void the_response_is(List<KeyPin> expectedResponse) {
		Assert.assertEquals(expectedResponse, actualResponse);
	}
	
	@Then("^the response has (\\d+) red and (\\d+) white key pins$")
	public void the_response_has_red_key_pins(int expectedRedCount, int expectedWhiteCount) {
		Assert.assertEquals(expectedRedCount, KeyPin.count(KeyPin.RED, actualResponse));
		Assert.assertEquals(expectedWhiteCount, KeyPin.count(KeyPin.WHITE, actualResponse));
	}
}
