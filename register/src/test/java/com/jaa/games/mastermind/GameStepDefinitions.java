package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLUE;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GameStepDefinitions {
	private CodePin[] secretCode;
	private MasterMindGame game;
	
	@Given("^a secret code$")
	public void a_secret_code() throws Throwable {
		secretCode = new CodePin[] {BLUE, BLUE, BLUE, BLUE};
	}

	@When("^a new game is started$")
	public void a_new_game_is_started() throws Throwable {
		game = new MasterMindGame();
		game.newGame(secretCode);
	}

	@Then("^the secret code is stored$")
	public void the_secret_code_is_stored() throws Throwable {
		assertTrue("Secret code must have at least one pin", game.getSecretCodeLength() > 0);
	}
	
	@Then("^the game should have no history$")
	public void the_game_should_have_no_history() throws Throwable {
		assertTrue("", game.getHistory().getMoves().size() == 0);
	}
}
