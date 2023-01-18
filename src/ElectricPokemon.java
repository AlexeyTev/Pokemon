public class ElectricPokemon extends Pokemon{
    private int currentEnergy;

    public ElectricPokemon(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Ability[] abilities) {
        super(names, maxHp, maxAp, maxLvl, abilities);
        this.currentEnergy=0;
    }

    public ElectricPokemon(Pokemon pokemon) {
        super(pokemon);
        this.currentEnergy = 0;
    }

    private void electricPokemonProperty(){//O(1)
        boolean passesHpThreshold = passesHpThreshold();
        if (this.currentEnergy<Constants.MAX_ENERGY && passesHpThreshold){
            this.currentEnergy+=Constants.ADD_ENERGY;
        }else if (!passesHpThreshold && currentEnergy>0){
            this.currentEnergy=Constants.REMOVE_ENERGY;
        }
    }
    private boolean passesHpThreshold(){//O(1)
        boolean passesHpThreshold = true;
        int percentage = this.getCurrentHp()*100/this.getMaxHp();
        if (percentage<Constants.HP_THRESHOLD)
            passesHpThreshold=false;
        return passesHpThreshold;
    }

    private double getCurrentEnergyPercent() {//O(1)
        double multiplier =this.currentEnergy+100;
        multiplier/=100;
        return multiplier;
    }
    public boolean useAttackAbility (Pokemon damaged){//O(1)
        boolean success = true;
        int chosenAttack=printAbilitiesAndReturnInput();
        boolean enoughAp=isEnoughApAndRemove(chosenAttack);

        int dmg;
        if (!enoughAp){
            System.out.println(this.getCurrentName() + " ,You dont have enough AP");
            success= false;
        }else {
            dmg = (int) ((this.getAbilities()[chosenAttack-1].getRandomDmg())*getCurrentEnergyPercent());
            if (this.isTripleAttackDamage()){
                dealTripleDmg(damaged,dmg);}
            else {
                System.out.println(damaged.getCurrentName() + ": -"+dmg+" HP");
                damaged.isEnoughHpToRemove(dmg);
            }
        }
        return success;
    }
    public boolean specialAbility (Pokemon damaged){//O(1)
        boolean success = false;
        if (this.isAvailableSpecialAbility()){
            this.setCurrentHp(this.getMaxHp());
            this.setCurrentAp(this.getMaxAp());
            this.setAvailableSpecialAbility(false);
            success = true;
            System.out.println("You have now max HP and AP via Ultimate ability");
        }else System.out.println("You already used your Ultimate ability");
        return success;
    }


    @Override
    public String toString() {//O(1)
        return super.toString() +", "+ this.currentEnergy + "(ENERGY)";
    }

    @Override
    public void turnPass() {//O(1)
        super.turnPass();
        electricPokemonProperty();
    }
}



