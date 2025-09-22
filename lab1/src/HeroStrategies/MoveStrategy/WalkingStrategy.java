package HeroStrategies.MoveStrategy;

public final class WalkingStrategy implements BaseMoveStrategy {
    private final float moveSpeed;

    public WalkingStrategy(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void move(float[] from, float[] to) {
        System.out.println("Герой идет пешком из (" + from[0] + "," + from[1] +
                ") в (" + to[0] + "," + to[1] + ")");
        System.out.println("Скорость передвижения: " + moveSpeed);
    }

    @Override
    public float getMoveSpeed() {
        return moveSpeed;
    }
}
