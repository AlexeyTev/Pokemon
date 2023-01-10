public class Main {
    public static void main(String[] args) {
       String [] charmenderNames = {"Charmender", "Charmeleon", "Charizard"};
        int [] charmenderHp = {60,80,130};
        int [] charmenderAp = {40,60,80};
        Ability [] charmenderAbilities = {
                new Ability("Scratch", 25, 30, 15),
        new Ability("Flame Tale", 30, 50, 40),
        new Ability("Fiery Blast", 50, 50, 50),
        };
        String [] bitzleNames = {"Bitzle" , "Zebstrika"};
        int [] bitzleHp = {90,100};
        int [] bitzleAp = {35,50};
        Ability [] bitzleAbilities = {
                new Ability("Flop", 20, 25, 20),
                new Ability("Zap Kick", 30, 35, 30),
        };
        FirePokemon charmender = new FirePokemon(charmenderNames,charmenderHp,charmenderAp,3,charmenderAbilities);
        ElectricPokemon bitzle = new ElectricPokemon(bitzleNames,bitzleHp,bitzleAp,2,bitzleAbilities);


        Trainer trainer1 = new Trainer(charmender,"Alex");
        Trainer trainer2 = new Trainer(bitzle,"Shon");
        Battle battle = new Battle(trainer1,trainer2);
        battle.startBattle();

    }
}