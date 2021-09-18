package com.proto.aerial.tasks;

import com.proto.aerial.data.AerialData;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;

public class GetBird extends Task{
    @Override
    public boolean activate() {
        return Equipment.stream().id(AerialData.ID_EQUIPPED_BIRD).isEmpty() && Equipment.stream().id(AerialData.ID_THROWN_BIRD).isEmpty();
    }

    @Override
    public void execute() {
        Component cont = Components.stream().text("Tap here to continue").first();
        if (cont.valid()) {
            cont.click();
            Condition.sleep(Random.nextInt(1200, 1800));
        } else {
            Npc npc = Npcs.stream().id(AerialData.ALRY_NPC).firstOrNull();
            if (npc != null) {
                if (npc.interact("Get bird")) {
                    Condition.wait(() -> Widgets.widget(217).component(4).valid(), 200, 20);
                }
            }
        }
    }
}
