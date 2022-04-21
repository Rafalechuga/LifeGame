package board.cell;

import java.util.Vector;

/*
	1	2	3
	4	5	6
	7	8	9
	
	1: UP_LEFT
	2: UP
	3: UP_RIGHT
	4: LEFT
	5: CENTER
	6: RIGHT
	7: DOWN_LEFT
	8: DOWN
	9: DOWN_RIGHT
*/



public class Cell{

	public enum Location { 
	UP,
	UP_LEFT,
	UP_RIGHT,
	LEFT,
	CENTER,
	RIGHT,
	DOWN,
	DOWN_LEFT,
	DOWN_RIGHT
	}

	public enum State{
		LIVE,
		DEAD
	}

	private Vector<Integer> position;
	private Location location;		
	private State state;
	private int liveNeighbors = 0;


	public Cell( int posX, int posY, Location location  ){
		position = new Vector<Integer>( 2 );
		position.insertElementAt( Integer.valueOf( posX ), 0 ); 
		position.insertElementAt( Integer.valueOf( posY ), 1 );

		this.location = location; 
		this.state = State.DEAD;
	}

	public String toString(){
		return 	"\nPosicion: ( " + position.elementAt( 0 ) + " , " + position.elementAt( 1 ) + " )" +
				"\nRegion: " + locationString() + 
				"\nEstado: " + stateSring() +
				"\nV. Viv: " + liveNeighbors;
	}

	
	public void setState( State state ){ this.state = state; }
	public void setLiveNeighbors( int liveNeighbors ){ this.liveNeighbors = liveNeighbors; }

	public int getLiveNeighbors(){ return liveNeighbors; }
	public State getState(){ return state; } 
	public Location getLocation(){ return location; }
	public Integer getXPosition(){ return position.elementAt( 0 ); }
	public Integer getYPosition(){ return position.elementAt( 1 ); }
	
	private String locationString(){
		switch( this.location ){
			case UP_LEFT: return "1";
			case UP: return "2";
			case UP_RIGHT: return "3";
			case LEFT: return "4";
			case CENTER: return "5";
			case RIGHT: return "6";
			case DOWN_LEFT: return "7";
			case DOWN: return "8";
			case DOWN_RIGHT: return "9";
			default: return "";
		}
	} 

	private String stateSring(){
		switch( this.state ){
			case LIVE: return "Vivo";
			case DEAD: return "muerto";
			default: return "";
		}
	}

}
