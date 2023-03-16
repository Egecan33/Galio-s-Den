# Galio-s-Den
Game by Egecan Aktan

Add attack potion (power modifier) to the game.

Add funtion to bosses that increase the score when they are killed. Current: the score is the number of  enemies killed. So figure a base value for the score and store it into an integer than displayes the total score by adding to it at the end.

Add instructions to the game.





________________________________________________________


public class Game {

    private LearningAI ai;

    public Game() {
        ai = new LearningAI("ai_data.txt");
    }

    public void play() {
        // Game logic here
