package racinggame.strategy;

import java.util.Random;

public final class DefaultRandomStrategy implements RandomStrategy {
    private static final Random ENGINE = new Random();
    private static final int ENGINE_LIMIT = 10;

    public static DefaultRandomStrategy newInstance() {
        return new DefaultRandomStrategy();
    }

    private DefaultRandomStrategy() {
    }

    @Override
    public int perform() {
        return ENGINE.nextInt(ENGINE_LIMIT);
    }
}
