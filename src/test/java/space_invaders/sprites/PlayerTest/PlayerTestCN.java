package space_invaders.sprites.PlayerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.Commons;
import space_invaders.sprites.Player;

import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTestCN {

    //Inicializamos el objeto player
	Player player;
	
	@BeforeEach
	public void init() {
		player = new Player();
	}
	

    @Test
    @DisplayName("Debería inicializar el jugador correctamente con valores por defecto")
    public void testPlayerInitialization() {
        assertEquals(270, player.getX());
        assertEquals(280, player.getY());
        assertNotNull(player.getImage());
    }

    @Test
    @DisplayName("Debería inicializar un jugador correctamente con entradas inválidas inferiores")
    public void testPlayerInitializationInvalidLower() {
        player.setX(-100);
        player.setY(-100);
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        assertNotNull(player.getImage());
    }

    @Test
    @DisplayName("Debería inicializar un jugador correctamente con entradas inválidas superiores")
    public void testPlayerInitializationInvalidUpper() {
        player.setX(400);
        player.setY(400);
        assertEquals(Commons.BOARD_WIDTH - player.getWidth(), player.getX());
        assertEquals(Commons.BOARD_HEIGHT, player.getY());
        assertNotNull(player.getImage());
    }

    @Test
    @DisplayName("Debería mover el jugador a la izquierda")
    public void testPlayerMoveLeft() {
        player.setdx(-2);
        player.act();
        assertEquals(268, player.getX());
    }

    @Test
    @DisplayName("Debería mover el jugador a la derecha")
    public void testPlayerMoveRight() {
        player.setdx(2);
        player.act();
        assertEquals(272, player.getX());
    }

    @Test
    @DisplayName("Debería quedarse en el límite izquierdo de la pantalla")
    public void testPlayerMoveLeftBoundary() {
        player.setX(0);
        player.setdx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    @DisplayName("Debería quedarse en el límite derecho de la pantalla")
    public void testPlayerMoveRightBoundary() {
        player.setX(Commons.BOARD_WIDTH - player.getWidth()); //El límite derecho BOARD_WIDTH - player.width que es 343
        player.setdx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH - player.getWidth(), player.getX());
    }


    @Test
    @DisplayName("Debería pulsar la tecla izquierda")
    public void testPlayerPressedKeyLeft() {
        KeyEvent leftKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyPressed(leftKeyPressed);
        assertEquals(-2, player.getdx());
    }

    @Test
    @DisplayName("Debería pulsar la tecla derecha")
    public void testPlayerPressedKeyRight() {
        KeyEvent rightKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyPressed(rightKeyPressed);
        assertEquals(2, player.getdx());
    }

    @Test
    @DisplayName("Debería detener el movimiento del jugador hacia la izquierda")
    public void testPlayerStopMoveLeft() {
        KeyEvent leftKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyPressed(leftKeyPressed);
        player.act();
        KeyEvent leftKeyReleased = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        player.keyReleased(leftKeyReleased);
        player.act();
        assertEquals(0, player.getdx());
    }

    @Test
    @DisplayName("Debería detener el movimiento del jugador hacia la derecha")
    public void testPlayerStopMoveRight() {
        KeyEvent rightKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyPressed(rightKeyPressed);
        KeyEvent rightKeyReleased = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        player.keyReleased(rightKeyReleased);
        assertEquals(0, player.getdx());
    }

    @Test
    @DisplayName("No debería mover el jugador con las teclas de arriba y abajo")
    public void testPlayerNoMoveUpDown() {
        int initialX = player.getX();
        int initialY = player.getY();

        // Simular la tecla de flecha hacia arriba presionada
        KeyEvent upKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        player.keyPressed(upKeyPressed);
        player.act();
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());

        // Simular la tecla de flecha hacia abajo presionada
        KeyEvent downKeyPressed = new KeyEvent(new java.awt.Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D');
        player.keyPressed(downKeyPressed);
        player.act();
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());
    }
}