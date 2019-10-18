package proyecto;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota {
	double x = 180;
	double y = 250;
	double velocidadX = 0;
	double velocidadY = 2;
	double n;
	int posX = 340;
	int posRandom = 200;
	int i = 0;
	int direccion = 1;
	public Pelota(){
		
	}

	public void dibujarPelota(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval((int)x,(int)y, 20, 20);
//		if(velocidadY >= 15) g.setColor(Color.RED);
//		else g.setColor(new Color((int) (velocidadY*direccion*13.5) + 50, Math.abs(105 - (int) (velocidadY*direccion*7)), Math.abs(250 - (int) ((velocidadY*direccion*15) ))));	
//		g.fillOval((int)x,(int)y, 20, 20);
		g.setColor(Color.black);
		g.drawOval((int) x, (int) y, 20, 20);	
		
	}
	
	public void movimientoPelota(){
		this.choquePelotaMargen();
		this.calcularPosChoquePelotaBarra();
		this.x = this.x + velocidadX;
		this.y = this.y + velocidadY;
	}
	
	
	public void choquePelotaMargen(){
		if(this.x >= 380 || this.x <= 0)
			velocidadX = -velocidadX;
	}
	
	public void choquePelotaBarra(int barra1, int barra2){
		if(this.y <= 520 && this.y >= 500 && this.x + 10 >= barra1 && this.x + 10 <= barra1 + 40){
			if(velocidadY > 0) velocidadY = -velocidadY;
			velocidadX = (this.x - 10 - barra1)/4;
			direccion = -1;
			i++;
			posRandom = (int) (Math.random()*80 + 160);
		} 
		if(this.y <= 40 && this.y >= 20 && this.x + 10 >= barra2 && this.x + 10 <= barra2 + 40){
			if(velocidadY < 0) velocidadY = -velocidadY;
			velocidadX = (this.x - 10 - barra2)/4;
			direccion = 1;
			i++;
			posRandom = (int) (Math.random()*80 + 160);
		}
	}
	
	private int calcularPosChoquePelotaBarra(){
		n = Math.abs((227.5 - (this.y - 45 - 227.5)*direccion) / velocidadY);
		if(((this.x + n*velocidadX - (this.x + n*velocidadX)%380))/380 == 1) posX = 10 + (int)  Math.abs(380 - Math.abs((this.x + n*velocidadX) % 380));		
		else posX = 10 + (int)  Math.abs(((380 * (((this.x + n*velocidadX)/380)) % 2)) - Math.abs((this.x + n*velocidadX) % 380));		
		return posX;
	}
	
	public int getPosicionBarra(){
		i++;
		if(i % 2 == 1) return posX;
		else return posRandom;
	}
	public boolean pierdeJugador1(){
		if(y >= 600){
			direccion = -1;
			reiniciarPelota();
			return true;
		}
		else return false;
	}
	public boolean pierdeJugador2(){
		if(y <= -20){
			direccion = 1;
			reiniciarPelota();
			return true;
		}
		else return false;
	}
	
	public void reiniciarPelota(){
		this.x = 180;
		this.y = 250;
		velocidadX = 0;
		i = (direccion - 1) / (-2);
	}
	
	public void setVelocidad(double velocidad){
		velocidadX *= velocidad * direccion / velocidadY;
		velocidadY = velocidad * direccion;
	}
}
