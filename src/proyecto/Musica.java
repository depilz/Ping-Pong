package proyecto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Musica extends Thread{
	 
	private double velocidad = 2.1;
	private double tiempo;
	private Player player;
	private int musica;
	private boolean musicaTerminada = false;
    private FileInputStream fis = null;
	public Musica(int musica){
		this.musica = musica;
		try {
	         if(musica == 0) fis = new FileInputStream("songs/Tetris_Theme_(Korobeiniki).mp3");
	         if(musica == 1) fis = new FileInputStream("songs/Taalbi Brothers - Freestyle.mp3");
	         if(musica == 2) fis = new FileInputStream("songs/Robin Schulz - Willst Du (Bootleg).mp3");
	         if(musica == 3) fis = new FileInputStream("songs/Descendent of Shinobi.mp3");
	         if(musica == 4) fis = new FileInputStream("songs/Gold Saucer.mp3");
	         
	         BufferedInputStream bis = new BufferedInputStream(fis);
	         player = new Player(bis);
	    } 
	    catch (JavaLayerException e) {e.printStackTrace();} 
		catch (FileNotFoundException e) {e.printStackTrace();}
	}
		
	public void run(){
		try {
			player.play();
		} 
	    catch (JavaLayerException e) {e.printStackTrace();}
	}
	
	public double velocidadSegunMusica(){
		tiempo = player.getPosition();
		tiempo /= 1000;
		if(musica == 0){
			if(tiempo > 0 && tiempo < 7.6) velocidad = 2.1;
			if(tiempo >= 7.6 && tiempo < 39.8) velocidad += 0.0065;
			if(tiempo >= 39.8 && tiempo < 40.9) velocidad = 1;
			if(tiempo >= 40.9 && tiempo < 55.7) velocidad = 6;
			if(tiempo >= 55.7 && tiempo < 70) velocidad = 8;
			if(tiempo >= 70 && tiempo < 84) velocidad = 3;
			if(tiempo >= 84 && tiempo < 97.9) velocidad = 8;
			if(tiempo >= 97.9 && tiempo < 112.5) velocidad = 11;
			if(tiempo >= 112.5 && tiempo < 126.7) velocidad = 3;
			if(tiempo >= 126.7 && tiempo < 135.8) velocidad = 1;
			if(tiempo >= 135.8 && tiempo < 139) velocidad = 0.03;
			if(tiempo >= 139 && tiempo < 142) velocidad += 0.001;
			if(tiempo >= 142 && tiempo < 177.4) velocidad += 0.002;
			if(tiempo == 177.4) velocidad = 7;
			if(tiempo > 177 && tiempo < 195) velocidad += 0.0079;
			if(tiempo >= 200.3 && tiempo < 205) velocidad = 1;
			if(tiempo > 205) musicaTerminada = true;
		}
		if(musica == 1){
			if(tiempo > 0 && tiempo < 1) velocidad = 4;
			if(tiempo >= 1 && tiempo < 15) velocidad += 0.01;
			if(tiempo >= 49.5 && tiempo < 63.5) velocidad = 8;
			if(tiempo >= 63.5 && tiempo < 104) velocidad = 10;
			if(tiempo >= 104 && tiempo < 118) velocidad = 12;
			if(tiempo >= 118 && tiempo < 144.5) velocidad = 14;
			if(tiempo >= 144.5 && tiempo < 158) velocidad = 10;
			if(tiempo >= 158 && tiempo < 198.5) velocidad = 13;
			if(tiempo >= 198.5 && tiempo < 212) velocidad = 14;
			if(tiempo >= 212) velocidad = 1;
			if(tiempo > 213) musicaTerminada = true;
			}
		
		if(musica == 2){
			if(tiempo > 0 && tiempo < 3) velocidad = 7;
			if(tiempo >= 3 && tiempo < 18) velocidad += 0.005;
			if(tiempo >= 47 && tiempo < 52) velocidad += 0.005;
			if(tiempo >= 386) musicaTerminada = true;
		}	
		
		if(musica == 3){
			velocidad = 8;
			if(tiempo >= 194) musicaTerminada = true;
		}	
		
		if(musica == 4){
			if(tiempo > 0 && tiempo < 15) velocidad = 5;
			if(tiempo >= 15 && tiempo < 25) velocidad += 0.003;
			if(tiempo >= 45 && tiempo < 47) velocidad -= 0.025;
			if(tiempo >= 48 && tiempo < 50) velocidad += 0.05;
			if(tiempo >= 65 && tiempo < 68) velocidad -= 0.02;
			if(tiempo >= 71.5 && tiempo < 74) velocidad += 0.025;
			if(tiempo >= 74 && tiempo < 76.5) velocidad -= 0.035;
			if(tiempo >= 76.5 && tiempo < 78) velocidad += 0.05;
			if(tiempo >= 78 && tiempo < 79) velocidad -= 0.05;
			if(tiempo >= 149) musicaTerminada = true;
		}	
		System.out.println(tiempo + "    " + velocidad*0.7);
		return velocidad*0.7;
	}
	
	public boolean isMusicaTerminada(){
		boolean musicTermin = musicaTerminada;
		musicaTerminada = false;
		return musicTermin;
	}
	
	public void detenerMusica(){
		player.close();
	}
}