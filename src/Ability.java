import java.util.Random;

public class Ability {
    Random random = new Random();
    String name;
    int minDmg;
    int maxDmg;
    int apCost;

    public Ability( String name, int minDmg, int maxDmg, int apCost) {
        this.name = name;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.apCost = apCost;
    }

    public int getRandomDmg(){
        int dmg;
        if (this.minDmg!=this.maxDmg) {
            dmg = random.nextInt(this.minDmg, this.maxDmg + 1);
        }else dmg = minDmg;
        return dmg;
    }
    public int getApCost(){
        return this.apCost;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        String output = this.name + ": " + this.apCost+ "(AP)";
        if (minDmg!=maxDmg){
            output+= "," + minDmg + "-" + maxDmg + "(DMG)";
        }else output+= "," + minDmg+ "(DMG)";
        return output;
    }
}
