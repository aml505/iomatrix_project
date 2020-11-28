import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintStream;
import java.net.* ;
import java.io.* ;


import javax.swing.JFrame;

public class Client_Matrix {

	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				JFrame frame = new JFrame("IoRMatrix");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(100, 100, 600, 600);				
				MyMatrix matrix = new MyMatrix();
				frame.add(matrix);
				frame.setVisible(true);
///////////////////////////////////
				InputStream inp = null;
				BufferedReader brinp = null;
				DataOutputStream out = null;

				//
				try {


					System.out.println("IoRMatrix CLIENT Start ...");
					System.out.print("IoRMatrix_C > ");
					Socket client = new Socket("localhost", 1978);
					PrintStream ps = new PrintStream(client.getOutputStream());
					Scanner scan = new Scanner(System.in);

					String s ;


					while(!(s = scan.nextLine()).equals("exit")) {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
						System.out.println(bufferedReader.readLine());
						bufferedReader.close();

						System.out.print("IoRMatrix_C > ");
						ps.println(s);
						matrix.afficher();
						matrix.init();
						//BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
						//String s ;
						//while(!(s = br.readLine()).equals("exit")) {
						//	if(s.equals("d")) { System.out.println("DROITE"); matrix.incX(); System.out.print("IoRMatrix_S > "); }
						//	if(s.equals("g")) { System.out.println("GAUCHE"); matrix.decX(); System.out.print("IoRMatrix_S > "); }
						//	if(s.equals("b")) { System.out.println("BAS"); matrix.incY(); System.out.print("IoRMatrix_S > "); }
						//	if(s.equals("h")) { System.out.println("HAUT"); matrix.decY(); System.out.print("IoRMatrix_S > "); }
						//	if(s.equals("init")) { System.out.println("INIT"); matrix.init(); System.out.print("IoRMatrix_S > "); }
						//	if(s.equals("afficher")) { System.out.println("AFFICHER") ;matrix.afficher();System.out.print("IoRMatrix_S > ");}
						//	if(s.equals("cacher")) { System.out.println("CACHER") ;matrix.cacher();System.out.print("IoRMatrix_S > ");}
						//}
						//matrix.cacher();
					}
					System.out.println("Bye");
					frame.dispose();
					ps.println(s);
					ps.close();
					scan.close();
					client.close();
				}
				catch(Exception e) {}
			}
		}.start();
	}

}

