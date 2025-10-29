import HeroStrategies.MoveStrategy.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        WalkingStrategy walking = new WalkingStrategy(5.0f);
        HorseRidingStrategy horseRiding = new HorseRidingStrategy(15.0f);
        FlyingStrategy flying = new FlyingStrategy(25.0f);

        Hero hero = new Hero(new float[]{0, 0}, walking);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ДЕМОНСТРАЦИЯ ПАТТЕРНА СТРАТЕГИЯ ===\n");
        System.out.println("Доступные команды:");
        System.out.println("1 - Пешком");
        System.out.println("2 - На лошади");
        System.out.println("3 - Полетом");
        System.out.println("4 - Переместиться в точку");
        System.out.println("5 - Показать текущее состояние");
        System.out.println("0 - Выход");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.print("Введите команду: ");
            if (scanner.hasNextInt()) {
                int command = scanner.nextInt();
                scanner.nextLine();

                switch (command) {
                    case 1:
                        hero.setMoveStrategy(walking);
                        System.out.println("Установлен способ передвижения: Пешком\n");
                        break;

                    case 2:
                        hero.setMoveStrategy(horseRiding);
                        System.out.println("Установлен способ передвижения: На лошади\n");
                        break;

                    case 3:
                        hero.setMoveStrategy(flying);
                        System.out.println("Установлен способ передвижения: Полетом\n");
                        break;

                    case 4:
                        System.out.print("Введите координату X: ");
                        float x = scanner.nextFloat();
                        System.out.print("Введите координату Y: ");
                        float y = scanner.nextFloat();
                        hero.move(new float[]{x, y});
                        break;

                    case 5:
                        System.out.println("Текущая позиция героя: (" +
                                hero.getPosition()[0] + "," + hero.getPosition()[1] + ")");
                        System.out.println("Текущая скорость передвижения: " + hero.getCurrentMoveSpeed());
                        System.out.println();
                        break;

                    case 0:
                        running = false;
                        System.out.println("Выход из программы...");
                        break;

                    default:
                        System.out.println("Неизвестная команда! Попробуйте снова.\n");
                        break;
                }
            } else {
                System.out.println("Ошибка! требуется ввести целое число!");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}