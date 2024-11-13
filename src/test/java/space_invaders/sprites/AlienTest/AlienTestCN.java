package space_invaders.sprites.AlienTest;

import main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Alien.Bomb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlienTestCN {

    @Test
    @DisplayName("Debería inicializar un alien con coordenadas válidas")
    public void testAlienInitialization() {
        Alien alien = new Alien(50, 100);
        assertEquals(50, alien.getX());
        assertEquals(100, alien.getY());
    }

    @Test
    @DisplayName("Debería inicializar un alien con coordenadas negativas")
    public void testAlienInitializationWithNegativeCoordinates() {
        Alien alien = new Alien(-10, -20);
        assertEquals(0, alien.getX());
        assertEquals(0, alien.getY());
    }

    @Test
    @DisplayName("Debería inicializar un alien fuera de los límites del tablero")
    public void testAlienInitializationWithExceedingCoordinates() {
        Alien alien = new Alien(Commons.BOARD_WIDTH + 10, Commons.BOARD_HEIGHT + 20);
        assertEquals(Commons.BOARD_WIDTH, alien.getX());
        assertEquals(Commons.BOARD_HEIGHT, alien.getY());
    }

    @Test
    @DisplayName("Debería mover el alien a la derecha")
    public void testAlienActRight() {
        Alien alien = new Alien(50, 100);
        alien.act(10);
        assertEquals(60, alien.getX());
    }

    @Test
    @DisplayName("Debería mover el alien a la izquierda")
    public void testAlienActLeft() {
        Alien alien = new Alien(50, 100);
        alien.act(-10);
        assertEquals(40, alien.getX());
    }

    @Test
    @DisplayName("No debería mover el alien")
    public void testAlienActNone() {
        Alien alien = new Alien(50, 100);
        alien.act(0);
        assertEquals(50, alien.getX());
    }

    @Test
    @DisplayName("Debería inicializar una bomba asociada al alien")
    public void testBombInitialization() {
        Alien alien = new Alien(50, 100);
        Alien.Bomb bomb = alien.getBomb();
        assertNotNull(bomb);
        assertEquals(50, bomb.getX());
        assertEquals(100, bomb.getY());
    }

    @Test
    @DisplayName("Debería inicializar una bomba asociada al alien que empezó por debajo del limite")
    public void testBombInitializationLower() {
        Alien alien = new Alien(-10, -20);
        Alien.Bomb bomb = alien.getBomb();
        assertNotNull(bomb);
        assertEquals(0, bomb.getX());
        assertEquals(0, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }

    @Test
    @DisplayName("Debería inicializar una bomba asociada al alien que empezó por encima del límite")
    public void testBombInitializationUpper() {
        Alien alien = new Alien(368, 360);
        Alien.Bomb bomb = alien.getBomb();
        assertNotNull(bomb);
        assertEquals(358, bomb.getX());
        assertEquals(350, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }

}