package space_invaders.sprites.PlayerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Commons;
import space_invaders.sprites.Player;

import java.awt.event.KeyEvent;

public class PlayerTestCB {
	Player player;

	@BeforeEach
	public void init() {
		player = new Player();
	}

	@Test
	@DisplayName("comprueba act")
	public void testPlayerAct() {
		// Límite izquierdo
		player.setX(0);
		player.setdx(-2);
		player.act();

		// Límite derecho NO SE PUEDE PROBAR DEBIDO A EL METODO ACT
		player.setX(Commons.BOARD_WIDTH);
		player.setdx(2);
		player.act();
	}

	@Test
	@DisplayName("comprueba keypressed")
	public void testPlayerKeyPressed() {
		KeyEvent keyLeft = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
				KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
		KeyEvent keyRight = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
				KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
		player.keyPressed(keyLeft);
		player.keyPressed(keyRight);
	}

	@Test
	@DisplayName("comprueba keyreleased")
	public void testPlayerKeyReleased() {
		KeyEvent keyLeft = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
				KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
		KeyEvent keyRight = new KeyEvent(new java.awt.Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
				KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
		player.keyReleased(keyLeft);
		player.keyReleased(keyRight);
	}
}
