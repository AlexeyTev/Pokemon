
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
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
     final   int [] bitzleHp = {90,100};
      final   int [] bitzleAp = {35,50};
      final  Ability [] bitzleAbilities = {
                new Ability("Flop", 25, 30, 20),
                new Ability("Zap Kick", 30, 35, 30),
        };
      String[]salanditNames ={"Salandit","Salazzle"};
      int [] salanditHp ={100,160};
      int [] salanditAp = {60,80};
      Ability[]salanditAbilities = {
              new Ability("Live Coal",0,25,10),
              new Ability("Fire Claws",0,50,25),
      };
      String[]moltersNames = {"Molters"};
      int [] moltersHp ={120};
      int [] moltersAp = {60};
      Ability[]moltersAbilities = {new Ability("Assisting Heater",10,60,30),
      new Ability("Fire Wing",30,30,30)};
      String[]pikachuNames = {"Pichu","Pikachu","Raichu"};
      int [] pikachuHp ={40,50,100};
      int [] pikachuAp ={30,40,80};
      Ability[]pikachuAbilities={new Ability("Quick Attack",10,10,5),
      new Ability("Electro Ball",30,40,10),
      new Ability("Electric Surfer",20,120,60)};
      String []electabuzzNames = {"Electabuzz","Electivire"};
      int [] electabuzzHp = {30,35};
      int []electabuzzAp = {100,120};
      Ability [] electabuzzAbilities = {new Ability("Thunder",40,50,60),
      new Ability("Thunder Punch",50,120,80)};


        FirePokemon charmender = new FirePokemon(charmenderNames,charmenderHp,charmenderAp,3,charmenderAbilities);
        FirePokemon salandit = new FirePokemon(salanditNames,salanditHp,salanditAp,2,salanditAbilities);
        FirePokemon molters = new FirePokemon(moltersNames,moltersHp,moltersAp,1,moltersAbilities);
        ElectricPokemon pikachu = new ElectricPokemon(pikachuNames,pikachuHp,pikachuAp,3,pikachuAbilities);
        ElectricPokemon electabuzz = new ElectricPokemon(electabuzzNames,electabuzzHp,electabuzzAp,2,electabuzzAbilities);
        ElectricPokemon bitzle = new ElectricPokemon(bitzleNames,bitzleHp,bitzleAp,2,bitzleAbilities);
        Pokemon [] allPokemons = new Pokemon[6];


        allPokemons[0]=charmender;
        allPokemons[1]=salandit;
        allPokemons[2]=molters;
        allPokemons[3]=pikachu;
        allPokemons[4]=electabuzz;
        allPokemons[5]=bitzle;
        Battle battle = new Battle(allPokemons);
        mainMenu(battle);

    }
    public static void mainMenu (Battle battle){
        int userChoice;
        do {
            System.out.println("MAIN MENU:\n"+Constants.START_BATTLE+")Start new battle.\n" +
                    Constants.GAME_INFO+")Game Info\n" +
                    Constants.QUIT+")Exit");
             userChoice = scanner.nextInt();
             switch (userChoice){
                 case Constants.START_BATTLE -> startNewBattle(battle);
                 case Constants.GAME_INFO -> printGameInfo();
             }
        }while (userChoice !=Constants.QUIT);
    }
    public static void startNewBattle (Battle battle){
        battle.createTrainers();
        battle.startBattle();
    }
    public static void printGameInfo (){
        System.out.println("There are 2 kinds of pokemons: \n\n" +
                "Fire Pokemon - has 25% that after using an ability he will get damaged.\n" +
                "In a battle you have one use of an ultimate ability that randomly uses 2 abilities on the opponent and cuts your HP by " +Constants.SPECIAL_ABILITY_HP_COST_FIRE + " & removes your AP\n\n"+
                "Electric Pokemon - with every turn that passes and while he has more than "+Constants.HP_THRESHOLD +"% HP his ENERGY increases by "+Constants.ADD_ENERGY+" and the ability's DMG increases accordingly.\n" +
                "In a battle you have one use of an ultimate ability that fills up the pokemons HP & AP to max \n\n" +
                "-Every turn that passes you will have your HP & AP increased randomly.\n" +
                "-You can use 'KICK' with no AP cost and deal  "+Constants.KICK_DMG+" DMG to the opponent \n" +
                "-You can evolve to a stronger form with the cost of "+Constants.EVOLVE_2_HP_COST+"(HP) & "+Constants.EVOLVE_2_AP_COST+"(AP) for the first evolve\n" +
                " and "+Constants.EVOLVE_3_HP_COST+"(HP) & "+Constants.EVOLVE_3_AP_COST+"(AP) for the second evolve.(Only if your pokemon is being able to evolve)\n" +
                "-You can wait a turn a get a random bonus of (HP/AP/Triple DMG next attack)\n\n" +
                "~HAVE FUN~\n\n\n");
    }
}