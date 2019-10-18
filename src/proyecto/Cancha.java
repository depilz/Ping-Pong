package proyecto;

import java.awt.Color;
import java.awt.Graphics;

public class Cancha {

	int red1;
	int red2;
	int green1;
	int green2;
	int blue1;
	int blue2;
	double posX;
	double posY;
	double altoCancha;
	double anchoCancha;
	Color colorFondo;
	
	public Cancha(int colorFondo){
		if(colorFondo == 0) {
			red1 = 30; green1 = 0; blue1 = 30;
			red2 = 90; green2 = 60; blue2 = 90;
		}
		if(colorFondo == 1) {
			red1 = 120; green1 = 0; blue1 = 0;
			red2 = 200; green2 = 80; blue2 = 80;
			}
		if(colorFondo == 2) {
			red1 = 0; green1 = 35; blue1 = 50;
			red2 = 0; green2 = 115; blue2 = 150;
			}
		if(colorFondo == 3) {
			red1 = 50; green1 = 100; blue1 = 20;
			red2 = 50; green2 = 100; blue2 = 20;
			}
		if(colorFondo == 4) {
			red1 = 50; green1 = 100; blue1 = 20;
			red2 = 50; green2 = 100; blue2 = 20;
			}
		this.colorFondo = new Color((int) (2*red1/3 + red2/3), (int) (2*green1/3 + green2/3), (int) (2*blue1/3 + blue2/3));
	}
	
	public void dibujarCancha(Graphics g){
		g.setColor(Color.WHITE);  g.fillRect((int) posX, (int) posY, (int) anchoCancha, (int) altoCancha);
		g.setColor(colorFondo);   g.fillRect((int) posX + 4, (int) posY + 4, (int) anchoCancha - 8, (int) altoCancha - 8);
		g.setColor(Color.WHITE);  g.fillRect((int) posX, (int) (posY + altoCancha/2 - 2), (int) anchoCancha, (int) 4);
		g.setColor(Color.BLACK);  g.drawRect((int) posX, (int) posY, (int) anchoCancha, (int) altoCancha);
		}

	public void calcularPosicionYTamañoImagen(double x){
		double direccion = Math.signum(x - this.posX);
		this.posX = x;
		if(x <= 160 || x >= 590) posY = 393;
		else{
			if(x >= 300) {
				posY = 250 + (x - 300)*5/9;
				this.posX += 5*direccion;
			}
			else posY = 250 + (300 - x)*5/6;
		}
		altoCancha = 500 - posY;
		anchoCancha = altoCancha*2/3;
	}
	public double getX(){
		return this.posX;
	}
	
	public void moverIzquierda(){
		calcularPosicionYTamañoImagen(this.posX - 5);
	}
	
	public void moverDerecha(){
		calcularPosicionYTamañoImagen(this.posX + 5);
	}
	
	public void getPosicionYTamaño(int x, int y, int alto){
		this.posX = x;
		this.posY = y;
		this.altoCancha = alto;
		this.anchoCancha = alto*2/3;
		
	}
	
	public void setColorFondo(double velocidad){
		colorFondo = new Color((int) ((15 - velocidad)/15 * red1 + velocidad * red2 /15), (int) ((15 - velocidad)/15 * green1 + velocidad * green2 /15), (int) ((15 - velocidad)/15 * blue1 + velocidad * blue2 /15));
	}
}
