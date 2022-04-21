package board;
import board.cell.Cell;

public class Board{
	private int xSize, ySize;
	private Cell board[][];	


	public Board( int xSize, int ySize ){
		this.xSize = xSize;
		this.ySize = ySize;
		board = new Cell[ xSize ][ ySize ];
		
		for( int j = 0; j < ySize; j++ ){
			for( int i = 0; i < xSize; i++ ){
				board[ i ][ j ] = new Cell( i, j, this.calculateLocation( i, j ) );
			}
		}  
	}

	public boolean isLive( int xPos, int yPos ){
		if( board[ xPos - 1 ][ yPos - 1 ].getState() == Cell.State.LIVE ) return true;
		else return false;
	}

	public int getYSize(){ return ySize;	}

	public void setLiveCell( int x, int y ){
		Cell c = board[ x - 1 ][ y -1 ];
		c.setState( Cell.State.LIVE );
	}

	public String toString(){
		String chain = new String("");

		for( int j = 0; j < ySize; j++ ){
			chain = chain + "-------------------------------" ;
			for( int i = 0; i < xSize; i++ ){
				chain = chain + ( board[ i ][ j ].toString() ) + "\n";
			}
		}

		return chain;  
	}

	public void nextState(){
		meshComunication();
		verifyState();
	}

	private Cell.Location  calculateLocation( int x, int y ){		
		if( x == 0 ){
			if( y == 0 ) 							return Cell.Location.DOWN_LEFT;
			if( y > 0 && y < (ySize - 1) )	 		return Cell.Location.LEFT;
			if( y == ( ySize - 1 ) )				return Cell.Location.UP_LEFT;
		} else if( x > 0 && x < (xSize - 1) ){	
			if( y == 0 ) 							return Cell.Location.DOWN;
			if( y > 0 && y < (ySize - 1) ) 			return Cell.Location.CENTER;
			if( y == (ySize - 1) ) 					return Cell.Location.UP;
		} else if( x == ( xSize - 1 ) ){	
			if( y == 0 ) 							return Cell.Location.DOWN_RIGHT;
			if( y > 0 && y < (ySize - 1) ) 			return Cell.Location.RIGHT;
			if( y == ( ySize - 1 ) ) 				return Cell.Location.UP_RIGHT;
		}
		return Cell.Location.CENTER;
	}

	private void meshComunication(){
		for( int j = 0; j < ySize; j++ ){
			for( int i = 0; i < xSize; i++ ){
				Cell c = board[ i ][ j ];
				c.setLiveNeighbors( 0 );
				
				switch( c.getLocation() ){
					case UP_LEFT: 		comunicationR1( c ); break;
					case UP: 			comunicationR2( c ); break;
					case UP_RIGHT: 		comunicationR3( c ); break;
					case LEFT: 			comunicationR4( c ); break;
					case CENTER: 		comunicationR5( c ); break;
					case RIGHT: 		comunicationR6( c ); break;
					case DOWN_LEFT: 	comunicationR7( c ); break;
					case DOWN: 			comunicationR8( c ); break;
					case DOWN_RIGHT:   	comunicationR9( c ); break;
					default: break;
				}
			}
		}
	}

	private void verifyState(  ){
		for( int j = 0; j < ySize; j++ ){
			for( int i = 0; i < xSize; i++ ){
				Cell c = board[ i ][ j ];
				updateState( c );
			}
		}
	}

	private void updateState( Cell c ){
		if( c.getState() == Cell.State.DEAD && c.getLiveNeighbors() == 3 ){ c.setState( Cell.State.LIVE ); }
		else if( c.getState() == Cell.State.LIVE && ( 3 == c.getLiveNeighbors()  || c.getLiveNeighbors() == 2 ) ){ c.setState( Cell.State.LIVE ); }
		else{ c.setState( Cell.State.DEAD ); }

	}

	private void comunicationR1( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c2 = board[ x + 1 ][ y ];

		Cell c4 = board[ x ][ y - 1 ];

		Cell c5 = board[ x + 1 ][ y - 1 ];


		if( c2.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c4.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}	
	}

	private void comunicationR2( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		
		Cell c1 = board[ x - 1 ][ y ];
		Cell c3 = board[ x + 1 ][ y ];
		// Middle
		Cell c4 = board[ x - 1 ][ y - 1 ];
		Cell c5 = board[ x ][ y - 1 ];
		Cell c6 = board[ x + 1 ][ y - 1 ];

		if( c1.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c3.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c4.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c6.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}

	private void comunicationR3( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		// Up
		Cell c2 = board[ x - 1 ][ y ];
		Cell c5 = board[ x - 1 ][ y - 1 ];
		Cell c6 = board[ x ][ y - 1 ];

		if( c2.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c6.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}

	private void comunicationR4( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c1 = board[ x ][ y + 1 ];
		Cell c2 = board[ x + 1 ][ y + 1 ];
		Cell c5 = board[ x + 1 ][ y ];
		Cell c7 = board[ x ][ y - 1 ];
		Cell c8 = board[ x + 1 ][ y - 1 ];
		

		if( c1.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c2.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c7.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c8.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

	}

	private void comunicationR5( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c1 = board[ x - 1 ][ y + 1 ];
		Cell c2 = board[ x ][ y + 1 ];
		Cell c3 = board[ x + 1 ][ y + 1 ];

		Cell c4 = board[ x - 1 ][ y ];
		Cell c6 = board[ x + 1 ][ y ];

		Cell c7 = board[ x - 1 ][ y - 1 ];
		Cell c8 = board[ x ][ y -1 ];
		Cell c9 = board[ x + 1 ][ y - 1 ];

		if( c1.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c2.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c3.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c4.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c6.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c7.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c8.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c9.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}

	private void comunicationR6( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c2 = board[ x - 1 ][ y + 1 ];
		Cell c3 = board[ x ][ y + 1];
		Cell c5 = board[ x - 1 ][ y ];
		Cell c8 = board[ x - 1 ][ y -1 ];
		Cell c9 = board[ x ][ y - 1 ];

		if( c2.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c3.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c8.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c9.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}

	private void comunicationR7( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c4 = board[ x ][ y + 1 ];
		Cell c5 = board[ x + 1 ][ y + 1 ];
		Cell c8 = board[ x + 1 ][ y ];

		if( c4.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c8.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}

	private void comunicationR8( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c4 = board[ x - 1 ][ y + 1 ];
		Cell c5 = board[ x ][ y + 1 ];
		Cell c6 = board[ x + 1 ][ y + 1 ];
		Cell c7 = board[ x - 1 ][ y ];
		Cell c9 = board[ x + 1 ][ y ];

		if( c4.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c6.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c7.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c9.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}

	private void comunicationR9( Cell c ){
		Integer x = c.getXPosition();
		Integer y = c.getYPosition();

		Cell c5 = board[ x - 1 ][ y + 1 ];
		Cell c6 = board[ x ][ y + 1 ];
		Cell c8 = board[ x - 1 ][ y ];

		if( c5.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c6.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}

		if( c8.getState() == Cell.State.LIVE ){
			int lN = c.getLiveNeighbors() + 1;
			c.setLiveNeighbors( lN );
		}
	}
}
