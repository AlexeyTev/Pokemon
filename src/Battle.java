import java.util.Random;
import java.util.Scanner;

public class Battle {
    Trainer trainer1;
    Trainer trainer2;
     Pokemon [] allPokemons;

    public Battle(Pokemon [] allPokemons) {
        this.allPokemons=allPokemons;
    }

    public void createTrainers(){
        Scanner scanner =new Scanner(System.in);
        Random random = new Random();
        Trainer trainer1;
        Trainer trainer2;
        int randIndex = random.nextInt(0,allPokemons.length);
        System.out.println("Insert 1st trainer name:");
         trainer1 = new Trainer(allPokemons[randIndex], scanner.nextLine());
        this.trainer1=trainer1;
       randIndex=random.nextInt(0,allPokemons.length);
        System.out.println("Insert 2nd trainer name:");
        if (allPokemons[randIndex].equals(trainer1.getPokemon())) {
        Pokemon dup = createDupPokemon(trainer1.getPokemon());
        trainer2 = new Trainer(dup, scanner.nextLine());
        }else {trainer2=new Trainer(allPokemons[randIndex], scanner.nextLine());}
        this.trainer2=trainer2;


    }
    private Pokemon createDupPokemon (Pokemon pokemon){
        Pokemon returnPok;
        if (pokemon instanceof FirePokemon){
            returnPok = new FirePokemon(pokemon);
        }else {returnPok = new ElectricPokemon(pokemon);}

        return returnPok;
    }
    public void startBattle (){
        boolean trainer1Turn = false;
        do {trainer1Turn = turn(trainer1Turn);

            if (trainer1Turn) {
                trainer1.turnOptions(trainer2);
            }else {
                trainer2.turnOptions(trainer1);
            }


        }while (!isDone());
        System.out.println("||***************||\n\n\n");
    }
  private boolean turn (boolean trainer1Turn){
      System.out.println("CURRENT STATUS :" +trainer1 + " --- "+trainer2);
      return !trainer1Turn;
  }
    private boolean isDone (){
        boolean isDone = false;
        if (trainer1.getPokemon().getCurrentHp()<=0){
            isDone = true;
            System.out.println(trainer2.getName() + " with " + trainer2.getPokemon().getCurrentName() + " is the winner.");
        }else if (trainer2.getPokemon().getCurrentHp()<=0){
            isDone=true;
            System.out.println(trainer1.getName() + " with " + trainer1.getPokemon().getCurrentName()+ " is the winner.");
        }

        return isDone;
    }
}
