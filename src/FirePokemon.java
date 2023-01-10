import java.util.Random;

public class FirePokemon extends Pokemon{

    public FirePokemon(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Ability[] abilities) {
        super(names, maxHp, maxAp, maxLvl, abilities);
    }

    public void firePokemonProperty(){
        Random random = new Random();
        int chance25Percent = random.nextInt(1,5);
        if (chance25Percent == 1){
            int hpToRemove = random.nextInt(3,11);
            this.removeHp(hpToRemove);
            System.out.println("You have been dealt: "+hpToRemove+" damage from the fire property");
        }
    }
    public boolean useAttackAbility (Pokemon damaged){
        boolean success = true;
        int chosenAttack;
        int dmg;
        for (int i = 0 ; i <this.getCurrentLvl();i++){
            System.out.println((i+1) +")"+ this.getAbilities()[i]);
        }
        do {
            chosenAttack = scanner.nextInt();
        }while (chosenAttack>this.getCurrentLvl());
        boolean enoughAp= this.removeAp(this.getAbilities()[chosenAttack-1].getApCost());
        if (!enoughAp){
            System.out.println("You dont have enough AP");
            success = false;
        }else {
            dmg = this.getAbilities()[chosenAttack-1].getRandomDmg();
            if (this.isTripleAttackDamage()){
                System.out.println(damaged.getCurrentName() + ": -"+dmg*Constants.TRIPLE_DMG+" HP");
            damaged.removeHp(dmg*Constants.TRIPLE_DMG);
            this.setTripleAttackDamage(false);}
            else {
                System.out.println(damaged.getCurrentName() + ": -"+dmg+" HP");
                damaged.removeHp(dmg);
            }
            firePokemonProperty();
        }
        return success;
    }
    public boolean specialAbility (Pokemon damaged) {
        boolean success = false;
        if (this.isAvailableSpecialAbility()) {
            int ability1 = random.nextInt(0, this.getCurrentLvl() );
            int ability2 = random.nextInt(0, this.getCurrentLvl() );
            this.setCurrentAp(Constants.SPECIAL_ABILITY_AP_COST_FIRE);
            this.setCurrentHp(this.getCurrentHp()/Constants.SPECIAL_ABILITY_HP_COST_FIRE);
            int totalDmg = this.getAbilities()[ability1].getRandomDmg() + this.getAbilities()[ability2].getRandomDmg();
            damaged.removeHp(totalDmg);
            this.setAvailableSpecialAbility(false);
            success = true;
            System.out.println("You dealt " + totalDmg + "to the opponent via Ultimate");
        }else System.out.println("You already used your Ultimate ability ");
        return success;
    }
    private void dealTripleDmg (Pokemon damaged, int dmg){
        System.out.println(damaged.getCurrentName() + ": -"+dmg*Constants.TRIPLE_DMG+" HP");
        damaged.removeHp(dmg*Constants.TRIPLE_DMG);
        this.setTripleAttackDamage(false);}
}
