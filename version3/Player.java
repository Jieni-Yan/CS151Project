
public class Player {
	int id;
	int undo;
	int undocheck;

	public Player(int id) {
		this.undocheck = 0;// if already undo assign 1 not undo assign 0
		this.undo = 0;// undo counter max 3
		this.id = id;// player id 1 or 0
	}
	
}
