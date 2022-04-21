
import board.Board;
import board.cell.Cell;
import javax.swing.SwingUtilities;
import java.util.Scanner;

import graphics.Graphics;

public class LifeGame{
	static final int WIDTH = 512;
	static final int HEIGHT = 512;
	static final int CELL_SIZE  = 8;
	static final int STEPS = 150;

	public static void main( String[] args ){
		new LifeGame();
	}

	public LifeGame(){
		Scanner sc =new Scanner( System.in );

		System.out.println( "\n[1] Acorn Game" + 
							"\n[2] Diehard Game" +
							"\n[3] T Game" +
							"\n>");
		int n = sc.nextInt();
		
		switch( n ){
			case 1: acornGame(); break;
			case 2: diehardGame(); break;
			case 3: tGame(); break;
			default: break;
		}
	}

	private void acornGame(){
		Board b = new Board( WIDTH/CELL_SIZE, HEIGHT/CELL_SIZE );
		b.setLiveCell( 31,33 );
		b.setLiveCell( 32,33);
		b.setLiveCell( 32,35 );


		b.setLiveCell( 34,34 );
		b.setLiveCell( 35,33 );
		b.setLiveCell( 36,33 );
		b.setLiveCell( 37,33 );

		Graphics g = new Graphics( WIDTH, HEIGHT, CELL_SIZE, b );

		try
		{ 
			Thread.sleep(2500); 
		} catch(InterruptedException e ) { 
			System.out.println("Thread Interrupted");
		}

		int i = 0;
		while( i < 400 ){
			b.nextState();
			g.refreshGUI( WIDTH, HEIGHT, CELL_SIZE, b );
			try
			{ 
				Thread.sleep(30); 
			} catch(InterruptedException e ) { 
				System.out.println("Thread Interrupted");
			}
			System.out.println( "Iteracion: " + i);

			i++;	
		}
	}
	private void diehardGame(){
		Board b = new Board( WIDTH/CELL_SIZE, HEIGHT/CELL_SIZE );
		b.setLiveCell( 31,33 );
		b.setLiveCell( 32,33);
		b.setLiveCell( 32,32 );


		b.setLiveCell( 36,32 );
		b.setLiveCell( 37,32 );
		b.setLiveCell( 38,32 );
		b.setLiveCell( 37,34 );

		Graphics g = new Graphics( WIDTH, HEIGHT, CELL_SIZE, b );

		try
		{ 
			Thread.sleep(2500); 
		} catch(InterruptedException e ) { 
			System.out.println("Thread Interrupted");
		}

		int i = 0;
		while( i < 150 ){
			b.nextState();
			g.refreshGUI( WIDTH, HEIGHT, CELL_SIZE, b );
			try
			{ 
				Thread.sleep(30); 
			} catch(InterruptedException e ) { 
				System.out.println("Thread Interrupted");
			}
			System.out.println( "Iteracion: " + i);

			i++;	
		}
	}

	public void tGame(){
		Board b = new Board( WIDTH/CELL_SIZE, HEIGHT/CELL_SIZE );
		b.setLiveCell( 31,33 );
		b.setLiveCell( 32,33 );
		b.setLiveCell( 33,32 );
		b.setLiveCell( 32,32);
		b.setLiveCell( 32,31 );


	

		Graphics g = new Graphics( WIDTH, HEIGHT, CELL_SIZE, b );

		try
		{ 
			Thread.sleep(2500); 
		} catch(InterruptedException e ) { 
			System.out.println("Thread Interrupted");
		}

		int i = 0;
		while( i < 1500 ){
			b.nextState();
			g.refreshGUI( WIDTH, HEIGHT, CELL_SIZE, b );
			try
			{ 
				Thread.sleep(30); 
			} catch(InterruptedException e ) { 
				System.out.println("Thread Interrupted");
			}
			System.out.println( "Iteracion: " + i);

			i++;	
		}
	}
}
