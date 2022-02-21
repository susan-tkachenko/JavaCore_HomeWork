package lesson1;

public class Course {

    private final Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public Obstacle[] getObstacles() {
        return obstacles;
    }

    public void doIt(Team team) {
        Player[] players = team.getPlayers();
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            player.tryPassCourse(this);
        }
    }

}
