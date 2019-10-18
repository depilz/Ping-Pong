package proyecto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Juego extends JPanel implements KeyListener, Runnable {
	
		private Thread hilo;
		private Barras barra1 = new Barras(520);
		private Barras barra2 = new Barras(40);
		private Pelota pelota = new Pelota();
		private Musica musica;
		private JTextField score1 = new JTextField();
		private JTextField score2 = new JTextField();
		private Cancha cancha;
		private final int FPS = 60;
		private Integer puntaje1 = 0;
		private Integer puntaje2 = 0;
		
		private boolean juegoEmpezado = true;
		private boolean teclaIzquierda = false;
		private boolean teclaDerecha = false;
		private boolean teclaA = false;
		private boolean teclaD = false;
		private boolean juegoPausado = false;
		
		public Juego(int numCancha){
			cancha = new Cancha(numCancha);
			cancha.getPosicionYTamaño(0, 0, 600);
			setBorder(new LineBorder(Color.black));
			setBounds(200, 0, 400, 600);
			setLayout(null);
			setFocusable(true);
			addKeyListener(this);
	
			score1.setEditable(false);
			score1.setText("0");
			score1.setHorizontalAlignment(SwingConstants.CENTER);
			score1.setBounds(15, 10, 30, 30);
			this.add(score1);
			
			score2.setEditable(false);
			score2.setText("0");
			score2.setHorizontalAlignment(SwingConstants.CENTER);
			score2.setBounds(350, 530, 30, 30);
			this.add(score2);
			
			musica = new Musica(numCancha);
			musica.start();  
			this.start();
	}
		
		@Override
		public void run() {
			this.requestFocus();
			while(juegoEmpezado){
				pelota.setVelocidad(musica.velocidadSegunMusica());
				cancha.setColorFondo(musica.velocidadSegunMusica());
				
				barra1.moverIzquierda(teclaIzquierda);
				barra1.moverDerecha(teclaDerecha);
				barra1.mover(pelota.getPosicionBarra());

				barra2.moverIzquierda(teclaA);
				barra2.moverDerecha(teclaD);
				barra2.mover(pelota.getPosicionBarra());
				
				pelota.choquePelotaBarra(barra1.getX(), barra2.getX());
				
				if(pelota.pierdeJugador1()){
					puntaje1++;
					score1.setText((puntaje1.toString()));
				}
				if(pelota.pierdeJugador2()){
					puntaje2++;
					score2.setText((puntaje2.toString()));
				}
				
				pelota.movimientoPelota();
				repaint();
				
				while(juegoPausado){
					try {Thread.sleep(100);}
					catch (InterruptedException e) 
					{e.printStackTrace();}
				}
				try {Thread.sleep(1000/FPS);}
				catch (InterruptedException e) 
				{e.printStackTrace();}
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
			cancha.dibujarCancha(g);
			barra1.dibujarBarra(g);
			barra2.dibujarBarra(g);
			pelota.dibujarPelota(g);
		}

		public boolean isJuegoTerminado(){
			return musica.isMusicaTerminada();
		}
		@Override
		public void keyPressed(KeyEvent teclaApretada) {	
			if (teclaApretada.getKeyCode() == KeyEvent.VK_LEFT)   teclaIzquierda = true;
			if (teclaApretada.getKeyCode() == KeyEvent.VK_RIGHT)  teclaDerecha = true;
			if (teclaApretada.getKeyCode() == KeyEvent.VK_A)      teclaA = true;
			if (teclaApretada.getKeyCode() == KeyEvent.VK_D)      teclaD = true;
			if (teclaApretada.getKeyCode() == KeyEvent.VK_R){
				puntaje1 = 0;
				puntaje2 = 0;
				score1.setText((puntaje1.toString()));
				score2.setText((puntaje2.toString()));
				pelota.reiniciarPelota();
			}
			if (teclaApretada.getKeyCode() == KeyEvent.VK_P)      if(juegoPausado) juegoPausado = false; else juegoPausado = true;
			if (teclaApretada.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
		}

		@Override
		public void keyReleased(KeyEvent teclaSoltada) {
			if (teclaSoltada.getKeyCode() == KeyEvent.VK_LEFT)   teclaIzquierda = false;
			if (teclaSoltada.getKeyCode() == KeyEvent.VK_RIGHT)  teclaDerecha = false;
			if (teclaSoltada.getKeyCode() == KeyEvent.VK_A)      teclaA = false;
			if (teclaSoltada.getKeyCode() == KeyEvent.VK_D)      teclaD = false;
			}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

		public void getJuegoTerminado() {
			juegoEmpezado = false;
		}
}