package HeroStrategies.MoveStrategy;

public sealed interface BaseMoveStrategy permits WalkingStrategy,
                                                 HorseRidingStrategy,
                                                 FlyingStrategy {
    void move(float[] from, float[] to);
    float getMoveSpeed();
}