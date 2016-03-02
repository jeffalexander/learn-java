package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.TEAL;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.Delimiter;
import cucumber.api.Transformer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GameStepDefinitions {
	private AttemptTransformer attemptTransformer = new AttemptTransformer();
	private List<CodePin> secretCode;
	private MasterMindGame game;
	private Response actualResponse;
	
	@Given("^a secret code$")
	public void a_secret_code() {
		secretCode = Arrays.asList(new CodePin[] {TEAL, TEAL, TEAL, TEAL});
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
	
	@Given("^the secret code ((?:[A-Z]+(?:,\\s*)?)+)$")
	public void the_secret_code(@Delimiter(",\\s*") List<CodePin> secret) {
		secretCode = secret;
		game.newGame(secretCode);
	}

	@When("^the attempt is ((?:[A-Z]+(?:,\\s*)?)+)$")
	public void the_attempt_is(@Delimiter(",\\s*") List<CodePin> attempt) {
		actualResponse = game.submitAttempt(attempt);
	}
	
	@Then("^the response has (\\d+) red and (\\d+) white key pins$")
	public void the_response_has_red_key_pins(int expectedRedCount, int expectedWhiteCount) {
		Assert.assertEquals(expectedRedCount, actualResponse.getRedCount());
		Assert.assertEquals(expectedWhiteCount, actualResponse.getWhiteCount());
	}
	
	@Given("^the attempts$")
	public void the_attempts(List<String> attempts) {
		for (String attempt : attempts) {
			game.submitAttempt(attemptTransformer.transform(attempt));
		}
	}
	
	@Then("^the histroy is$")
	public void the_histroy_is(DataTable expectedMoveHistoryTable) {
		List<Move> expectedMoveHistory = new ArrayList<Move>();
		List<List<String>> rows = expectedMoveHistoryTable.cells(1);
		for (List<String> row : rows) {
			Move move = new Move();
			move.setAttempt(attemptTransformer.transform(row.get(0)));
			Integer redCount = Integer.valueOf(row.get(1));
			Integer whiteCount = Integer.valueOf(row.get(2));
			move.setResponse(redCount, whiteCount);
			expectedMoveHistory.add(move);
		}
		
		RoundHistory actualHistory = game.getHistory();
		int index = 0;
		for (Iterator<Move> iterator = expectedMoveHistory.iterator(); iterator.hasNext(); index++) {
			Move expectedMove = iterator.next();
			Assert.assertEquals("Move " + index + " doesn't match", expectedMove, actualHistory.getMoves().get(index));
		}
	}
	
	/*
	 * For use when you need a DataTable Cell to contain a List<CodePin>
	 * You can't really use this as an @Transform(AttemptTransformer.class)
	 * Just manually call this function
	 */
	public static class AttemptTransformer extends Transformer<List<CodePin>> {
		
		@Override
		public List<CodePin> transform(String attemptString) {
			String[] codePins = attemptString.split(",");
			
			List<CodePin> attempt = new ArrayList<CodePin>();
			for (String codePin : codePins) {
				attempt.add(CodePin.valueOf(codePin.trim()));
			}
			return attempt;
		}
	}
}
