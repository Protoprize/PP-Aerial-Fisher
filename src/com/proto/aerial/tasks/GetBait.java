package com.proto.aerial.tasks;

import com.proto.aerial.data.AerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class GetBait extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(AerialData.ID_GROUND_WORM).isEmpty() && Inventory.stream().id(AerialData.ID_FISH_CHUNKS).isEmpty() && Inventory.stream().id(AerialData.CUT_ITEMS).isEmpty() || Inventory.isFull();
    }

    @Override
    public void execute() {

        GroundItem worm = GroundItems.stream().id(AerialData.ID_GROUND_WORM).nearest().first();
        if (worm.valid()) {
            if (!worm.inViewport()) {
                Camera.turnTo(worm);
            } else {
                worm.interact("Take");
                Condition.wait(() -> !GroundItems.stream().filter(groundItem -> groundItem.tile().equals(worm.tile())).id(AerialData.ID_GROUND_WORM).first().valid(), 450, 39);
            }
        }
    }
}
