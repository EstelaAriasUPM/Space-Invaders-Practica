package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import space_invaders.sprites.Alien;

public class BoardTestCB {

	@Test
	@DisplayName("Debería inicializar la partida correctamente")
	void testGameInit() {
		Board board = new Board();

		// CP1.1 Verificar que los aliens están inicializados correctamente
		assertNotNull(board.getAliens());
		assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, board.getAliens().size()); // 4 filas x 6 columnas = 24 aliens

		// CP1.2 Verifica el alien de la posicion 0 (primer alien i=0, j=0)
		Alien firstAlien = board.getAliens().get(0);
		assertEquals(Commons.ALIEN_INIT_Y, firstAlien.getX());
		assertEquals(Commons.ALIEN_INIT_Y, firstAlien.getY());

		// CP1.3 Verifica el alien de la posicion 23 (último alien i=3, j=5)
		Alien lastAlien = board.getAliens().get(23);
		assertEquals(Commons.ALIEN_INIT_Y + 18 * 5, lastAlien.getX());
		assertEquals(Commons.ALIEN_INIT_Y + 18 * 3, lastAlien.getY());

		// CP1.4 Verificar que el jugador está inicializado correctamente
		assertNotNull(board.getPlayer());
		assertTrue(board.getPlayer().isVisible());

		// CP1.5 Verificar que el disparo está inicializado correctamente
		assertNotNull(board.getShot());
	}

	@Test
	@DisplayName("update board CB")
	void testUpdate() {
		Board board = new Board();
		board.setDeaths(0);
		board.update();
		board.setDeaths(5);
		board.update();
	}

	@Test
	@DisplayName("update shoots CP1 CB")
	void testUpdateShoots() {
		Board board = new Board();
		board.getShot().setVisible(false);
		board.update_shots();
	}

	@Test
	@DisplayName("update shoots CP2 CB")
	void testUpdateShoots2() {
		Board board = new Board();
		board.getShot().setVisible(true);
		board.setAliens(new ArrayList<Alien>());
		board.update_shots();
	}

	@Test
	@DisplayName("update shoots CP3 CB")
	void testUpdateShoots3() {
		Board board = new Board();
		board.getShot().setVisible(true);
		board.getShot().setY(10);
		board.setAliens(new ArrayList<Alien>());
		board.update_shots();
	}

	@Test
	@DisplayName("update shoots CP4 CB")
	void testUpdateShoots4() {
		Board board = new Board();
		board.getShot().setVisible(true);
		board.getShot().setX(10);
		board.getShot().setY(10);
		for (Alien a : board.getAliens()) {
			a.setVisible(false);
		}
		board.update_shots();
	}
	@Test
	@DisplayName("update shoots CP5 CB")
	void testUpdateShoots5() {
		Board board = new Board();
		board.getShot().setVisible(true);
		board.getShot().setX(10);
		board.getShot().setY(10);
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.setX(100);
		}
		board.update_shots();
	}
	@Test
	@DisplayName("update shoots CP6 CB")
	void testUpdateShoots6() {
		Board board = new Board();
		board.getShot().setVisible(true);
		board.getShot().setX(10);
		board.getShot().setY(10);
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.setX(10);
			a.setY(10);
		}
		board.update_shots();
	}

	@Test
	@DisplayName("update aliens CP1 CB")
	void testUpdateAliens() {
		Board board = new Board();
		board.setAliens(new ArrayList<Alien>());

	}

	@Test
	@DisplayName("update aliens CP4 CB")
	void testUpdateAliens1() {
		Board board = new Board();
		for (Alien a : board.getAliens()) {
			a.setVisible(false);
			a.setX(400);
		}
		board.update_aliens();
	}

	@Test
	@DisplayName("update aliens CP2 CB")
	void testUpdateAliens2() {
		Board board = new Board();
		for (Alien a : board.getAliens()) {
			a.setVisible(false);
			a.setX(100);
		}
		board.setDirection(-1);
		board.update_aliens();

	}

	@Test
	@DisplayName("update aliens CP3 CB")
	void testUpdateAliens3() {
		Board board = new Board();
		for (Alien a : board.getAliens()) {
			a.setVisible(false);
			a.setX(0);
		}
		board.setDirection(0);
		board.update_aliens();

	}

	@Test
	@DisplayName("update aliens CP5 CB")
	void testUpdateAliens4() {
		Board board = new Board();
		for (Alien a : board.getAliens()) {
			a.setX(400);
			a.setY(Commons.GROUND - 100);
		}
		board.setDirection(0);
		board.update_aliens();

	}
	
	@Test
	@DisplayName("update aliens CP6 CB")
	void testUpdateAliens5() {
		Board board = new Board();
		for (Alien a : board.getAliens()) {
			a.setX(400);
			a.setY(Commons.GROUND + 100);
		}
		board.setDirection(0);
		board.update_aliens();

	}

	@Test
	@DisplayName("update bomb cp1 CB")
	void testUpdatebombs() {
		Board board = new Board();
		board.getTimer().stop();
		board.setAliens(new ArrayList<Alien>());
		board.update_bomb();

	}

	@Test
	@DisplayName("update bomb cp2 CB")
	void testUpdatebombs2() {
		Board board = new Board();
		board.getTimer().stop();
		for (Alien a : board.getAliens()) {
			a.setVisible(false);
			a.getBomb().setDestroyed(true);
		}
		board.getPlayer().setVisible(false);
		board.update_bomb();

	}

	@Test
	@DisplayName("update bomb cp3 CB")
	void testUpdatebombs3() {
		Board board = new Board();
		board.getTimer().stop();
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.getBomb().setDestroyed(true);
		}
		board.getPlayer().setVisible(false);
		board.update_bomb();

	}

	@Test
	@DisplayName("update bomb cp4 CB")
	void testUpdatebombs4() {
		Board board = new Board();
		board.getTimer().stop();
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.getBomb().setDestroyed(true);
			a.getBomb().setX(-100);
			a.getBomb().setY(-100);
		}
		board.getPlayer().setVisible(true);
		board.getPlayer().setX(200);
		board.getPlayer().setY(200);
		board.update_bomb();

	}

	@Test
	@DisplayName("update bomb cp5 CB")
	void testUpdatebombs5() {
		Board board = new Board();
		board.getTimer().stop();
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.getBomb().setDestroyed(true);
			a.getBomb().setX(100);
			a.getBomb().setY(100);
		}
		board.getPlayer().setVisible(true);
		board.getPlayer().setX(100);
		board.getPlayer().setY(100);
		board.update_bomb();

	}

	@Test
	@DisplayName("update bomb cp6 CB")
	void testUpdatebombs6() {
		Board board = new Board();
		board.getTimer().stop();
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.getBomb().setDestroyed(false);
			a.getBomb().setX(100);
			a.getBomb().setY(100);
		}
		board.getPlayer().setVisible(true);
		board.getPlayer().setX(100);
		board.getPlayer().setY(100);
		board.update_bomb();

	}

	@Test
	@DisplayName("update bomb cp7 CB")
	void testUpdatebombs7() {
		Board board = new Board();
		board.getTimer().stop();
		for (Alien a : board.getAliens()) {
			a.setVisible(true);
			a.getBomb().setDestroyed(true);
			a.getBomb().setX(100);
			a.getBomb().setY(300);
		}
		board.getPlayer().setVisible(false);
		board.getPlayer().setX(0);
		board.getPlayer().setY(0);
		board.update_bomb();

	}
}
