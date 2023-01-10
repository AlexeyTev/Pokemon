import java.util.Random;
import java.util.Scanner;

public abstract class Pokemon {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
   private String[] names;
    private String currentName;
    private int[] maxHp;
    private int[] maxAp;
    private int currentHp;
    private int currentAp;
    private int maxLvl;
    private int currentLvl;
    private Ability[] abilities;
    private boolean tripleAttackDamage;
    private boolean availableSpecialAbility;

    public String[] getNames() {
        return names;
    }

    public String getCurrentName() {
        return currentName;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public int getCurrentLvl() {
        return currentLvl;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public boolean isTripleAttackDamage() {
        return tripleAttackDamage;
    }

    public boolean isAvailableSpecialAbility() {
        return availableSpecialAbility;
    }

    public Pokemon(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Ability[] abilities) {
        this.names = names;
        this.currentName=names[currentLvl];
        this.maxHp = maxHp;
        this.maxAp = maxAp;
        this.currentHp = maxHp[currentLvl];
        this.currentAp = (int) (maxAp[currentLvl]*Constants.PRESET_AP);
        this.maxLvl = maxLvl;
        this.currentLvl = 1;
        this.abilities = abilities;
        tripleAttackDamage=false;
        availableSpecialAbility=true;
    }
    public abstract boolean useAttackAbility (Pokemon damaged);
    public abstract boolean specialAbility (Pokemon damaged);
    public int getMaxHp() {
        return maxHp[currentLvl -1];
    }

    public int getMaxAp() {
        return maxAp[currentLvl -1];
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getCurrentAp() {
        return currentAp;
    }

    public boolean removeHp(int hpToRemove) {
        boolean hasHp = true;
        if (this.currentHp - hpToRemove > 0) {
            this.currentHp -= hpToRemove;
        } else {
            this.currentHp=0;
            hasHp = false;
        }
        return hasHp;
    }

    public boolean removeAp(int apToRemove) {
        boolean hasAp = true;
        if (this.currentAp - apToRemove > 0) {
            this.currentAp -= apToRemove;
        } else hasAp = false;
        return hasAp;
    }

    public boolean kick(Pokemon damagedPokemon) {
        damagedPokemon.currentHp -= Constants.KICK_DMG;
        System.out.println(damagedPokemon.currentName + ": -"+Constants.KICK_DMG + "(HP)");
        return true;
    }

    public void turnAddHpAndAp() {
        int hpToAdd = random.nextInt(Constants.MIN_TURN_HP_ADD, Constants.MAX_TURN_HP_ADD + 1);
        int apToAdd = random.nextInt(Constants.MIN_TURN_AP_ADD, Constants.MAX_TURN_AP_ADD + 1);
        if (this.currentHp + hpToAdd > this.maxHp[this.getCurrentLvl() - 1]) {
            this.currentHp = this.maxHp[this.getCurrentLvl() - 1];
        } else {
            this.currentHp += hpToAdd;
        }
        if (this.currentAp + apToAdd > this.maxAp[this.getCurrentLvl() - 1]) {
            this.currentAp = this.maxAp[this.getCurrentLvl() - 1];
        } else {
            this.currentAp += apToAdd;
        }
    }
    public void waitOption() {
        this.tripleAttackDamage = false;
        int randomOption = random.nextInt(Constants.WAIT_OPTION_1,Constants.WAIT_OPTION_3+1);
        switch (randomOption){
            case Constants.WAIT_OPTION_1 -> randomOptionAddHp();
            case Constants.WAIT_OPTION_2  -> randomOptionAddAp();
            case Constants.WAIT_OPTION_3  -> {this.tripleAttackDamage=true;
                System.out.println(this.currentName + "you got Triple Attack Damage for next attack ");}
        }

    }
    private void randomOptionAddHp(){
        int hpToAdd = random.nextInt(Constants.MIN_WAIT_HP_ADD,Constants.MAX_WAIT_HP_ADD);
        if (this.currentHp+hpToAdd>this.maxHp[getCurrentLvl() -1]){
            this.currentHp=this.maxHp[getCurrentLvl() -1];
        }else {
            this.currentHp+=hpToAdd;
        }
        System.out.println(this.currentName + " : +"+ hpToAdd + " (HP) for waiting");
    }
    private void randomOptionAddAp(){
        int apToAdd = random.nextInt(Constants.MIN_WAIT_AP_ADD,Constants.MAX_WAIT_AP_ADD);
        if (this.currentAp+apToAdd>this.maxAp[getCurrentLvl() -1]){
            this.currentAp=this.maxAp[getCurrentLvl() -1];
        }else {
            this.currentAp+=apToAdd;
        }
        System.out.println(this.currentName + ": +"+ apToAdd + " (AP) for waiting");
    }
    public boolean evolve (){
        boolean isEvolvedSuccessfully = false;
        if (this.currentLvl <this.maxLvl){
            switch (this.currentLvl){
                case 1 -> {if (this.currentHp-Constants.EVOLVE_2_HP_COST>0){
                    if (this.currentAp-Constants.EVOLVE_2_AP_COST>0){
                        this.currentLvl++;
                        this.currentHp-=Constants.EVOLVE_2_HP_COST;
                        this.currentAp-=Constants.EVOLVE_2_AP_COST;
                        isEvolvedSuccessfully = true;
                    }else System.out.println("You dont have enough attack points ("+Constants.EVOLVE_2_AP_COST+")");
                }else System.out.println("You dont have enough hp ("+Constants.EVOLVE_2_HP_COST+")");
                }
                case 2 -> {if (this.currentHp-Constants.EVOLVE_3_HP_COST>0){
                    if (this.currentAp-Constants.EVOLVE_3_AP_COST>0){
                        this.currentLvl++;
                        this.currentHp-=Constants.EVOLVE_3_HP_COST;
                        this.currentAp-=Constants.EVOLVE_3_AP_COST;
                        isEvolvedSuccessfully = true;
                    }else System.out.println("You dont have enough attack points ("+Constants.EVOLVE_3_AP_COST+")");
                }else System.out.println("You dont have enough hp ("+Constants.EVOLVE_3_HP_COST+")");
                }
            }
        }else System.out.println("You are at your max level");
        if (isEvolvedSuccessfully){
        setEvolvedName();
            System.out.println("You are now: " + this.currentName);}
        return isEvolvedSuccessfully;
    }
    private void setEvolvedName(){
        this.currentName=this.names[currentLvl -1];
    }



    @Override
    public String toString() {
        return currentName + ": "+ currentHp + "/"+this.maxHp[currentLvl -1] + "(HP), "+currentAp +  "/"+ maxAp[currentLvl -1]+"(AP)";
    }

    public void setTripleAttackDamage(boolean tripleAttackDamage) {
        this.tripleAttackDamage = tripleAttackDamage;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public void setCurrentAp(int currentAp) {
        this.currentAp = currentAp;
    }

    public void setAvailableSpecialAbility(boolean availableSpecialAbility) {

        this.availableSpecialAbility = availableSpecialAbility;
    }

}