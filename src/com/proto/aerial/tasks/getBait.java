package com.proto.aerial.tasks;

import com.proto.aerial.aerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class getBait extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(aerialData.GroundWorm).isEmpty() && Inventory.stream().id(aerialData.FishChunks).isEmpty() && Inventory.stream().id(aerialData.CutItems).isEmpty() || Inventory.isFull();
    }

    @Override
    public void execute() {

        GroundItem worm = GroundItems.stream().id(aerialData.GroundWorm).nearest().first();
        if (worm.valid()) {
            if (!worm.inViewport()) {
                Camera.turnTo(worm);
            } else {
                worm.interact("Take");
                Condition.wait(() -> !GroundItems.stream().filter(groundItem -> groundItem.tile().equals(worm.tile())).id(aerialData.GroundWorm).first().valid(), 450, 39);
            }
        }
    }
}
