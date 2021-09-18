package com.proto.aerial.tasks;

import com.proto.aerial.data.aerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Equipment;

public class sleep extends Task{
    @Override
    public boolean activate() {
        return !Equipment.stream().id(aerialData.thrownBird).isEmpty();
    }

    @Override
    public void execute() {
        Condition.wait(() -> !Equipment.stream().id(aerialData.equippedBird).isEmpty(), 200, 40);
    }
}
