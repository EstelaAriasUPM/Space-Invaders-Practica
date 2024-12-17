package main;

import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; 
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board extends JPanel {

    private static Logger logger = LoggerFactory.getLogger(Board.class);

    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;

    private int direction = -1;

    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/main/resources/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;

    public Player getPlayer() {
        return this.player;
    }

    public List<Alien> getAliens(){
        return this.aliens;
    }

    public Shot getShot() {
        return this.shot;
    }

    /**
     * Inicializa el tablero y comienza la partida
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    public Board() {

        initBoard();
        //gameInit(); //Duplicicdad de código (?)
    }
    /**
     * Inicializa un nuevo tablero con las dimensiones predefinidas, le asigna un fondo de color negro, inicializa el contador de juego e inicia la partida.
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
        logger.info("Termina de ejecutarse initBoard y se llama al metodo gameInit()");
    }

    /**
     * Incializa la partida.
     * Crea las filas de alienígenas, asignando a cada uno su posición inicial
     * */
    private void gameInit() {

        this.aliens = new ArrayList<>();

/*      Bucle original, no sabiamos si considerarlo como error, inicializaba los aliens 
        pegados al borde izquierdo, lo que hacia que una vez arreglado el programa automaticamente
        cambiase la direccion y bajasen.

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {

                var alien = new Alien(Commons.ALIEN_INIT_Y + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i);
                this.aliens.add(alien);
            }
        }
*/
     // Bucle modificado, inicializa los aliens en una posicion mas centrada del eje X
     for (int i = 0; i <4; i++) {
            for (int j = 0; j <6; j++) {

            var alien = new Alien(Commons.ALIEN_INIT_X + 18 * j,
                    Commons.ALIEN_INIT_Y + 18 * i);
            this.aliens.add(alien);
        }
    }
        this.player = new Player();
        this.shot = new Shot();
    }
    /**
     * Genera gráficamente los aliens en la interfaz en las posiciones indicadas.
     * Si el alien es disparado, ejecuta la acción correspondiente (explota y desaparece de la pantalla)
     * NO ES NECESARIO PROBAR EL MÉTODO MEDIANTE PRUEBAS UNITARIAS
     *  */
    private void drawAliens(Graphics g) {

        for (Alien alien : this.aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.setDying(false);
            }
        }
    }
    /**
     * Genera gráficamente el jugador en la interfaz en las posiciones indicadas.
     * Si el jugador es disparado, el jugador muere y termina
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    private void drawPlayer(Graphics g) {

        if (this.player.isVisible()) {

            g.drawImage(this.player.getImage(), this.player.getX(), this.player.getY(), this);
        }

        if (this.player.isDying()) {

            this.player.die();
            inGame = false;
        }
    }
    /**
     * Genera gráficamente los disparos en las posiciones indicadas
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    private void drawShot(Graphics g) {

        if (this.shot.isVisible()) {

            g.drawImage(this.shot.getImage(), this.shot.getX(), this.shot.getY(), this);
        }
    }
    /**
     * Genera gráficamente las explosiones de aliens
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    private void drawBombing(Graphics g) {

        for (Alien a : this.aliens) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }
    /**
     * Actualiza los componentes de la interfaz después de que se ejecute una acción
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    /**
     * Genera y coloca todos los elementos en la interfaz gráfica.
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    /**
     * Genera en la interfaz un mensaje indicando que se ha perdido la partida :(
     * NO ES NECESARIO PROBAR ESTE MÉTODO MEDIANTE PRUEBAS UNITARIAS
     * */
    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.BOARD_WIDTH / 2);
    }
    /**
     * Actualiza el estado del juego de acuerdo a las teclas pulsadas.
     * Si se han destruido todos los alienígenas, el juego finaliza la partida.
     * Si no se han destruido, actualiza el estado del juego.
     * */
    public void update() {
        // Original: if (deaths == Commons.CHANCE){ En el enunciado indica que se gana matando a todos los aliens
        // Corrección:
        if (getDeaths() == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {
            logger.info("Se han destruido todos los aliens (24 para ganar). El juego ha terminado.");
            inGame = false;
            logger.info("Se ha actualizado el estado del juego a 'inGame = false'");
            timer.stop();
            message = "Game won!";
            logger.info("Se ha actualizado el mensaje de la interfaz a 'Game won!'");
        } else {
        	this.player.act();
        	update_shots();
        	update_aliens();
        	update_bomb();
        }
    }
    /**
     * Actualiza el estado de los disparos a los alienígenas.
     * Comprueba la posición del alien y del disparo realizado y, si coinciden, activa la animación de explosión del alienígena, lo elimina del tablero y aumenta en uno el contador de alienígenas derribados (deaths) en uno.
     * */
    public void update_shots() {
        if (this.shot.isVisible()) {
            int shotX = this.shot.getX();
            int shotY = this.shot.getY();

            for (Alien alien : this.aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && this.shot.isVisible() ) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {
                        
                        logger.info("El disparo ha alcanzado a un alien en la posicion: " + alien.getX() + ", " + alien.getY());
                        var ii = new ImageIcon(explImg);
                        
                        // Controlo que la imagen del alien sea distinta a la de la explosión para que no contabilice más de una vez la misma muerte
                        if(alien.getImage() != ii.getImage()){
                            alien.setImage(ii.getImage());
                            logger.info("Se ha cambiado la imagen del alien por la de la explosion");
                            alien.setDying(true);
                            logger.info("Se ha actualizado el estado del alien a dying = true");
                            /* deaths--; */ // ERROR: Debería ser deaths++
                            deaths++; //CORRECCIÓN
                            logger.info("Se ha actualizado el contador de muertes a " + deaths);
                            this.shot.die();
                            logger.info("El disparo ha muerto");

                            // Se ha añadido este fragmento de codigo para eliminar el alien 
                            //(por su imagen de explosion) transcurrido un tiempo de 500ms
                            CompletableFuture.runAsync(() -> {
                                try {
                                    Thread.sleep(500);
                                    alien.die();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            }

            int y = this.shot.getY();
            y -= 4;

            if (y < 0) {
                logger.info("El disparo ha alcanzado el borde superior del tablero y procede a morir");
                this.shot.die();
            } else {
                this.shot.setY(y);
            }
        }
    }
    /**
     * Actualiza los el estado de los aliens
     * Mueve el alienígena hacia la izquierda o a la derecha, en función de la dirección indicada (direction=1 izquierda, direction=-1 derecha), y baja todos los aliens una posición hacia abajo (Commons.GO_DOWN)
     * Si los alienígenas alcanzan el borde inferior del tablero, el juego termina y se nos muestra por pantalla el mensaje "Invasion!"
     * */
    public void update_aliens(){
        for (Alien alien : this.aliens) {

            int x = alien.getX();

            // Original(ERROR): if (x <= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
            // Si el alienígena llega al borde derecho del tablero y la dirección no es hacia la izquierda, cambia la dirección a la izquierda
            if (x>= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) { //CORRECCIÓN
            logger.info("Los aliens han llegado al borde derecho del tablero");
            //  direction = 0;//ERROR: Debería ser -1 (izquierda)
                direction = -1; //CORRECCIÓN
                logger.info("Se ha cambiado la direccion de los aliens a la izquierda (direccion= -1) en el eje X");
                Iterator<Alien> i1 = this.aliens.iterator();

                while (i1.hasNext()) {

                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
                logger.info("Los aliens bajan una posicion hacia abajo (eje Y)");
            }

           // Si el alienígena llega al borde izquierdo del tablero y la dirección no es hacia la derecha, cambia la dirección a la derecha 
           if(x<=Commons.BORDER_LEFT && direction != 1){
                logger.info("Los aliens han llegado al borde izquierdo del tablero");

                direction=1;
                logger.info("Se ha cambiado la direccion de los aliens a la derecha (direccion= 1) en el eje X");

                Iterator<Alien> i2 = this.aliens.iterator();

                while (i2.hasNext()) {

                    Alien a = i2.next();
                    // Original(ERROR): a.setX(a.getY() + Commons.GO_DOWN);
                    // Bajan los alienigenas una posición hacia abajo (eje Y)
                    a.setY(a.getY() + Commons.GO_DOWN); //CORRECCIÓN
                }
                logger.info("Los aliens bajan una posición hacia abajo (eje Y)");
            }
        }

        Iterator<Alien> it = this.aliens.iterator();

        while (it.hasNext()) {

            Alien alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    logger.info("Un alien ha llegado al suelo del tablero");
                    inGame = false;
                    logger.info("Se ha actualizado el estado del juego a 'inGame = false'");
                    message = "Invasion!";
                    logger.info("Se ha actualizado el mensaje de la interfaz a 'Invasion!'");
                }

                alien.act(direction);
            }
        }


    }
    /**
     * Actualiza el estado del jugador tras los disparos de los alienígenas.
     * Si el jugador ha disparado a una bomba y acierta, la bomba se destruye.
     * Si la bomba ha llegado al suelo, se destruye y desaparece
     * Si el jugador ha sido alcanzado por una bomba, el jugador cambiará su estado "setDying" a verdadero, y su imagen se cambiará por la animación de explosión
     * */
    public void update_bomb(){
        var generator = new Random();

        for (Alien alien : this.aliens) {

            int shotB = generator.nextInt(15);
            Alien.Bomb bomb = alien.getBomb();
 
            if (shotB == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {
                
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = this.player.getX();
            int playerY = this.player.getY();
            int shotX = this.shot.getX();
            int shotY = this.shot.getY();

            if (this.player.isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {
                    logger.info("El jugador ha sido alcanzado por una bomba");

                    var ii = new ImageIcon(explImg);
                    this.player.setImage(ii.getImage());
                    logger.info("Se ha cambiado la imagen del jugador por la de la explosion");
                   /*  this.player.setDying(false);*/ //ERROR: Debería ser true
                    this.player.setDying(true); //CORRECCIÓN
                    bomb.setDestroyed(true);
                    logger.info("Se ha actualizado el estado de la bomba a 'destruida = true' y el estado del jugador a 'dying = true'");
                }
                //Codigo añadido para eliminar bombas si les alcanza el disparo
                if (shotX == bombX && shotY >= bombY && shotY <= (bombY + Commons.BOMB_HEIGHT)) {
                    logger.info("El disparo ha alcanzado a una bomba");
                    bomb.setDestroyed(true);
                    bomb.setVisible(false);
                    this.shot.setDying(true);
                    this.shot.setVisible(false);
                    logger.info("Se ha actualizado el estado de la bomba a 'destruida = true' y el estado del disparo a 'dying = true'");
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {

                    // Original: bomb.setDestroyed(false);
                    bomb.setDestroyed(true); //CORRECCIÓN
                    logger.info("La bomba ha llegado al suelo y se ha destruido");
                }
            }
        }
    }

    /**
     * FUNCIÓN RELACIONADA CON LA GESTIÓN DE INTERFAZ. NO ES NECESARIO PROBARLA.
     * */
    private void doGameCycle() {

        update();
        repaint();
    }
    /**FUNCIÓN RELACIONADA CON LA GESTIÓN DE INTERFAZ. NO ES NECESARIO PROBARLA.**/
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }
    /**FUNCIÓN RELACIONADA CON LA GESTIÓN DE INTERFAZ. NO ES NECESARIO PROBARLA.**/
    private class TAdapter extends KeyAdapter {
        /**FUNCIÓN RELACIONADA CON LA GESTIÓN DE INTERFAZ. NO ES NECESARIO PROBARLA.**/
        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }
        /**FUNCIÓN RELACIONADA CON LA GESTIÓN DE INTERFAZ. NO ES NECESARIO PROBARLA.**/
        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!shot.isVisible()) {

                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }

    public Dimension getD() {
        return d;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getExplImg() {
        return explImg;
    }

    public void setExplImg(String explImg) {
        this.explImg = explImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
