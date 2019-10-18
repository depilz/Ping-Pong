package proyecto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ElegirCancha extends JPanel implements KeyListener,Runnable {
	
	private final int FPS = 60;
	int central = 0;
	private boolean teclaIzquierda = false;
	private boolean teclaDerecha = false;
	private boolean irIzquierda = false;
	private boolean irDerecha = false;
	private boolean teclaEnter = false;
	private boolean canchaElegida = false;
	private Thread hilo;
	private Juego juego;
	private Musica musica;
	private Cancha cancha[] = new Cancha[5];
	
	public ElegirCancha(){
		setBackground(new Color(140, 140, 160));
		setBorder(new LineBorder(Color.BLACK));
		setBounds(0, 0, 800, 600);
		setLayout(null);
		setFocusable(true);
		addKeyListener(this);
		for(int i = 0; i <= 4; i++)   cancha[i] = new Cancha(i);
		this.start();
		
}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		this.posicionarCanchas();
		while(true){
			if(juego == null){
				if(teclaEnter) {
					for(int i = 0; i <= 4; i++)
					cancha[i].calcularPosicionYTamañoImagen(800);
					canchaElegida = true;
					repaint();	
				}}
				if(!canchaElegida){
					if(teclaDerecha) {
						if(!irDerecha){
							if(central < 4) {
								central++;
								irIzquierda = false;
								irDerecha = true;
								musica.stop();
								
								
							}
						}
					}
					if(teclaIzquierda) {
						if(!irIzquierda){
							if(central > 0){
								central--;
								irIzquierda = true;
								irDerecha = false;
								musica.stop();
								
							}
						}
					}
					if(irDerecha){
						if(cancha[central].getX() > 300){
							for(int i = 0; i <= 4; i++)
								cancha[i].moverIzquierda();
						}
						else {
							musica = new Musica(central);
							musica.start();
							irDerecha= false;
						}
					}
					if(irIzquierda){
						if(cancha[central].getX() < 300){
							for(int i = 0; i <= 4; i++)
								cancha[i].moverDerecha();
						}
						else {
							musica = new Musica(central);
							musica.start();
							irIzquierda = false;	
						}
					}
					if(musica == null){
						musica = new Musica(central);
						musica.start();
					}
					repaint();
				}
			else{
				if(juego == null){
					musica.detenerMusica();
					juego = new Juego(central);
					this.add(juego);
				}
				else{
					if(juego.isJuegoTerminado()){
						juego.getJuegoTerminado();
						this.remove(juego);
						juego = null;
						posicionarCanchas();
						requestFocus();
						canchaElegida = false;
					}
				}
			}
			this.PausarTiempo();
		}
	}
	
	private void start(){
		if(hilo == null){
			hilo = new Thread(this);
			hilo.start();
     }
}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i <= 4; i++)
			cancha[i].dibujarCancha(g);
	}

	public boolean isCanchaElegida(){
		return canchaElegida;
	}
	
	private void posicionarCanchas(){
		for(int i = 4; i >= 0; i--){
			cancha[i].calcularPosicionYTamañoImagen(-71);
			while(cancha[i].getX() < 100){
				for(int a = 4; a >= i; a--){
					cancha[a].moverDerecha();
				}
				this.PausarTiempo();
				repaint();	
			}
		}
		while(cancha[0].getX() < 300){
			for(int i = 0; i <= 4; i++) 
				cancha[i].moverDerecha();
				this.PausarTiempo();
				repaint();	
		}
	}		
	
	private void PausarTiempo(){
		try {Thread.sleep(1000/FPS);}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {	
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
			teclaIzquierda = true;
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			teclaDerecha = true;
		if (arg0.getKeyCode() == KeyEvent.VK_1)
			teclaEnter = true;
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
			teclaIzquierda = false;
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			teclaDerecha = false;
		if (arg0.getKeyCode() == KeyEvent.VK_1)
			teclaEnter = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}
}
