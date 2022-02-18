package lesson1;

public class Player {

    private final String name;
    private final int maxStrength;
    private int currentStrength;
    private boolean lastCoursePassed;

    public Player(String name, int maxStrength) {
        this.name = name;
        this.maxStrength = maxStrength;
        this.currentStrength = maxStrength;
        this.lastCoursePassed = false;
    }

    public String getName() {
        return name;
    }

    public int getMaxStrength() {
        return maxStrength;
    }

    public int getCurrentStrength() {
        return currentStrength;
    }

    public boolean isLastCoursePassed() {
        return lastCoursePassed;
    }

    public void tryPassCourse(Course course) {
        boolean passedAllObstacles = true;

        Obstacle[] obstacles = course.getObstacles();
        for (int i = 0; i < obstacles.length; i++) {
            Obstacle obstacle = obstacles[i];
            if (currentStrength >= obstacle.getStrength()) {
                currentStrength = currentStrength - obstacle.getStrength();
            } else {
                currentStrength = 0;
                passedAllObstacles = false;
                break;
            }
        }

        lastCoursePassed = passedAllObstacles;
    }

    public void goToSleep() {
        currentStrength = maxStrength;
    }

}
