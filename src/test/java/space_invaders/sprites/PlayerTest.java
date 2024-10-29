package space_invaders.sprites;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.Commons;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    @DisplayName("Debería inicializar el jugador correctamente")
    public void testPlayerInitialization() {
        Player player = new Player();
        assertEquals(270, player.getX());
        assertEquals(280, player.getY());
        assertNotNull(player.getImage());
    }

    @Test
    @DisplayName("Debería mover el jugador a la izquierda")
    public void testPlayerMoveLeft() {
        Player player = new Player();
        KeyEvent leftKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyPressed(leftKeyPressed);
        player.act();
        assertEquals(268, player.getX());
    }

    @Test
    @DisplayName("Debería mover el jugador a la derecha")
    public void testPlayerMoveRight() {
        Player player = new Player();
        KeyEvent rightKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyPressed(rightKeyPressed);
        player.act();
        assertEquals(272, player.getX());
    }

    @Test
    @DisplayName("Debería detener el movimiento del jugador hacia la izquierda")
    public void testPlayerStopMoveLeft() {
        Player player = new Player();
        KeyEvent leftKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyPressed(leftKeyPressed);
        player.act();
        KeyEvent leftKeyReleased = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyReleased(leftKeyReleased);
        player.act();
        assertEquals(268, player.getX());
    }

    @Test
    @DisplayName("Debería detener el movimiento del jugador hacia la derecha")
    public void testPlayerStopMoveRight() {
        Player player = new Player();
        KeyEvent rightKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyPressed(rightKeyPressed);
        player.act();
        KeyEvent rightKeyReleased = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyReleased(rightKeyReleased);
        player.act();
        assertEquals(272, player.getX());
    }

    @Test
    @DisplayName("Debería mover el jugador al límite izquierdo")
    public void testPlayerMoveLeftBoundary() {
        Player player = new Player();
        player.setX(1);
        KeyEvent leftKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyPressed(leftKeyPressed);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    @DisplayName("Debería mover el jugador al límite derecho")
    public void testPlayerMoveRightBoundary() {
        Player player = new Player();
        player.setX(Commons.BOARD_WIDTH - player.getWidth());
        KeyEvent rightKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyPressed(rightKeyPressed);
        player.act();
        assertEquals(Commons.BOARD_WIDTH - player.getWidth(), player.getX());
    }
}