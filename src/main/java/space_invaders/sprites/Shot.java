package space_invaders.sprites;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

import main.Commons;

public class Shot extends Sprite {
	Logger	logger	= Logger.getLogger(Shot.class.getName());

	public Shot() {
	}

	/**
	 * Inicializa un nuevo objeto disparo en las coordenadas indicadas
	 * 
	 * @param x coordenada X de la posición del nuevo disparo
	 * @param y coordenada Y de la posición del nuevo disparo
	 */
	public Shot(int x, int y) {

		initShot(x, y);
	}

	/**
	 * Inicializa un nuevo objeto disparo en las coordenadas indicadas y le asigna
	 * la imagen correspondiente en la interfaz
	 * 
	 * @param x coordenada X de la posición del nuevo disparo
	 * @param y coordenada Y de la posición del nuevo disparo Si alguna de las
	 *          coordenadas indicadas es mayor al máximo permitido, se le asignará
	 *          el valor máximo permitido. Si se introducen valores negativos de
	 *          coordenada, se asignará el mínimo permitido (0).
	 */
	private void initShot(int x, int y) {

		var shotImg = "src/main/resources/images/shot.png";
		var ii = new ImageIcon(shotImg);
		setImage(ii.getImage());
		logger.info("Imagen del disparo asignada correctamente");
		int H_SPACE = 6;
		int V_SPACE = 1;

		// Corrección (iniciar shot en valores por fuera de tablero)
		if (x < 0) {
			x = 0;
		} else if (x > Commons.BOARD_WIDTH) {
			x = Commons.BOARD_WIDTH - H_SPACE;
		}
		if (y < 0) {
			y = V_SPACE;
		} else if (y > Commons.BOARD_HEIGHT) {
			y = Commons.BOARD_HEIGHT;
		}

		setX(x + H_SPACE);

		setY(y - V_SPACE);

		logger.info("Disparo creado correctamente en: x: " + getX() + " y: " + getY());

	}
}
