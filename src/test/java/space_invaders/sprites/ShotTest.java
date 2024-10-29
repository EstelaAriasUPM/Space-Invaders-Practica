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
    @DisplayName("Debería inicializar un disparo con coordenadas negativas")
    public void testShotInitializationWithNegativeCoordinates() {
        Shot shot = new Shot(-10, -20);
        assertNotNull(shot.getImage());
        assertEquals(-4, shot.getX()); // -10 + H_SPACE (6)
        assertEquals(-21, shot.getY()); // -20 - V_SPACE (1)
    }

    @Test
    @DisplayName("Debería inicializar un disparo fuera de los límites del tablero")
    public void testShotInitializationWithExceedingCoordinates() {
        Shot shot = new Shot(Commons.BOARD_WIDTH + 10, Commons.BOARD_HEIGHT + 20);
        assertNotNull(shot.getImage());
        assertEquals(Commons.BOARD_WIDTH + 16, shot.getX()); // Commons.BOARD_WIDTH + 10 + H_SPACE (6)
        assertEquals(Commons.BOARD_HEIGHT + 19, shot.getY()); // Commons.BOARD_HEIGHT + 20 - V_SPACE (1)
    }
}