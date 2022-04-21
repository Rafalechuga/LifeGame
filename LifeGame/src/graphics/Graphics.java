package graphics;

import board.Board;
import graphics.Figure;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Graphics extends JFrame{
	private Figure figure;

	public Graphics( int width, int height, int cellSize, Board b ){
		initGUI( width, height, cellSize );
		figure = new Figure();
		figure.initFigure( width, height, cellSize, b  );
		add( figure );
		setVisible( true );
	}

	public void initGUI( int width, int height, int cellSize ){
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setTitle( "Juego de la vida" );
		setSize( width , height + (3*cellSize) );
		getContentPane().setLayout( new BorderLayout() );
		
		setLocationRelativeTo( null );
		setResizable( false );
    }

    public void refreshGUI( int width, int height, int cellSize, Board b ){
    	figure = new Figure();
		figure.initFigure( width, height, cellSize, b  );
		add( figure );
		setVisible( true );
    }
}