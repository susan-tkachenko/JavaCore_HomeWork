package lesson1;

public class Team {

    private final String name;
    private final Player[] players;

    public Team(String name, Player player1, Player player2, Player player3, Player player4) {
        this(name, new Player[]{player1, player2, player3, player4});
    }

    private Team(String name, Player[] players) {
        this.name = name;
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void showInfo() {
        System.out.println("Team " + name);
        System.out.println("Players:");

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            System.out.println(player.getName() + " - current strength "
                    + player.getCurrentStrength() + "/" + player.getMaxStrength()
            );
        }
    }

    public void showResults() {
        System.out.println("Team " + name);
        System.out.print("Passed players: ");

        boolean anyOnePassed = false;
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.isLastCoursePassed()) {
                if (anyOnePassed) {
                    System.out.print(", ");
                }
                System.out.print(player.getName());
                anyOnePassed = true;
            }
        }

        if (!anyOnePassed) {
            System.out.print("no anyone");
        }
        System.out.println();
    }

}
