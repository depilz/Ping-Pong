package proyecto;

import java.awt.Color;
import java.awt.Graphics;

public class Barras {

	int x = 175;
	int y;
	int izquierda = 0;
	int derecha = 0;
	boolean maquina = true;
	
	public Barras(int y){
		this.y = y;
	}
	
	public void dibujarBarra(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 40, 5);
		g.setColor(Color.darkGray);
		g.drawRect(x - 1, y - 1, 41, 7);
	}

	public void mover(int pos){
		if(maquina){
			if(this.x + 30 < pos){
				if(this.x + 80 > pos) {if(Math.random() > 0.4) this.x += 3;}
				else this.x +=5;
			}
			if(this.x + 10 > pos){
				if(this.x - 40 < pos) {if(Math.random() <= 0.4) this.x -= 3;}
				else this.x -=5;
			}
			if(this.x + 20 == pos) this.x += 5*Math.random() - 5*Math.random();
		}
			
	}
	public void moverIzquierda(boolean mover){
		if(mover){
			maquina = false;
			if(this.x > 0)
				this.x = this.x - 7;
		}
	}
	public void moverDerecha(boolean mover){
		if(mover){
			maquina = false;
			if(this.x < 350)
				this.x = this.x + 7;
		}	
	}
	
	public int getX(){
		return this.x;
	}
}