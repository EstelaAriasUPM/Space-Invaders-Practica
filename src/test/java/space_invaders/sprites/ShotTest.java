package space_invaders.sprites;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Commons;

import static org.junit.jupiter.api.Assertions.*;

public class ShotTest {

    @Test
    @DisplayName("Debería inicializar un disparo con coordenadas válidas")
    public void testShotInitialization() {
        Shot shot = new Shot(50, 100);
        assertNotNull(shot.getImage());
        assertEquals(56, shot.getX()); // 50 + H_SPACE (6)
        assertEquals(99, shot.getY()); // 100 - V_SPACE (1)
    }

    @Test
    @DisplayName("Debería inicializar un disparo con coordenadas por debajo del tablero")
    public void testShotInitializationWithNegativeCoordinates() {
        Shot shot = new Shot(-10, -20);
        assertNotNull(shot.getImage());
        assertEquals(6, shot.getX()); // 0 + H_SPACE (6)
        assertEquals(0, shot.getY()); // 0 - V_SPACE (1)
    }

    @Test
    @DisplayName("Debería inicializar un disparo con coordenadas por encima del tablero")
    public void testShotInitializationWithExceedingCoordinates() {
        Shot shot = new Shot(Commons.BOARD_WIDTH + 10, Commons.BOARD_HEIGHT + 20);
        assertNotNull(shot.getImage());
        assertEquals(Commons.BOARD_WIDTH, shot.getX()); // Commons.BOARD_WIDTH + 10 + H_SPACE (6)
        assertEquals(349, shot.getY()); // Commons.BOARD_HEIGHT + 20 - V_SPACE (1)
    }
}