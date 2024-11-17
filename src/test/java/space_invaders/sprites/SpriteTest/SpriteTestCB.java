package space_invaders.sprites.SpriteTest;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import space_invaders.sprites.Sprite;

public class SpriteTestCB {

	    @Test
	    @DisplayName("sprite CB")
	    public void testSpriteDie() {
	        Sprite sprite = new Sprite();
	        sprite.die();
	        sprite.isVisible();
	        sprite.setVisible(false);
	        sprite.setImage(new ImageIcon("src/main/resources/images/player.png").getImage());
	        sprite.setX(100);
	        sprite.setY(200);
	        sprite.setDying(true);
	        sprite.getY();
	        sprite.getX();
	        sprite.isDying();
	        sprite.getImage();
	    }

}
