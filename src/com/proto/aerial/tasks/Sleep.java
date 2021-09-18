package com.proto.aerial.tasks;

import com.proto.aerial.data.AerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Equipment;

public class Sleep extends Task{
    @Override
    public boolean activate() {
        return !Equipment.stream().id(AerialData.ID_THROWN_BIRD).isEmpty();
    }

    @Override
    public void execute() {
        Condition.wait(() -> !Equipment.stream().id(AerialData.ID_EQUIPPED_BIRD).isEmpty(), 200, 40);
    }
}
