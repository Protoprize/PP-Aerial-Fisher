package com.proto.aerial.tasks;

import com.proto.aerial.aerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;

public class fish extends Task {
    @Override
    public boolean activate() {
        return !Equipment.stream().id(aerialData.equippedBird).isEmpty();
    }

    @Override
    public void execute() {
        Npc spot = Npcs.stream().name(aerialData.FishingSpot).nearest().first();
        if (spot.valid()) {
            if (!spot.inViewport()) {
                Camera.turnTo(spot);
            } else {
                if (spot.interact("Catch")) {
                    Condition.wait(() -> (Equipment.stream().id(aerialData.equippedBird).isEmpty() || Npcs.stream().filter(npc -> npc.tile().equals(spot.tile()) && npc.name().equals(spot.getName())).isEmpty()), 200, 46);
                }
            }
        }
    }
}
