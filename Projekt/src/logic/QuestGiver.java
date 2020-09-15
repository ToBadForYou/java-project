package logic;

import java.awt.Point;
import java.util.List;

/**
 * This class represents a certain NPC that contains different Quests for the player to obtain and complete for a reward.
 */
public class QuestGiver extends AbstractInteractableCharacter
{
    private List <Integer> quests;

      public QuestGiver(final int id, final int mapID, final Point pos, final List<Integer> questIDs) {
	  super(id, "npc", mapID, pos);
	  this.quests = questIDs;
      }

    @Override
    public void interact(Player player){
        if(!quests.isEmpty()) {
	    int i = quests.get(0);
	    Quest nextQuest = World.getQuest(i);
	    if (nextQuest.canPickUP(player)) {
		player.addQuest(i);
		System.out.println("Picked up " + nextQuest.getName());
	    } else if (nextQuest.isCompleted(player.getQuestProgress(i))) {
		System.out.println("Completed quest " + nextQuest.getName());
		nextQuest.gainRewards(player);
		this.quests.remove(0);
		player.completeQuest(i);
	    }
	}
    }

    public int getQuestStatus(Player player){
      	if (quests.isEmpty()){ return -1; }

      	else if(World.getQuest(quests.get(0)).canPickUP(player)){return 1; }

      	else if (player.getQuestProgress(quests.get(0)) == null){ return 0; }

      	else if(!World.getQuest(quests.get(0)).isCompleted(player.getQuestProgress(quests.get(0)))) {
			return 2;
		}
      	else { return 3; }
	}

}
