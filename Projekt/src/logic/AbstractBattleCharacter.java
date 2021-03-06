package logic;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is an abstract class for the different BattleCharacter in the game.
 * This is used in the classes which can Battle.
 */

public abstract class AbstractBattleCharacter extends GameObject implements BattleCharacter
{
    protected final String name;
    protected int hp, ap, teamID, level, maxHP, maxAP;
    protected Point pos;
    protected Map<String, Integer> attributes;
    protected transient Color statusColor;
    private static transient final Random RANDOM = new Random();

    protected AbstractBattleCharacter(final String name, final int stamina, final int ap, final int atk, final int sp, final int dodgeChance, final int critChance,
			      final int physicalDef, final int magicalDef, final Point pos, int id, String type, int teamID, int level, Color statusColor)
    {
        super(id, type);
        this.name = name;
        this.maxHP = stamina * 10;
        this.hp = maxHP;
        this.maxAP = ap;
        this.ap = ap;
        attributes = new HashMap<>();
        attributes.put("Stamina", stamina);
        attributes.put("Attack Power", atk);
        attributes.put("Spell Power", sp);
        attributes.put("Dodge Chance", dodgeChance);
        attributes.put("Crit Chance", critChance);
        attributes.put("Physical Defence", physicalDef);
        attributes.put("Magical Defence", magicalDef);
        this.pos = pos;
        this.level = level;

        this.teamID = teamID;
        this.statusColor = statusColor;
    }

    protected AbstractBattleCharacter(int level, Point pos, int id, String type, int teamID, Object[] stats){
	    this((String)stats[0], (int)((double)stats[1] * level), (int) ((double)stats[2] * level), (int) ((double)stats[3] * level),
	     (int) ((double)stats[4] * level), (int) ((double)stats[5]*1), (int) ((double)stats[6]*1),
	     (int) ((double)stats[7] * level), (int)((double)stats[8] * level / 2), pos, id, type, teamID, level, Color.YELLOW);
    }

    public int getHP() {
	return hp;
    }

    public int getMaxHP() { return maxHP; }

    public int getAP() { return ap; }

    public int getMaxAP(){ return maxAP; }

    public int getAttribute(String name){
        return attributes.get(name);
   }

    public Map<String, Integer> getAttributes(){
        return attributes;
   }

    public Point getPos() {return pos; }

    public int getLevel() { return level; }

    public void restoreHP(int hp){
        this.hp += hp;
    	if(this.hp > maxHP){
    	    this.hp = maxHP;
    	}
    }

    public void restoreAP(){
        ap = maxAP;
    }

    public void reduceAP(final int i){
        ap -= i;
    }

    public int getTeamID() { return teamID; }

    public Color getStatusColor() {
	    return statusColor;
    }

    public String getName() {
	    return name;
    }

    public void attack(BattleCharacter defender){
	if(defender != null) {
	    boolean dodge = RANDOM.nextInt(100) < attributes.get("Dodge Chance");
	    if (dodge){
		System.out.println("Dodged");
	    }
	    else{
		boolean crit = RANDOM.nextInt(100) < attributes.get("Crit Chance");
		double damage = (double)attributes.get("Attack Power") / defender.getAttribute("Physical Defence") * 10;
		if(crit){
		    damage *= 2;
		}
		defender.takeDamage((int)Math.ceil(damage));
	    }
	}
	else{
	    System.out.println("Opponent is null");

	}
    }

    public void move(Point p){
	pos.setLocation(p);
    }

    public Point getAdjacentPoint(DirectionMapper.Direction direction){
	Point newPos = new Point(pos);
	Point deltaCoord = DirectionMapper.getDeltaCoord(direction);
	newPos.translate((int) deltaCoord.getX(), (int) deltaCoord.getY());
	return newPos;
    }

    public void takeDamage(int damage){
        hp -= damage;
    }

    public void setStatusColor(Color statusColor){
	this.statusColor = statusColor;
    }

}
