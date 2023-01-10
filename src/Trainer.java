import java.util.Scanner;

public class Trainer {
    Pokemon pokemon;
    String name;

    public Trainer(Pokemon pokemon, String name) {
        this.pokemon = pokemon;
        this.name = name;
    }

    public void turnOptions (Trainer otherTrainer) {
        Scanner scanner = new Scanner(System.in);
        if (this.pokemon instanceof ElectricPokemon)
        ((ElectricPokemon) this.pokemon).electricPokemonProperty();
        if (otherTrainer.pokemon instanceof ElectricPokemon)
        ((ElectricPokemon) otherTrainer.pokemon).electricPokemonProperty();
        this.pokemon.turnAddHpAndAp();
        otherTrainer.pokemon.turnAddHpAndAp();
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
    public Pokemon getPokemon() {
        return pokemon;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " ("+this.pokemon+")";
    }
}
