import java.util.Random;
import java.util.Scanner;

public class gameCode {
    public static void main(String[] args) {

        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        int gameCount = 0;
        String[] enemies = { "Gatekeeper Galio", "One Punch Man", "Skeletons", "Baby Zombie", "Raging Warrior",
                "Faceless Assasin", "Ghoul", "Wisp", "Slasher", "Warden", "Phantom", "Meep", "Dragon", "Baby Dragon",
                "Oversized Spider", "Reaper", "Grim Reaper", "Sleepy Murderer", "Shock Wizzard", "Earth Wizzard",
                "Fire Wizard", "Windcaster Wizard", "Craving Giant Rat", "Weeping Angel", "Ninja Flamingo",
                "Yourself but better", "Wolves", "Grump", "Velociraptors", "Jon Snow", "Harambe", "Ceasar", "Wanderer",
                "Giant Penguin", "Chucky", "Silver Rat", "Gold Rat", "Mutant", "Fish-man", "Con-man",
                "Ludicurious Clown", "Man-eater Plant", "Giant Man-eater Plant", "Giant Worm", "Warper", "Sweapster",
                "Evil Speedster", "Sharkface", "Freddy", "Evil Poro", "Dancing Demons", "Servant of Galio",
                "Decendant of Galio", "Karate Master Slender", "Armed Jellyfish", "Armed Squid", "Hungry Cyclops",
                "Retarted Minatour", "Zombie", "Vampire", "Servant of Galio" };
        // [0] has to be Gatekeeper Galio, it is set to be the boss
        // [1] is the second important character.

        // Player variables
        int health = 100;
        int maxAttackDamage = 43 + (gameCount / 3);
        int numHealthPotions = 3;

        boolean running = true;

        // System.out.println("Want to see the instructions? (y/n)");
        // instructions();

        System.out.println("Welcome to Galio's Den!");

        GAME: while (running) {
            int maxEnemyHealth = 82 + (2 * gameCount);
            int maxEnemyAttackDamage = 26 + gameCount;
            int onePunchAttack = 5 * (gameCount + 1) * (gameCount + 1);
            int onePunchHealth = 1;
            int maxGalioAttackDamage = 23 + (2 * gameCount);
            int galioHealth = 185 + (5 * gameCount);
            // also some interactive variables
            int healthPotionDropChance = 47 - (2 * (gameCount / 3)); // Percentage - every 3 score -2 chance
            int healthPotionHealAmount = 40 + (19 * (gameCount / 3)); // so that, pot saving is profitable
            gameCount = gameCount + 1;
            String enemy = enemies[rand.nextInt(enemies.length)];

            System.out.println("---------------------------------------------------");

            galioHealth = isServant(enemy, galioHealth);

            if (enemy == enemies[0] && galioHealth > 0) {
                int enemyHealth = galioHealth;
                while (enemyHealth > 0 && health > 0) {
                    System.out.println("\tYour HP: " + health);
                    System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                    System.out.println(enemy + ": You cannot run away from me, I am going to smashh you!!!");
                    System.out.println("\n\tWhat can I do againt the mighty Galio?");

                    // Choices
                    System.out.println("\t1. Attack");
                    System.out.println("\t2. Drink health potion");
                    System.out.println("\t3. Beg forgiveness");
                    String input = in.nextLine();

                    // 1. choice

                    if (input.equals("1")) {
                        int damageDealt = rand.nextInt(maxAttackDamage);
                        int damageTaken = rand.nextInt(maxGalioAttackDamage);

                        enemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You recieved " + damageTaken + " in retaliation.");

                        if (health < 1) {
                            System.out.println(
                                    "\t> He broke you too, like the other knights who tried to overcome his glory. You are too weak to go on.");
                            System.out.println("\t> No Score for the weak kind such as you...");
                            credits();

                            System.exit(0);
                        } else if (health >= 1 && enemyHealth < 1) {

                            System.out.println(" # " + enemy + " was defeated! # ");
                            System.out.println(" # You have " + health + " HP left. #");
                            System.out.println("---------------------------------------------------");
                            System.out.println("What would you like to do now?");
                            System.out.println("1. Continue fighting");
                            System.out.println("2. Exit dungeon");
                            input = in.nextLine();

                            while (!input.equals("1") && !input.equals("2")) {
                                System.out.println("Invalid command!");
                                input = in.nextLine();
                            }
                            // options function

                            if (input.equals("1")) {
                                System.out.println(
                                        "You continue your adventure even after defeating Gatekeeper Galio, impresive!");
                                // jump to next monster needed so
                                continue GAME;
                            } else if (input.equals("2")) {
                                System.out.println(
                                        "You encountered " + gameCount + " of monsters. So your score is " + gameCount);
                                credits();
                                System.exit(0);
                            }

                        }

                    }
                    // 2. choice
                    else if (input.equals("2")) {
                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for "
                                    + healthPotionHealAmount + ". " + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left.\n");
                        }

                        else {
                            System.out.println(
                                    "\t> You are out of health potions at the moment defeat Galio first to have the chance obtaining one again!");
                        }

                    }
                    // 3. choice
                    else if (input.equals("3")) {
                        boolean mercy = rand.nextBoolean();
                        if (mercy) {
                            System.out.println("\t> Galio has mercy after all, he didn't smash you, that's a relief!");
                            System.out.println(
                                    "\t> You exit the dungeon with greatful eyes and your body-count in this run is "
                                            + gameCount + " and your score is " + gameCount + " !");

                            credits();

                            System.exit(0);

                        } else {
                            System.out.println(
                                    "\t He smashed you in pieces in cold blood, Galio has no mercy for insects such as you!");

                            System.out.println("\t You are DONE. So no score for U!");

                            credits();

                            System.exit(0);

                        }

                    }

                    else {
                        System.out.println("\tInvalid command!");
                    }
                }
            }

            if (enemy == enemies[1] && onePunchHealth > 0) {
                int enemyHealth = onePunchHealth;
                while (enemyHealth > 0 && health > 0) {
                    System.out.println("\tYour HP: " + health);
                    System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                    System.out.println(
                            enemy + " I will become stronger. Have you seen Genos btw.. Today is discount day.");
                    System.out.println("\n\tGo away if you are weak in the head. WHat shall you do now?");

                    // Choices
                    System.out.println("\t1. Attack fearlessly");
                    System.out.println("\t2. Feeling Weak...(Drinks Pot)");
                    System.out.println("\t3. Coward move but run...");
                    String input = in.nextLine();

                    // 1. choice

                    if (input.equals("1")) {
                        int damageDealt = rand.nextInt(maxAttackDamage);
                        int damageTaken = onePunchAttack;

                        enemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You recieved " + damageTaken + " in retaliation.");

                        if (health < 1) {
                            System.out.println("\t> Got one punched!");
                            System.out.println("\t> Not a weak mental but a weak body I suppose.");
                            credits();

                            System.exit(0);
                        } else if (health >= 1 && enemyHealth < 1) {

                            System.out.println(" # " + enemy + " was defeated! # ");
                            System.out.println(" # You have " + health + " HP left. #");
                            System.out.println("---------------------------------------------------");
                            System.out.println("What would you like to do now?");
                            System.out.println("1. Continue fighting");
                            System.out.println("2. Exit dungeon");
                            input = in.nextLine();

                            while (!input.equals("1") && !input.equals("2")) {
                                System.out.println("Invalid command!");
                                input = in.nextLine();
                            }
                            // options function

                            if (input.equals("1")) {
                                System.out.println(
                                        "So you survived One punch man's punch and decided to continue. You got nerves..!");
                                // jump to next monster needed so
                                continue GAME;
                            } else if (input.equals("2")) {
                                System.out.println("That was enough good to know your limits.");
                                System.out.println(
                                        "You encountered " + gameCount + " of monsters. So your score is " + gameCount);
                                credits();
                                System.exit(0);
                            }

                        }

                    }
                    // 2. choice
                    else if (input.equals("2")) {
                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for "
                                    + healthPotionHealAmount + ". " + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions
                                    + " health potions left. (I think this was a great idea but are you weak in head?)\n");
                        }

                        else {
                            System.out.println(
                                    "\t> You are out of health potions at the moment defeat One Punch Man first to have the chance obtaining one again! (Not happenin' watch and see...)");
                        }

                    }
                    // 3. choice
                    else if (input.equals("3")) {
                        System.out.println(
                                "\tYou ran away from the " + enemy + "! Which was the right thing to do trust me.");
                        gameCount = gameCount - 1;
                        continue GAME;
                    }

                    else {
                        System.out.println("\tInvalid command!");
                    }
                }
            }

            // g

            // 2.d important char.

            int enemyHealth = rand.nextInt(maxEnemyHealth) + 1;

            System.out.println("\t# " + enemy + " has appeared! #\n");

            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat shall I do?");

                // Choices
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();

                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(maxAttackDamage);
                    int damageTaken = rand.nextInt(maxEnemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t> You recieved " + damageTaken + " in retaliation.");

                    if (health < 1) {
                        System.out.println(
                                "\t> You have been destroyed, you took more damage than you could endure. You don't exist anymore.\n\t # No Score for whom does not exist. #");
                        break;
                    }

                } else if (input.equals("2")) {
                    if (numHealthPotions > 0) {
                        health += healthPotionHealAmount;
                        numHealthPotions--;
                        System.out.println("\t> You drink a health potion, healing yourself for "
                                + healthPotionHealAmount + ". " + "\n\t> You now have " + health + " HP."
                                + "\n\t> You have " + numHealthPotions + " health potions left.\n");
                    }

                    else {
                        System.out.println(
                                "\t> You are out of health potions at the moment defeat enemies to have the chance obtaining one again!");
                    }

                } else if (input.equals("3")) {
                    System.out.println("\tYou ran away from the " + enemy + "!");
                    gameCount = gameCount - 1;
                    continue GAME;
                } else {
                    System.out.println("\tInvalid command!");

                }

            }

            // When you die
            if (health < 1) {
                System.out.println("\t> You limp out of the dungeon, weak from battle.\n");
                break;
            }

            System.out.println("---------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + health + " HP left. #");
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You now have " + numHealthPotions + " health potion(s). # ");

            }
            System.out.println("---------------------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command!");
                input = in.nextLine();
            }
            // options function

            if (input.equals("1")) {
                System.out.println("You continue your adventure!");
            } else if (input.equals("2")) {
                System.out.println("You defeated " + gameCount + " monster(s). So your score is " + gameCount);
                break;
            }
        }

        credits();

    }

    private static int isServant(String enemy, int galioHealth) {
        if (enemy.equals("Servant of Galio")) {
            System.out.println("\nYou are not worthy of Galio's presence. He grows stronger.");
            galioHealth = +75;
            return galioHealth;
        }
        return galioHealth;
    }

    public static void credits() {
        System.out.println("\t########################");
        System.out.println("\t#  THANKS FOR PLAYING  #");
        System.out.println("\t########################");
        System.out.println("\tGame created by: ");
        System.out.println("\tEgecan Aktan");

    }

}