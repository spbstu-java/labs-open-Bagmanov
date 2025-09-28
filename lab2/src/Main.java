import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    private static Object getDefaultValue(Class<?> type) {
        if (type.isArray()) return Array.newInstance(type.getComponentType(), 0);

        return switch (type.getName()) {
            case "int" -> 0;
            case "boolean" -> false;
            case "double" -> 0.0;
            case "long" -> 0L;
            case "float" -> 0.0f;
            case "short" -> (short) 0;
            case "byte" -> (byte) 0;
            case "char" -> '\u0000';
            default -> null;
        };
    }

    private static Object instantiateObject(Class<?> type) {
        try {
            if (type.isPrimitive()) return getDefaultValue(type);

            final var constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            System.out.println("Failed to instantiate parameter of type: " + type.getName());
            return null;
        }
    }

    private static void invokeAnnotatedMethods(Class<?> clazz) {
        for (final var method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(InvokeTimes.class)) continue;

            if (Modifier.isPublic(method.getModifiers())) {
                continue;
            }

            final var paramValues = Arrays.stream(method.getParameterTypes())
                    .map(Main::instantiateObject).toArray();
            try {
                method.setAccessible(true);

                for (var i = 0; i < method.getAnnotation(InvokeTimes.class).value(); i++) {
                    method.invoke(null, paramValues);
                    System.out.println("Invoked: " + method.getName() + " #" + (i + 1));
                }
            } catch (Exception e) {
                System.out.println("Error invoking method: " + method.getName() + " - " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        invokeAnnotatedMethods(Test.class);
    }
}