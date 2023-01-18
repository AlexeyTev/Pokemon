import java.util.Random;

public class Ability {
    Random random = new Random();
   private String name;
   private int minDmg;
   private int maxDmg;
  private  int apCost;

    public Ability( String name, int minDmg, int maxDmg, int apCost) {
        this.name = name;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.apCost = apCost;
    }

    public int getRandomDmg(){//O(1)
        int dmg;
        if (this.minDmg!=this.maxDmg) {
            dmg = random.nextInt(this.minDmg, this.maxDmg + 1);
        }else dmg = minDmg;
        return dmg;
    }
    public int getApCost(){//O(1)
        return this.apCost;
    }


    @Override
    public String toString() {//O(1)
        String output = this.name + ": " + this.apCost+ "(AP)";
        if (minDmg!=maxDmg){
            output+= "," + minDmg + "-" + maxDmg + "(DMG)";
        }else output+= "," + minDmg+ "(DMG)";
        return output;
    }
}
