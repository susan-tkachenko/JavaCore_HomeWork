package lesson1;

public class Main {

    public static void main(String[] args) {
        Team team = new Team(
                "Rocket",
                new Player("Alex", 250),
                new Player("Vasiliy", 75),
                new Player("Boris", 120),
                new Player("Vladimir", 500)
        );
        Course course = new Course(new Obstacle[]{
                new Obstacle(60),
                new Obstacle(20),
                new Obstacle(40)
        });

        team.showInfo();
        course.doIt(team);
        team.showResults();
        team.showInfo();
    }

}
