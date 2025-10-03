import HeroStrategies.MoveStrategy.BaseMoveStrategy;

public class Hero {
    private float[] position;
    private BaseMoveStrategy moveStrategy;

    public Hero(float[] startPosition, BaseMoveStrategy initialStrategy) {
        this.position = startPosition.clone();
        this.moveStrategy = initialStrategy;
    }

    public void move(float[] targetPosition) {
        moveStrategy.move(position, targetPosition);
        position = targetPosition.clone();
        System.out.println("Герой прибыл в точку (" + position[0] + "," + position[1] + ")");
        System.out.println("---");
    }

    public void setMoveStrategy(BaseMoveStrategy newStrategy) {
        this.moveStrategy = newStrategy;
        System.out.println("Смена способа передвижения!");
    }

    public float[] getPosition() {
        return position.clone();
    }

    public float getCurrentMoveSpeed() {
        return moveStrategy.getMoveSpeed();
    }
}
