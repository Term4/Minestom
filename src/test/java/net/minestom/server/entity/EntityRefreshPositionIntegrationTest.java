package net.minestom.server.entity;

import net.minestom.server.coordinate.Pos;
import net.minestom.testing.Env;
import net.minestom.testing.EnvTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnvTest
public class EntityRefreshPositionIntegrationTest {

    @Test
    public void refreshBackToLastSyncedPositionApplies(Env env) {
        var instance = env.createFlatInstance();
        var entity = new Entity(EntityTypes.ZOMBIE);
        Pos start = new Pos(0.5, 42, 0.5);
        entity.setInstance(instance, start).join();

        // silent refreshes leave lastSyncedPosition at the start position
        entity.refreshPosition(new Pos(0.5, 43, 0.5), false, false);
        assertEquals(43, entity.getPosition().y());

        entity.refreshPosition(start, false, false);
        assertEquals(start, entity.getPosition());
        entity.remove();
    }
}
