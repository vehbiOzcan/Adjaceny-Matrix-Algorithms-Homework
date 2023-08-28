import java.util.Arrays;
public class Graph {

	private int adjacenyMatrix[][]; //Komþuluk matrisi
	private int rowSize;//Düðüm sayýsý yani ayný zamanda satýr ve sütun sayýsý
	private int time; //Adým sayýsý yani ne zaman gidildi
	private int parent[]; //düðümlerin parentýný tutan dizi
	private String color[];//düðümlerin renklerini tutan dizi
	private int turnedGray[];//kaç adýmda gri olduðunu tutan dizi
	private int turnedBlack[];//kaç adýmda siyah olduðunu tutan dizi
	private String visited; //ziyaret edilen düðümleri sýrasýyla tuttuðumuz string
	

	public Graph(String str) {
		initializeAttributes(str); // DFS sýnýfýnda tanýmlanan tüm adjacenyMatrix hariç tüm özellikleri baþlatýyoruz
		System.out.println("----------------------------1.KISIM-------------------------------");
		splitAndMatrix(str); //adjacenyMatrix'i baþlattýk ve oluþturduk
		printAdjacenyMatrix(); // Oluþan Matrisi görmek adýna yazdýrdýk 
		System.out.println("----------------------------2.KISIM-------------------------------");
		printNeigbourNodes(); //Ödevde istenen düðümlerin komþularýný yazdýrdýk
		System.out.println("----------------------------3.KISIM-------------------------------");
		DFS(); // DFS ile dizilere atanacak tüm deðerleri bulduk ve ziyaret edilen düðümleri kayýt ettik
		printDFSValues(); //Sýrasýyla ziyaret edilen düðümleri ve bulduðumuz tüm deðerleri "Düðüm X: Deðeri " olacak þekilde yazdýk
	}
	
	public void DFS() { //DFS'yi (Depth-first-search) baþlatan metot
		
		for(int i = 0 ; i < this.rowSize; i++ ) {
				if(color[i].equals("white")) {
					visited += i +", ";
					DFSVisit(i);
				}
		}
	}
	
	public void DFSVisit(int node) { //Düðümleri gezen, parent ve renk leri atayan metot 
		color[node] = "gray";
		turnedGray[node] = ++time;
		for(int j = 0; j < this.rowSize; j++) {
			if(adjacenyMatrix[node][j] == 1 && color[j].equals("white")) {
				visited += j +", ";
				parent[j] = node;
				DFSVisit(j);
			}
		}
		visited += node +", ";
		color[node] = "black";
		turnedBlack[node] = ++time; 
	}
	
	public void initializeAttributes(String str) { // Özellikleri oluþturan ve baþlangýç deðerlerini atayan metot
		this.rowSize = rowSize(str);
		this.adjacenyMatrix = new int[this.rowSize][this.rowSize];
		this.time = 0;
		this.visited = "";
		this.parent = new int[this.rowSize];
		this.color = new String[this.rowSize];
		this.turnedGray = new int[this.rowSize];
		this.turnedBlack = new int[this.rowSize];
		Arrays.fill(this.color,"white");
	}

	public void splitAndMatrix(String str) { // içerisine dosyadan alýnan stringi parametre olarak alýr ve düðümleri
												// matrise ekler
		String[] lines = str.split("\n");
		String[] node = null;
		for (int i = 0; i < this.rowSize; i++) {
			node = lines[i].split(" ");
			for (int j = 0; j < this.rowSize; j++) {
				this.adjacenyMatrix[i][j] = Integer.parseInt(node[j]);
			}
		}
	}

	public int rowSize(String str) { // Satýr/sutün sayýsýný hesaplar kare olduðu için satýr sayýsý yeterli
		int length = str.split("\n").length;
		return length;
	}
	

	public void printNeigbourNodes() { //Düðümlerin komþularýný yazdýran metot
		String str = "";
		System.out.println("Komþu Düðümler: ");
		for (int i = 0; i < this.rowSize; i++) {
			System.out.print(i+":");
			for (int j = 0; j < this.rowSize; j++) {
				if(this.adjacenyMatrix[i][j] == 1) {
					str += j + ",";
				}
			}
			str = str.equals("") ? "Hiç Komþusu yok": str.substring(0,str.length()-1) + "\n";
			
			System.out.print(str);
			str = "";
		}
	}

	public void printAdjacenyMatrix() { //Komþuluk Matrisin kendisini yazdýran metot
		System.out.println("Komþuluk Matrisi: ");
		for (int i = 0; i < this.rowSize; i++) {
			for (int j = 0; j < this.rowSize; j++) {
				System.out.print(this.adjacenyMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void printArr(int[] arr) { //int dizilerin içeriðini yazdýran metot
		for(int i = 0; i < this.rowSize; i++) {
			System.out.print(" Düðüm "+i+": " +arr[i] + "  ");
		}
		System.out.println();
	}
	
	
	public void printArr(String[] arr) { //String dizilerin içeriðini yazdýran metot
		for(int i = 0; i < this.rowSize; i++) {
			System.out.print(" Düðüm "+i+": " +arr[i] + "  ");
		}
		System.out.println();
	}
	
	public void printNodes(int[][] arr) { //düðümleri yazdýran metot
		for(int i = 0 ; i < this.rowSize; i++ ) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public void printDFSValues() { // DFS ile sýrasýyla ziyaret edilen düðümleri ve  
		//bulunan color, parent, adým sayýlarýný tutan dizilerin içeriklerini yazdýran metot
		//Bu metot " Düðüm X: Deðeri " þeklinde tüm dizilerin içeriðini düðüm karþýlýklarý ile yazdýrýr
		System.out.print("Graftaki Düðümler: ");
		printNodes(this.adjacenyMatrix);
		System.out.println();
		
		System.out.println("Ziyaret Edilen Düðümler Sýrasýyla: ");
		System.out.println(this.visited);
		System.out.println();
		
		
		System.out.print("Renk Dizisi: ");
		printArr(this.color);
		System.out.println();
		
		System.out.print("Parent Dizisi: ");
		printArr(this.parent);
		System.out.println();
		
		System.out.print("Kaç Adýmda Gri Oldu: ");
		printArr(this.turnedGray);
		System.out.println();
		
		System.out.print("Kaç Adýmda Siyah Oldu: ");
		printArr(this.turnedBlack);
		System.out.println();
		
	}
	
	
}
