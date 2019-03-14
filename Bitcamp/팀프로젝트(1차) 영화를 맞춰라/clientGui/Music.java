import java.io.*;
import javazoom.jl.player.*;


public class Music extends Thread{
	String name; 
	private Player player;
	private boolean isLoop;
	private File f;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop){
		this.name=name;
		this.isLoop=isLoop;
		init();
	}
	public void init() {
		try{
			this.isLoop = isLoop;
			if(name.contains("D:\\firstTeamProject\\movie-work\\clientGui\\quizData")) f = new File(name);
			else f = new File("music/"+name);
			fis = new FileInputStream(f);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public int getTime(){
		if(player == null) return 0;
		return player.getPosition();
	}

	public void close(){
		isLoop = false;
		player.close();
		this.interrupt();
	}
	public void run(){
		try{
			do{
				player.play();
				fis = new FileInputStream(f);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	
	}

}
