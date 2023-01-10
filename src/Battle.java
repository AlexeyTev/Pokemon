public class Battle {
    Trainer trainer1;
    Trainer trainer2;

    public Battle(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
    }

    public void startBattle (){
        boolean trainer1Turn = true;
        do {
            System.out.println("CURRENT STATUS :" +trainer1 + " --- "+trainer2);
            if (trainer1Turn) {
                trainer1.turnOptions(trainer2);
            }else {
                trainer2.turnOptions(trainer1);
            }
           trainer1Turn = turn(trainer1Turn);
        }while (!isDone());
    }
  private boolean turn (boolean trainer1Turn){
        return !trainer1Turn;
  }
    private boolean isDone (){
        boolean isDone = false;
        if (trainer1.getPokemon().getCurrentHp()<=0){
            isDone = true;
            System.out.println(trainer2.name + " with " + trainer2.pokemon.getCurrentName() + " is the winner.");
        }else if (trainer2.getPokemon().getCurrentHp()<=0){
            isDone=true;
            System.out.println(trainer1.name + " with " + trainer1.pokemon.getCurrentName()+ " is the winner.");
        }
        return isDone;
    }
}
