package space_invaders.sprites.PlayerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Commons;
import space_invaders.sprites.Player;

public class PlayerTestCB {
	Player player;
	@BeforeEach
	public void init() {
		player = new Player();
	}
	
	   @Test
	    @DisplayName("comprueba act")
	    public void testPlayerAct() {
	        player.setX(400);
	        player.setY(400);
	        assertEquals(Commons.BOARD_WIDTH - player.getWidth(), player.getX());
	        assertEquals(Commons.BOARD_HEIGHT, player.getY());
	        assertNotNull(player.getImage());
	    }
}
