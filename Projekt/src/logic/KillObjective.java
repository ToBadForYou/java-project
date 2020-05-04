package logic;

import java.util.List;

/**
 * This class represtents represents an objective the needs a certain amount of enemies killed in order to complete.
 * This is created in the Quest class.
 */

public class KillObjective
{
    private int goal;
    private int value;
    private String description;
    private int enemyID;

    public KillObjective(final int goal, final String description, final int enemyID) {
	this.goal = goal;
	this.description = description;
	this.enemyID = enemyID;
    }

    public boolean checkCompletion(int value){ return value >= goal; }

    public int updateCompletion(List<Enemy> enemiesKilled, int value){
	for (Enemy enemy : enemiesKilled) {
	    if(enemy.getId() == enemyID){
	        if (value < goal) {
		    value++;
		}
	        if (value > goal){
	            value = goal;
		}
	    }
	}
	return  value;
    }

    public int getGoal() {
	return goal;
    }

    public int getValue() {
	return value;
    }

    public String getDescription() {
	return description;
    }

    public int getEnemyID() {
	return enemyID;
    }
}
