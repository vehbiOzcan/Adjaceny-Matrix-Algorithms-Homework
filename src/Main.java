import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	//Bu program �devde istenen t�m i�lemleri ger�ekle�tirmektedir. 
	//��kt� detayl� oldu�u i�in ve tamam�n� g�rebilmek i�in konsol ekran�n� b�y�t�n�z veya scroll ediniz! 
	public static void main(String[] args) {
		String str = fileRead("./src/graf.txt"); //Dosyam�z� okuduk ve yap�s�n� bozmadan kullan�lmas� �zere 
		//As�l i�lemlerin yap�ld��� Graph s�n�f�m�z�n yap�c� (constructor) metoduna parametre olarak verdik
		
		Graph graph = new Graph(str); // T�m i�lemlerin yap�ld��� s�n�f 
		
	}
	
	public static String fileRead(String path) { //Dosyam�z� okuyan metot parametre olarak dosya yolunu al�r
		
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
