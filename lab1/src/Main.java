
import HeroStrategies.MoveStrategy.*;

public class Main {

    public static void main(String[] args) {
        WalkingStrategy walking = new WalkingStrategy(5.0f);
        HorseRidingStrategy horseRiding = new HorseRidingStrategy(15.0f);
        FlyingStrategy flying = new FlyingStrategy(25.0f);

        Hero hero = new Hero(new float[]{0, 0}, walking);

        System.out.println("=== ДЕМОНСТРАЦИЯ ПАТТЕРНА СТРАТЕГИЯ ===\n");

        System.out.println("1. Перемещение пешком:");
        hero.move(new float[]{10, 10});

        hero.setMoveStrategy(horseRiding);
        System.out.println("2. Перемещение на лошади:");
        hero.move(new float[]{20, 5});

        hero.setMoveStrategy(flying);
        System.out.println("3. Перемещение полетом:");
        hero.move(new float[]{50, 30});

        hero.setMoveStrategy(walking);
        System.out.println("4. Снова пешком:");
        hero.move(new float[]{55, 32});

        System.out.println("Текущая позиция героя: (" +
                hero.getPosition()[0] + "," + hero.getPosition()[1] + ")");
        System.out.println("Текущая скорость передвижения: " + hero.getCurrentMoveSpeed());
    }
}