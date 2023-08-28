import java.util.Arrays;
public class Graph {

	private int adjacenyMatrix[][]; //Kom�uluk matrisi
	private int rowSize;//D���m say�s� yani ayn� zamanda sat�r ve s�tun say�s�
	private int time; //Ad�m say�s� yani ne zaman gidildi
	private int parent[]; //d���mlerin parent�n� tutan dizi
	private String color[];//d���mlerin renklerini tutan dizi
	private int turnedGray[];//ka� ad�mda gri oldu�unu tutan dizi
	private int turnedBlack[];//ka� ad�mda siyah oldu�unu tutan dizi
	private String visited; //ziyaret edilen d���mleri s�ras�yla tuttu�umuz string
	

	public Graph(String str) {
		initializeAttributes(str); // DFS s�n�f�nda tan�mlanan t�m adjacenyMatrix hari� t�m �zellikleri ba�lat�yoruz
		System.out.println("----------------------------1.KISIM-------------------------------");
		splitAndMatrix(str); //adjacenyMatrix'i ba�latt�k ve olu�turduk
		printAdjacenyMatrix(); // Olu�an Matrisi g�rmek ad�na yazd�rd�k 
		System.out.println("----------------------------2.KISIM-------------------------------");
		printNeigbourNodes(); //�devde istenen d���mlerin kom�ular�n� yazd�rd�k
		System.out.println("----------------------------3.KISIM-------------------------------");
		DFS(); // DFS ile dizilere atanacak t�m de�erleri bulduk ve ziyaret edilen d���mleri kay�t ettik
		printDFSValues(); //S�ras�yla ziyaret edilen d���mleri ve buldu�umuz t�m de�erleri "D���m X: De�eri " olacak �ekilde yazd�k
	}
	
	public void DFS() { //DFS'yi (Depth-first-search) ba�latan metot
		
		for(int i = 0 ; i < this.rowSize; i++ ) {
				if(color[i].equals("white")) {
					visited += i +", ";
					DFSVisit(i);
				}
		}
	}
	
	public void DFSVisit(int node) { //D���mleri gezen, parent ve renk leri atayan metot 
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
	
	public void initializeAttributes(String str) { // �zellikleri olu�turan ve ba�lang�� de�erlerini atayan metot
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

	public void splitAndMatrix(String str) { // i�erisine dosyadan al�nan stringi parametre olarak al�r ve d���mleri
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

	public int rowSize(String str) { // Sat�r/sut�n say�s�n� hesaplar kare oldu�u i�in sat�r say�s� yeterli
		int length = str.split("\n").length;
		return length;
	}
	

	public void printNeigbourNodes() { //D���mlerin kom�ular�n� yazd�ran metot
		String str = "";
		System.out.println("Kom�u D���mler: ");
		for (int i = 0; i < this.rowSize; i++) {
			System.out.print(i+":");
			for (int j = 0; j < this.rowSize; j++) {
				if(this.adjacenyMatrix[i][j] == 1) {
					str += j + ",";
				}
			}
			str = str.equals("") ? "Hi� Kom�usu yok": str.substring(0,str.length()-1) + "\n";
			
			System.out.print(str);
			str = "";
		}
	}

	public void printAdjacenyMatrix() { //Kom�uluk Matrisin kendisini yazd�ran metot
		System.out.println("Kom�uluk Matrisi: ");
		for (int i = 0; i < this.rowSize; i++) {
			for (int j = 0; j < this.rowSize; j++) {
				System.out.print(this.adjacenyMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void printArr(int[] arr) { //int dizilerin i�eri�ini yazd�ran metot
		for(int i = 0; i < this.rowSize; i++) {
			System.out.print(" D���m "+i+": " +arr[i] + "  ");
		}
		System.out.println();
	}
	
	
	public void printArr(String[] arr) { //String dizilerin i�eri�ini yazd�ran metot
		for(int i = 0; i < this.rowSize; i++) {
			System.out.print(" D���m "+i+": " +arr[i] + "  ");
		}
		System.out.println();
	}
	
	public void printNodes(int[][] arr) { //d���mleri yazd�ran metot
		for(int i = 0 ; i < this.rowSize; i++ ) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public void printDFSValues() { // DFS ile s�ras�yla ziyaret edilen d���mleri ve  
		//bulunan color, parent, ad�m say�lar�n� tutan dizilerin i�eriklerini yazd�ran metot
		//Bu metot " D���m X: De�eri " �eklinde t�m dizilerin i�eri�ini d���m kar��l�klar� ile yazd�r�r
		System.out.print("Graftaki D���mler: ");
		printNodes(this.adjacenyMatrix);
		System.out.println();
		
		System.out.println("Ziyaret Edilen D���mler S�ras�yla: ");
		System.out.println(this.visited);
		System.out.println();
		
		
		System.out.print("Renk Dizisi: ");
		printArr(this.color);
		System.out.println();
		
		System.out.print("Parent Dizisi: ");
		printArr(this.parent);
		System.out.println();
		
		System.out.print("Ka� Ad�mda Gri Oldu: ");
		printArr(this.turnedGray);
		System.out.println();
		
		System.out.print("Ka� Ad�mda Siyah Oldu: ");
		printArr(this.turnedBlack);
		System.out.println();
		
	}
	
	
}
