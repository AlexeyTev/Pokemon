import java.util.Scanner;

public class Trainer {
   private Pokemon pokemon;
    private String name;


    public Trainer(Pokemon pokemon, String name) {
        this.pokemon = pokemon;
        this.name = name;
    }

    public void turnOptions (Trainer otherTrainer) {//O(n)
        Scanner scanner = new Scanner(System.in);
        this.pokemon.turnPass();
        otherTrainer.pokemon.turnPass();
        boolean success = false;

        do {
            System.out.println(this.getName() + ", Chose what you want to do next (1 per turn):\n" +
                    Constants.ATTACK_ABILITY_OPTION + ")Use attack ability\n" +
                    Constants.WAIT_OPTION + ")Wait\n" +
                    Constants.EVOLVE_OPTION + ")Evolve\n" +
                    Constants.SPECIAL_ABILITY_OPTION + ")Ultimate ability\n" +
                    Constants.KICK_OPTION+")Kick");
            int userChoice;
            do {
                userChoice = scanner.nextInt();
            } while (userChoice > Constants.KICK_OPTION || userChoice < Constants.ATTACK_ABILITY_OPTION);
            switch (userChoice){
                case Constants.ATTACK_ABILITY_OPTION -> success = this.pokemon.useAttackAbility(otherTrainer.getPokemon());
                case Constants.WAIT_OPTION ->{this.pokemon.waitOption();success = true;}
                case Constants.EVOLVE_OPTION ->success = this.pokemon.evolve();
                case Constants.SPECIAL_ABILITY_OPTION -> success = this.pokemon.specialAbility(otherTrainer.getPokemon());
                case Constants.KICK_OPTION -> success = this.pokemon.kick(otherTrainer.getPokemon());
            }
        }while (!success);

    }
    public Pokemon getPokemon() {//O(1)
        return pokemon;
    }


    public String getName() {//O(1)
        return name;
    }



    @Override
    public String toString() {//O(1)
        return this.name + " ("+this.pokemon+")";
    }
}
