import java.util.*;
import java.io.*;
import java.lang.*;

public class Puzzle15 {

	private static Scanner in = new Scanner(System.in);
	private static final int MAXN = 16;
	public static int[] iniBoard = new int[MAXN];
	public static int[] finBoard = new int[MAXN];
	public static int dflt1[] = {1,2,3,4,5,6,8,12,13,9,0,7,14,11,10,15};
	public static int dflt2[] = {1,2,3,4,13,6,8,12,5,9,0,7,14,11,10,15};
	public static int dfltFin[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};

	public static void clearScreen() {  
    	System.out.print("\033[H\033[2J");  
    	System.out.flush();  
	}  



	public static void main(String[] args){
		//-------------MAIN MENU-----------------------------------
		clearScreen();
		System.out.println("\n\n\n\n");
		System.out.println("\t██████╗ ██╗   ██╗███████╗███████╗██╗     ███████╗     ██╗███████╗");
		System.out.println("\t██╔══██╗██║   ██║╚══███╔╝╚══███╔╝██║     ██╔════╝    ███║██╔════╝");
		System.out.println("\t██████╔╝██║   ██║  ███╔╝   ███╔╝ ██║     █████╗█████╗╚██║███████╗");
		System.out.println("\t██╔═══╝ ██║   ██║ ███╔╝   ███╔╝  ██║     ██╔══╝╚════╝ ██║╚════██║");
		System.out.println("\t██║     ╚██████╔╝███████╗███████╗███████╗███████╗     ██║███████║");
		System.out.println("\t╚═╝      ╚═════╝ ╚══════╝╚══════╝╚══════╝╚══════╝     ╚═╝╚══════╝");
        System.out.println("\n\n\n\n\n\n\n");


		//-------------INITAL CONFIG-------------------------------

		System.out.println("\t\t\t1) Use the default Inital Boards");
		System.out.println("\t\t\t2) Insert new Initial Board");
		int res = in.nextInt();
		clearScreen();

		if(res==2){
			for(int i=0; i<MAXN; i++)
				iniBoard[i]=in.nextInt();

			System.out.println("New initial board successfully created!");
		}

		else{
			System.out.println("> 1) Use default Initial Board #1:");
			Config dflt1Conf = new Config(dflt1);
			dflt1Conf.printBoard();

			System.out.println("> 2) Use default Initial Board #2:");
			Config dflt2Conf = new Config(dflt2);
			dflt2Conf.printBoard();
			res = in.nextInt();
			clearScreen();

			switch(res){
				case 1 :
					for(int i=0; i<MAXN; i++)
						iniBoard[i]=dflt1[i];
					System.out.println("Default board #1 successfully selected!");
					break;

				case 2 :
					for(int i=0; i<MAXN; i++)
						iniBoard[i]=dflt2[i];
					System.out.println("Default board #2 successfully selected!");
					break;

				default :
					System.out.println("Invalid Option!");
			}
		}


		//-------------FINAL CONFIG-------------------------------

		System.out.println("1) Use default Final Board");
		System.out.println("2) Insert new Final Board");
		res = in.nextInt();
		clearScreen();

		if(res==2){
			for(int i=0; i<MAXN; i++)
				finBoard[i]=in.nextInt();

			System.out.println("New final board successfully created!");
		}

		else{
			for(int i=0; i<MAXN; i++)
						finBoard[i]=dfltFin[i];
		}

		clearScreen();
		System.out.println("Initial Board:");
		Config iniConf = new Config(iniBoard);
		iniConf.printBoard();
		System.out.println("Final Board:");
		Config finConf = new Config(finBoard);
		finConf.printBoard();


		//-------------CHECK SOLVABILITY-------------------------------

		if(!SearchAlgs.isSolvable(iniConf,finConf)){
			System.out.println("ERROR! It is impossible to reach a solution");
			System.exit(0);
		}

		//-------------SEARCH ALGORITHM SELECTION------------------------------- 

		System.out.println("1) DFS - Depth First Search");
		System.out.println("2) BFS - Breadth First Search");
		System.out.println("3) IDFS - Iterative DFS");
		System.out.println("4) A* - A-Star");
		System.out.println("5) Greedy");
		res= in.nextInt();
		System.out.println("Indique o limite de profundidade a usar");
		int maxDepth= in.nextInt();
		clearScreen();
		System.out.println("Initial Board:");
		iniConf.printBoard();
		System.out.println("Final Board:");
		finConf.printBoard();
		long iniClock=0;
		switch(res){
			case 1 :
				iniClock = System.nanoTime();
				SearchAlgs.DFS(iniConf,finConf,maxDepth);
				break;
			case 2 :
				iniClock = System.nanoTime();
				SearchAlgs.BFS(iniConf,finConf,maxDepth);
				break;
			case 3 :
				iniClock = System.nanoTime();
				SearchAlgs.IDFS(iniConf,finConf,maxDepth);
				break;
			case 4 :
				System.out.println("1) Number of pieces out of their final poisition");
				System.out.println("2) Manhattan Distance - Sum of the distance of all pieces from their final position");
				res= in.nextInt();
				iniClock = System.nanoTime();
				SearchAlgs.ASTAR(iniConf,finConf,maxDepth,res);
				break;
			case 5 :
				System.out.println("1) Number of pieces out of their final position");
				System.out.println("2) Manhattan Distance - Sum of the distance of all pieces from their final position");
				res= in.nextInt();
				iniClock = System.nanoTime();
				SearchAlgs.GREEDY(iniConf,finConf,maxDepth,res);
				break;
			default :
				System.out.println("Invalid Option!");
		}
		long finClock= System.nanoTime() - iniClock;
		float finClockSecs= (float) finClock/1000000000;
		//System.out.println("Running Time: " + finClock + " nanoseconds");
		System.out.printf("Running Time: %.4f seconds\n" , finClockSecs);
	}				
}



/*
  /$$$$$$                                          /$$        /$$$$$$  /$$                    
 /$$__  $$                                        | $$       /$$__  $$| $$                    
| $$  \__/  /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$| $$$$$$$ | $$  \ $$| $$  /$$$$$$   /$$$$$$$
|  $$$$$$  /$$__  $$ |____  $$ /$$__  $$ /$$_____/| $$__  $$| $$$$$$$$| $$ /$$__  $$ /$$_____/
 \____  $$| $$$$$$$$  /$$$$$$$| $$  \__/| $$      | $$  \ $$| $$__  $$| $$| $$  \ $$|  $$$$$$ 
 /$$  \ $$| $$_____/ /$$__  $$| $$      | $$      | $$  | $$| $$  | $$| $$| $$  | $$ \____  $$
|  $$$$$$/|  $$$$$$$|  $$$$$$$| $$      |  $$$$$$$| $$  | $$| $$  | $$| $$|  $$$$$$$ /$$$$$$$/
 \______/  \_______/ \_______/|__/       \_______/|__/  |__/|__/  |__/|__/ \____  $$|_______/ 
                                                                           /$$  \ $$          
                                                                          |  $$$$$$/          
                                                                           \______/           
*/
//GS -> GENERAL SEARCH
//PC -> PATH COST
//FS -> FINAL SPOT

class SearchAlgs{

	private static final int MAXN = 16; 
	static int genNodes;
	static int visNodes;


	public static int getInvs(int[][] board){
		int nInvs = 0;
		int[] arr = new int[MAXN]; 
		
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				arr[i*4 + j] = board[i][j];
			}
		}

		for(int i=0; i<MAXN; i++){
			nInvs+=arr[i];
			for(int j=0; j<=i; j++){
				if(arr[i] >= arr[j] && arr[i]!=0 && arr[j]!=0)
					nInvs--;
			}
		}
		return nInvs;
	}

	public static boolean isSolvable(Config iniConf, Config finConf){
		return((getInvs(iniConf.getBoard())%2 == 0) == (Math.abs(iniConf.getvoidN()-4)%2 == 1)) == ((getInvs(finConf.getBoard())%2 == 0) && (Math.abs(finConf.getvoidN()-4)%2 == 1));
	}

	public static int[] getFS(Config finBoard, int value){
		int[] fin= new int[2];

		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(finBoard.getBoard()[i][j]==value){
					fin[0]=i;
					fin[1]=j;
					return fin;				
				}
			}
		}
		return fin;
	}

	public static int getHeuri1(Node cur, Config finBoard){
		int oop= 0; //out of place

		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(cur.getConfig().getBoard()[i][j]!=finBoard.getBoard()[i][j])
					oop++;
			}
		}
		return oop;
	}

	public static int getHeuri2(Node cur, Config finBoard){
		int place= 0;
		int ct= 0;

		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				int value= cur.getConfig().getBoard()[i][j];
				int[] fin= getFS(finBoard, value);
				place++;

				if(value!=0 && value!=place)
					ct+= Math.abs(i-fin[0]) + Math.abs(j-fin[1]);
			}
		}
		return ct;
	}

	public static void PC_ASTAR(Node[] arr, Config finBoard, int heuri){
		switch(heuri) {
			case 1 :
				for(int i=0; i<4 && arr[i]!=null; i++)
					arr[i].setPathCost(arr[i].getDepth() + getHeuri1(arr[i],finBoard));
				break;
			case 2 :
				for(int i=0; i<4 && arr[i]!=null; i++)
					arr[i].setPathCost(arr[i].getDepth() + getHeuri2(arr[i],finBoard));
				break;
			default :
				System.out.println("Invalid Option!");
		}
	}

	public static void PC_GREEDY(Node[] arr, Config finBoard, int heuri){
		switch(heuri) {
			case 1 :
				for(int i=0; i<4 && arr[i]!=null; i++)
					arr[i].setPathCost(getHeuri1(arr[i],finBoard));
				break;
			case 2 :
				for(int i=0; i<4 && arr[i]!=null; i++)
					arr[i].setPathCost(getHeuri2(arr[i],finBoard));
				break;
			default :
				System.out.println("Invalid Option!");
		}
	}

	public static String GS_DFS(Config iniConf, Config finConf, int maxDepth){
		Node iniNode= new Node(iniConf);
		genNodes++;
		//System.out.println("a calcular...");

		Node[] descList = new Node[4];
		Set<List<Integer>> hashSet = new HashSet<List<Integer>>();
		Stack<Node> q= new Stack<Node>();
		q.push(iniNode);
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0 ; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++)
				l.add(iniNode.getConfig().getBoard()[i][j]);
		hashSet.add(l);
		//iniNode.getConfig().printBoard();

		while(!q.empty()){
			Node out= q.pop();
			//out.getConfig().printBoard();
			visNodes++;

			if(out.getConfig().equals(finConf)){
				System.out.println("Solution found!");
				System.out.println("Depth: " + out.getDepth());
				String wae = out.mkPath();
				return wae;
			}
			if(out.getDepth() < maxDepth){
			//	System.out.println(out.getMove());
				out.mkDescendants(hashSet,descList);

				for(int i=0; i<4 && descList[i]!=null; i++){
					genNodes++;
					q.push(descList[i]);
				}
			}
		}
		return "Solution not found!";
	}

	public static String GS_BFS(Config iniConf, Config finConf, int maxDepth){
		Node iniNode= new Node(iniConf);
		int depth= 0;
		genNodes++;
		//System.out.println("a calcular...");

		Node[] descList = new Node[4];
		Set<List<Integer>> hashSet = new HashSet<List<Integer>>();
		Queue<Node> q= new LinkedList<Node>();
		q.add(iniNode);
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0 ; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++)
				l.add(iniNode.getConfig().getBoard()[i][j]);
		hashSet.add(l);
		//iniNode.getConfig().printBoard();

		while(q.size()!=0){
			Node out= q.remove();
			//out.getConfig().printBoard();
			visNodes++;

			if(out.getConfig().equals(finConf)){
				System.out.println("Solution found!");
				System.out.println("Depth: " + out.getDepth());
				String wae = out.mkPath();
				return wae;
			}

			if(out.getDepth() < maxDepth){
				//	System.out.println(out.getMove());
				out.mkDescendants(hashSet,descList);

				for(int i=0; i<4 && descList[i]!=null; i++){
					genNodes++;
					q.add(descList[i]);
				}
			}

		}
		return "Solution not found!";
	}

	public static String GS_LDFS(Config iniConf, Config finConf, int maxDepth){
		Node iniNode= new Node(iniConf);
		genNodes++;
		Node[] descList = new Node[4];
		Stack<Node> q= new Stack<Node>();
		q.push(iniNode);

		while(!q.empty()){
			Node out= q.pop();
			visNodes++;

			if(out.getConfig().equals(finConf)){
				//System.out.println("Solution found!");
				//System.out.println("Depth: " + out.getDepth());
				String wae = out.mkPath();
				return wae;
			}

			if(out.getDepth() < maxDepth){
				out.mkDescendants(descList);
				for(int i=0; i<4 && descList[i]!=null; i++){
					genNodes++;
					q.push(descList[i]);
				}
			}
		}
		return "Solution not found!";
	}

	public static String GS_ASTAR(Config iniConf, Config finConf, int maxDepth, int heuri){
		Node iniNode= new Node(iniConf);
		genNodes++;
		Node[] descList = new Node[4];
		PriorityQueue<Node> prioQ = new PriorityQueue<Node>();
		prioQ.add(iniNode);

		while(prioQ.size()!=0){
			Node out= prioQ.poll();
			//System.out.println(out.getPathCost());
			visNodes++;

			if(out.getConfig().equals(finConf)){
				System.out.println("Solution found!");
				System.out.println("Depth: " + out.getDepth());
				String wae = out.mkPath();
				return wae;
			}
			
			if(out.getDepth() < maxDepth){
				out.mkDescendants(descList);
				PC_ASTAR(descList, finConf, heuri);

				for(int i=0; i<4 && descList[i]!=null; i++){
					genNodes++;
					//System.out.println(descList[i].getPathCost());
					prioQ.add(descList[i]);
				}
			}
		}
		return "Solution not found!";
	}

	public static String GS_GREEDY(Config iniConf, Config finConf, int maxDepth, int heuri){
		Node iniNode= new Node(iniConf);
		genNodes++;
		Node[] descList = new Node[4];
		PriorityQueue<Node> prioQ = new PriorityQueue<Node>();
		prioQ.add(iniNode);

		while(prioQ.size()!=0){
			Node out= prioQ.poll();
			visNodes++;

			if(out.getConfig().equals(finConf)){
				System.out.println("Solution found!");
				System.out.println("Depth: " + out.getDepth());
				String wae = out.mkPath();
				return wae;
			}
			
			if(out.getDepth() < maxDepth){
				out.mkDescendants(descList);
				PC_GREEDY(descList, finConf, heuri);

				for(int i=0; i<4 && descList[i]!=null; i++){
					genNodes++;
					//System.out.println(descList[i].getPathCost());
					prioQ.add(descList[i]);
				}
			}

		}
		return "Solution not found!";
	}

	public static void DFS(Config iniConf, Config finConf, int maxDepth){
		genNodes = visNodes = 0;
		long iniUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		String wae= GS_DFS(iniConf,finConf,maxDepth);
		long finUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		if(wae.equals("Solution not found!"))
			System.out.println(wae);
		else
			System.out.println("Steps: " + wae);

		long memSize= (finUsedMem - iniUsedMem) /1024;
		System.out.println("Generated Nodes: " + genNodes);
		System.out.println("Visited Nodes: " + visNodes);
		System.out.println("Memory Spent: " + memSize + " KB");

	}

	public static void BFS(Config iniConf, Config finConf, int maxDepth){
		genNodes = visNodes = 0;
		long iniUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		String wae= GS_BFS(iniConf,finConf,maxDepth);
		long finUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		if(wae.equals("Solution not found!"))
			System.out.println(wae);
		else
			System.out.println("Steps: " + wae);

		long memSize= (finUsedMem - iniUsedMem) /1024;
		System.out.println("Generated Nodes: " + genNodes);
		System.out.println("Visited Nodes: " + visNodes);
		System.out.println("Memory Spent: " + memSize + " KB");

	}

	public static void IDFS(Config iniConf, Config finConf, int maxDepth){
		long iniUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		for(int i=1; i<=maxDepth; i++){
			genNodes = visNodes = 0;
			String wae= GS_LDFS(iniConf,finConf,i);
		
			if(!wae.equals("Solution not found!")){
				System.out.println("Solution found: " + wae);
				System.out.println("Depth: " + i);
				break;
			}	
		}
		long finUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long memSize= (finUsedMem - iniUsedMem) /1024;
		System.out.println("Generated Nodes: " + genNodes);
		System.out.println("Visited Nodes: " + visNodes);
		System.out.println("Memory Spent: " + memSize + " KB");
	}

	public static void ASTAR(Config iniConf, Config finConf, int maxDepth, int heuri){
		genNodes = visNodes = 0;
		long iniUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		String wae= GS_ASTAR(iniConf,finConf,maxDepth,heuri);
		long finUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(wae.equals("Solution not found!"))
			System.out.println(wae);
		else
			System.out.println("Steps: " + wae);
		
		long memSize= (finUsedMem - iniUsedMem) /1024;
		System.out.println("Generated Nodes: " + genNodes);
		System.out.println("Visited Nodes: " + visNodes);
		System.out.println("Memory Spent: " + memSize + " KB");

	}

	public static void GREEDY(Config iniConf, Config finConf, int maxDepth, int heuri){
		genNodes = visNodes = 0;
		long iniUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		String wae= GS_GREEDY(iniConf,finConf,maxDepth,heuri);
		long finUsedMem= Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(wae.equals("Solution not found!"))
			System.out.println(wae);
		else
			System.out.println("Steps: " + wae);

		//int memSize= genNodes.BYTES;
		//int memSize= (genNodes*sizeof(Node))/1024;
		long memSize= (finUsedMem - iniUsedMem) /1024;
		System.out.println("Generated Nodes: " + genNodes);
		System.out.println("Visited Nodes: " + visNodes);
		System.out.println("Memory Spent: " + memSize + " KB");

	}
}



/*

 /$$   /$$                 /$$          
| $$$ | $$                | $$          
| $$$$| $$  /$$$$$$   /$$$$$$$  /$$$$$$ 
| $$ $$ $$ /$$__  $$ /$$__  $$ /$$__  $$
| $$  $$$$| $$  \ $$| $$  | $$| $$$$$$$$
| $$\  $$$| $$  | $$| $$  | $$| $$_____/
| $$ \  $$|  $$$$$$/|  $$$$$$$|  $$$$$$$
|__/  \__/ \______/  \_______/ \_______/
 */                                       
                                        
                                        
class Node implements Comparable<Node>{
	public Config board;
	private Node parent;
	private char move;
	private int depth;
	private int pathCost;
	private Node[] children = new Node[4];

	public Node(){
		board = new Config();
		parent = null;
		move = 's';
		depth = 0;
		pathCost = 0;
		for(int i=0; i<4; i++)
			children[i] = null; 
	}

	public Node(int[] arr){
		board = new Config(arr);
		parent = null;
		move = 's';
		depth = 0;
		pathCost = 0;
		for(int i=0; i<4; i++)
			children[i] = null; 
	}

	public Node(Config _board){
		this.board = _board;
		parent = null;
		move = 's';
		depth = 0;
		pathCost = 0;
		for(int i=0; i<4; i++)
			children[i] = null; 
	}

	public Node(Node nd, char mv){
		board = new Config(nd.getConfig());
		board.move(mv);
		parent = nd;
		move = mv;
		depth = nd.getDepth()+1;
		pathCost = 0;
		for(int i=0; i<4; i++)
			children[i] = null; 
	}

	public int compareTo(Node a){
		return (this.getPathCost() - a.getPathCost() );
	}

	public void setPathCost(int cost){
		this.pathCost= cost;
	}


	public Config getConfig(){
		return board;
	}

	public Node getParent(){
		return this.parent;
	}

	public char getMove(){
		return this.move;
	}

	public int getDepth(){
		return this.depth;
	}

	public int getPathCost(){
		return this.pathCost;
	}

	public Node[] getChildren(){
		return this.children;
	}

	public void printStatus(){
		System.out.println("Depth: " + depth);
		System.out.println("Move: " + move);
		System.out.println("Parent: " + parent);
		board.printBoard();
	}

	public void mkDescendants(Node[] lmoves){
		char[] moves = new char[4];
		this.board.possibleMoves(moves);
		int ct=0;

		for(int i=0; i<4; i++){
			lmoves[i]=null;
		}

		for(int i=0; i<4 && moves[i] != '\u0000'; i++) {
			Node nd = new Node(this,moves[i]);
			//nd.printStatus();
			for(int j=0; j<4; j++){
				if(this.children[j] == null){
					this.children[j] = nd;				
					break;
				}
			}
			lmoves[ct] = nd;
			ct++;
		}
		//System.out.println("HASH: " + hashSet);
	}

	public void mkDescendants(Set<List<Integer>> hashSet, Node[] lmoves){
		char[] moves = new char[4];
		this.board.possibleMoves(moves);

		int ct=0;

		for(int i=0; i<4; i++){
			lmoves[i]=null;
		}

		for(int i=0; i<4 && moves[i] != '\u0000'; i++) {
			Node nd = new Node(this,moves[i]);
			//nd.printStatus();
			if(hashSet != null){
				//System.out.println("HASH:" + hashSet);
				List<Integer> l = new ArrayList<Integer>();
				for (int ix = 0 ; ix < 4 ; ix++)
					for (int jx = 0 ; jx < 4 ; jx++)
						l.add(nd.getConfig().getBoard()[ix][jx]);
				if(!hashSet.add(l))
					continue;
			}
			for(int j=0; j<4; j++){
				if(this.children[j] == null){
					this.children[j] = nd;				
					break;
				}
			}
			lmoves[ct] = nd;
			ct++;
		}
		//System.out.println("HASH: " + hashSet);
	}

	public String mkPath(){
		String path= new String();
		StringBuilder pathRev = new StringBuilder();
		Node nd= this;

		while(nd!=null){
			char mv= nd.getMove();
			switch(mv){
				case 's' :
					path+= "tratS";
					break;

				case 'u' :
					path+= "pu >- ";
					break;		

				case 'd' :
					path+= "nwod >- ";
					break;

				case 'l' :
					path+= "tfel >- ";
					break;

				case 'r' :
					path+= "thgir >- ";
					break;	

				default :
					break;	
			}
			nd= nd.getParent();
		}
		pathRev.append(path);
		return pathRev.reverse().toString();
	}
}



/*

  /$$$$$$                       /$$$$$$  /$$          
 /$$__  $$                     /$$__  $$|__/          
| $$  \__/  /$$$$$$  /$$$$$$$ | $$  \__/ /$$  /$$$$$$ 
| $$       /$$__  $$| $$__  $$| $$$$    | $$ /$$__  $$
| $$      | $$  \ $$| $$  \ $$| $$_/    | $$| $$  \ $$
| $$    $$| $$  | $$| $$  | $$| $$      | $$| $$  | $$
|  $$$$$$/|  $$$$$$/| $$  | $$| $$      | $$|  $$$$$$$
 \______/  \______/ |__/  |__/|__/      |__/ \____  $$
                                             /$$  \ $$
                                            |  $$$$$$/
                                             \______/ 
*/

class Config{

	private static final int MAXN = 16;
	private static final int VOID_SQUARE = 0;  

	private int voidN;
	private int voidM;
	private int[][] board = new int[4][4];

	public Config(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				board[i][j] = i*4 + j + 1;
			}
		}

		voidN = 3; 
		voidM = 3;
		board[3][3] = VOID_SQUARE;
	}

	public Config(int[] arr){
		for(int i=0; i < 4; i++){
			for(int j=0; j < 4; j++){
				board[i][j] = arr[i*4 + j];
				if(arr[i*4 + j] == 0){
					voidN = i;
					voidM = j;
				}
			}
		}
	}

	public Config(Config _config) {
		this.voidN = _config.voidN;
		this.voidM = _config.voidM;
		this.board = new int[4][4];
		for (int i = 0 ; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++)
				this.board[i][j] = _config.board[i][j];
	}

	public boolean equals(Config _config) {
        for (int i = 0 ; i < 4 ; i++)
            for (int j = 0 ; j < 4 ; j++)
                if (this.board[i][j] != _config.board[i][j])
                    return false;
        return true;
    }	

	public int[][] getBoard(){
		return this.board;
	}

	public int getvoidN(){
		return this.voidN;
	}

	public int getvoidM(){
		return this.voidM;
	}

	public void printBoard(){
		for(int i=0; i < 4; i++){
			System.out.print("| ");
			for(int j=0; j < 4; j++){
				if(board[i][j]<10)
					System.out.print(board[i][j] + "  | ");
				else
					System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void possibleMoves(char[] pmoves){
		char moves[] = {'u', 'd', 'l', 'r'};
		int k=0;
		for(int i=0; i<4; i++){
			if(voidN==0 && moves[i]=='u')
				continue;
			else if(voidN==3 && moves[i]=='d')
				continue;
			else if(voidM==0 && moves[i]=='l')
				continue;
			else if(voidM==3 && moves[i]=='r')
				continue;

			pmoves[k] = moves[i];
			k++;
		}	
		return;
	}

	public void move(char move){
		switch(move){
			case 'u' :
				this.board[voidN][voidM]= this.board[voidN-1][voidM];
				this.board[voidN-1][voidM]= 0;
				this.voidN-= 1; 
				break;

			case 'd' :
				this.board[voidN][voidM]= this.board[voidN+1][voidM];
				this.board[voidN+1][voidM]= 0;
				this.voidN+= 1;
				break; 

			case 'l' :
				this.board[voidN][voidM]= this.board[voidN][voidM-1];
				this.board[voidN][voidM-1]= 0;
				this.voidM-= 1;
				break; 

			case 'r' :
				this.board[voidN][voidM]= this.board[voidN][voidM+1];
				this.board[voidN][voidM+1]= 0;
				this.voidM+= 1; 
				break;

			default :
				break;
		}
	}
}