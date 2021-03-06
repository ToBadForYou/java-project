package logic;

import io.gsonfire.annotations.PostDeserialize;

import java.awt.image.BufferedImage;

/**
 * This class represents each tile inside the MapArea, it can have collision, a static object and visual representation of the
 * actual tile
 */

public class MapTile extends GameObject
{
    private int staticObjectID;
    private transient BufferedImage objectImage = null;
    private int[] offset = null, size = null;
    private boolean collision;

    public MapTile(final int id, final int objectID, boolean collision) {
        super(id, "tiles");
        this.staticObjectID = objectID;
        this.collision = collision;
    }

    public void setObjectImage(final int id){
        if (staticObjectID != -1) {
            objectImage = OBJECT_DATA_HANDLER.getImage(id, "static");
            double[] imageSize = OBJECT_DATA_HANDLER.getImageSize(id, "static");
            size = new int[] { (int) (imageSize[0] * 1), (int) (imageSize[1] * 1) };
        }
    }

    public void setTile(int id, boolean collision){
        this.id = id;
        setImage(id, "tiles");
        this.collision = collision;
    }

    public void setObject(int id, boolean collision){
        staticObjectID = id;
        setObjectImage(staticObjectID);
        if (id == -1){
            objectImage = null;
        }
        this.collision = collision;
    }

    @PostDeserialize
    public void postDeserializeLogic(){
        setImage(id, "tiles");
        setObjectImage(staticObjectID);
    }

    public int[] getSize() {
        return size;
    }

    public boolean getCollision() {
        return collision;
    }

    public BufferedImage getObjectImage(){
        return objectImage;
    }

    public int getStaticObjectID() {
        return staticObjectID;
    }
}
