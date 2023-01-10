public class ElectricPokemon extends Pokemon{
    private int currentEnergy;

    public ElectricPokemon(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Ability[] abilities) {
        super(names, maxHp, maxAp, maxLvl, abilities);
        this.currentEnergy=0;
    }

    public void electricPokemonProperty(){
        boolean passesHpThreshold = passesHpThreshold();
        if (this.currentEnergy<Constants.MAX_ENERGY && passesHpThreshold){
            this.currentEnergy+=Constants.ADD_ENERGY;
        }else if (!passesHpThreshold && currentEnergy>0){
            currentEnergy=Constants.REMOVE_ENERGY;
        }
    }
    private boolean passesHpThreshold(){
        boolean passesHpThreshold = true;
        int percentage = this.getCurrentHp()*100/this.getMaxHp();
        if (percentage<Constants.HP_THRESHOLD)
            passesHpThreshold=false;
        return passesHpThreshold;
    }

    public float getCurrentEnergyPercent() {
        return (float) ((this.currentEnergy+100)/100);
    }
    public boolean useAttackAbility (Pokemon damaged){
        boolean success=true;
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
            System.out.println(this.getCurrentName() + " ,You dont have enough AP");
            success= false;
        }else {
            dmg = (int) ((this.getAbilities()[chosenAttack-1].getRandomDmg())*getCurrentEnergyPercent());
            if (this.isTripleAttackDamage()){
                dealTripleDmg(damaged,dmg);}
            else {
                System.out.println(damaged.getCurrentName() + ": -"+dmg+" HP");
                damaged.removeHp(dmg);
            }
        }
        return success;
    }
    public boolean specialAbility (Pokemon damaged){
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
    private void dealTripleDmg (Pokemon damaged, int dmg){
        System.out.println(damaged.getCurrentName() + ": -"+dmg*Constants.TRIPLE_DMG+" HP");
        damaged.removeHp(dmg*Constants.TRIPLE_DMG);
        this.setTripleAttackDamage(false);}

    @Override
    public String toString() {
        return super.toString() +", "+ this.currentEnergy + "(ENERGY)";
    }
}



