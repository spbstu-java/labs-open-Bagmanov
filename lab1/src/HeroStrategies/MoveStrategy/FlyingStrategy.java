package HeroStrategies.MoveStrategy;

public final class FlyingStrategy implements BaseMoveStrategy {
    private final float moveSpeed;

    public FlyingStrategy(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void move(float[] from, float[] to) {
        System.out.println("Герой летит по воздуху из (" + from[0] + "," + from[1] +
                ") в (" + to[0] + "," + to[1] + ")");
        System.out.println("Скорость передвижения: " + moveSpeed);
    }

    @Override
    public float getMoveSpeed() {
        return moveSpeed;
    }
}
