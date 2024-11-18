package space_invaders.sprites.ShotTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import space_invaders.sprites.Shot;

public class ShotTestCB {
	/**
	 * Las Pruebas de Caja Blanca para la clase Shot no tienen sentido porque esta
	 * compuesta de metodos setter y getter que no tienen comportamiento que se
	 * pueda probar. Es decir, no hay nada más que 1 unico caminio
	 */

	@Test
	@DisplayName("shot init CB")
	public void testShotInit() {
		new Shot();
		new Shot(0, 0);
	}
}