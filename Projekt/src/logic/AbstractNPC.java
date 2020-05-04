package logic;

import io.gsonfire.annotations.PostDeserialize;

import java.awt.*;

/**
 * This is an abstract class for the different NPC's in the game
 */

public abstract class AbstractNPC extends GameObject  implements NPC
{
    protected Point pos;
    protected int mapID;
    protected transient String name;

    protected AbstractNPC(final int id, final String type, final int mapID, final Point pos) {
        super(id, type);
        this.pos = pos;
        this.mapID = mapID;
        this.name = (String)GameObject.objectData.getData(id, "npc")[0];
    }

    public Point getPos() {
        return pos;
    }

    public int getMapID() {
        return mapID;
    }

    public String getName() {
        return name;
    }

    @PostDeserialize
    public void postDeserializeLogic(){
        name = (String)GameObject.objectData.getData(id, "npc")[0];
        setImage(id, "npc");
    }
}
