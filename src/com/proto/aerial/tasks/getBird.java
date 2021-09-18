package com.proto.aerial.tasks;

import com.proto.aerial.aerialData;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;

public class getBird extends Task{
    @Override
    public boolean activate() {
        return Equipment.stream().id(aerialData.equippedBird).isEmpty() && Equipment.stream().id(aerialData.thrownBird).isEmpty();
    }

    @Override
    public void execute() {
        Component cont = Components.stream().text("Tap here to continue").first();
        if (cont.valid()) {
            cont.click();
            Condition.sleep(Random.nextInt(1200, 1800));
        } else {
            Npc npc = Npcs.stream().id(aerialData.AlryTheAngler).firstOrNull();
            if (npc != null) {
                if (npc.interact("Get bird")) {
                    Condition.wait(() -> Widgets.widget(217).component(4).valid(), 200, 20);
                }
            }
        }
    }
}
