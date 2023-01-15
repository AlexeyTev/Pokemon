import java.util.Random;

public class FirePokemon extends Pokemon{

    public FirePokemon(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Ability[] abilities) {
        super(names, maxHp, maxAp, maxLvl, abilities);
    }

    public FirePokemon(Pokemon pokemon) {
        super(pokemon);
    }

    private void firePokemonProperty(){
        Random random = new Random();
        int chance25Percent = random.nextInt(1,5);
        if (chance25Percent == 1){
            int hpToRemove = random.nextInt(3,11);
            this.isEnoughHpToRemove(hpToRemove);
            System.out.println("You have been dealt: "+hpToRemove+" damage from the fire property");
        }
    }
    public boolean useAttackAbility (Pokemon damaged){
        boolean success = true;
        boolean enoughAp;
        int chosenAttack=this.printAbilitiesAndReturnInput();
            enoughAp = isEnoughApAndRemove(chosenAttack);
        int dmg;
        if (!enoughAp){
            System.out.println("You dont have enough AP");
            success = false;
        }else {
            dmg = this.getAbilities()[chosenAttack-1].getRandomDmg();
            if (this.isTripleAttackDamage()){
                System.out.println(damaged.getCurrentName() + ": -"+dmg*Constants.TRIPLE_DMG+" HP");
                dealTripleDmg(damaged,dmg);
          }
            else {
                System.out.println(damaged.getCurrentName() + ": -"+dmg+" HP");
                damaged.isEnoughHpToRemove(dmg);
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
            damaged.isEnoughHpToRemove(totalDmg);
            this.setAvailableSpecialAbility(false);
            success = true;
            System.out.println("You dealt " + totalDmg + " (DMG) to the opponent via Ultimate");
        }else System.out.println("You already used your Ultimate ability ");
        return success;
    }



}
