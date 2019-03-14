import java.io.*;
import java.util.*;


public class QuizData {

	FileInputStream fis;
	FileOutputStream fos;
	DataInputStream dis;
	DataOutputStream dos;
	
	QuizData(){
		getMovieName();
		posters();
	}
	
	String fName;
	String extention;
	
	File ppFile;
	ArrayList<File[]> files=new ArrayList<>();
	String ost;
	String scenesTip;
	String posters[]=new String[3];
	ArrayList<String> movieName = new ArrayList<>();
	
	void getMovieName(){
		movieName.add("HarryPotter"); 	//0
		movieName.add("PhoneBooth");	//1
		movieName.add("JurassicPark");  //2
		movieName.add("LaLaLand");		//3
		movieName.add("FightClub");		//4	
		movieName.add("TheDarkKnight");	//5
		movieName.add("CrimeCity");		//6	
	}
	
	int movieIndex;
	
	void posters(){
		System.out.println(movieName.size());
		for(int movieIndex=0; movieIndex<movieName.size(); movieIndex++){
			File pFile = new File("quizData" + File.separator + movieName.get(movieIndex));
			ppFile = new File(pFile.getAbsolutePath());
			File [] plist = ppFile.listFiles();
			files.add(plist);
		}
	}
	void make(int count) {
		int pcount=0;
		for(File ppFile : files.get(count)){
			if(ppFile.isFile()){
				fName = ppFile.getPath();
				int i = fName.lastIndexOf(".");
				extention = fName.substring(i+1);
				if(extention.equals("png") || extention.equals("jpg")){	
					posters[pcount++]=ppFile.getPath();
				}else if(extention.equals("mp3") || extention.equals("wav")){
					scenesTip=ppFile.getPath();
					i = fName.lastIndexOf("OST");
					if(i != -1){
						ost=ppFile.getPath();
					}
				}
			}
		}
	}
	void check() {
		for(File[] list: files) {
			for(File file :list)System.out.println(file);
		}
	}
	void checkFiles() {
		for(String fName :posters) {
			System.out.println(fName);
		}
		System.out.println(scenesTip);
		System.out.println(ost);
	}
}