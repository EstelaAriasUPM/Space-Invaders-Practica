package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlienTest {

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
    @DisplayName("Debería mover el alien en la dirección indicada")
    public void testAlienAct() {
        Alien alien = new Alien(50, 100);
        alien.act(10);
        assertEquals(10 + Commons.ALIEN_WIDTH, alien.getX());
    }

    @Test
    @DisplayName("Debería inicializar una bomba asociada al alien")
    public void testBombInitialization() {
        Alien alien = new Alien(50, 100);
        Alien.Bomb bomb = alien.getBomb();
        assertNotNull(bomb);
        assertEquals(50, bomb.getX());
        assertEquals(100, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }

    @Test
    @DisplayName("Debería cambiar el estado de destrucción de la bomba")
    public void testBombSetDestroyed() {
        Alien alien = new Alien(50, 100);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        assertFalse(bomb.isDestroyed());
    }
}