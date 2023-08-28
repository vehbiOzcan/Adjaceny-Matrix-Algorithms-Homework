import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	//Bu program ödevde istenen tüm iþlemleri gerçekleþtirmektedir. 
	//Çýktý detaylý olduðu için ve tamamýný görebilmek için konsol ekranýný büyütünüz veya scroll ediniz! 
	public static void main(String[] args) {
		String str = fileRead("./src/graf.txt"); //Dosyamýzý okuduk ve yapýsýný bozmadan kullanýlmasý üzere 
		//Asýl iþlemlerin yapýldýðý Graph sýnýfýmýzýn yapýcý (constructor) metoduna parametre olarak verdik
		
		Graph graph = new Graph(str); // Tüm iþlemlerin yapýldýðý sýnýf 
		
	}
	
	public static String fileRead(String path) { //Dosyamýzý okuyan metot parametre olarak dosya yolunu alýr
		
		String line = "";
		String str = "";
		try {
			FileReader file = new FileReader(path);
			BufferedReader br = new BufferedReader(file);
			
			line = br.readLine();
			while(line != null) {
				str += line;
				line = br.readLine();
				if(line != null) {
					str += "\n";
				}
			}
			file.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}

}
