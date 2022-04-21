package graphics;

import board.Board;

import java.util.concurrent.ThreadLocalRandom;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Figure extends JPanel{
	private int width;
	private int height; 
	private int cellSize;
	private Board board;
	private boolean black = true;


	public void initFigure( int width, int height, int cellSize, Board b ){
		this.width = width;
		this.height = height;
		this.cellSize = cellSize;
		this.board = b;
	}

	public void paintComponent( Graphics g ){
		super.paintComponent( g );
		drawBoard( g );
	}

	private void drawBoard( Graphics g ){
		int y = board.getYSize();
		for( int j = 0; j < height; j += cellSize ){

			int x = 1;

			for( int i = 0; i < width; i += cellSize ){
				
				int min_val = 0;
        		int max_val = 255;
        		ThreadLocalRandom tlr = ThreadLocalRandom.current();
        		int rnd0 = tlr.nextInt(min_val, max_val + 1);
        		int rnd1 = tlr.nextInt(min_val, max_val + 1);
        		int rnd2 = tlr.nextInt(min_val, max_val + 1);

				if( board.isLive( x, y ) ){
					g.setColor( new Color( 255, 255, 255 ) );
					g.fillRect( i, j, i + 8, j + 8 );
				}else{
					g.setColor( new Color( 0, 0, 0 ) );
					g.fillRect( i, j, i + 8, j + 8 );
				}
				x++;
			}

			y--;
		}
	}

	private void meshWitheAndBlack( Graphics g ){
		boolean black = true;

		for( int j = 0; j < height; j += cellSize ){
			for( int i = 0; i < width; i += cellSize ){
				if( black ){
					g.setColor( new Color( 0, 0, 0 ) );
					g.fillRect( i, j, i + 8, j + 8 );
					black = false;				
				}else{
					g.setColor( new Color( 255, 255, 255 ) );
					g.fillRect( i, j, i + 8, j + 8 );
					black = true;				
				}
			}
			if( black )
				black =  false;
			else
				black = true;
		}
	}

}