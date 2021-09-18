package com.proto.aerial.tasks;

import com.proto.aerial.data.AerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

public class CutBait extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && !Inventory.stream().id(AerialData.CUT_ITEMS).isEmpty();
    }

    @Override
    public void execute() {
        if (!Equipment.stream().id(AerialData.ID_THROWN_BIRD).isEmpty()) {
            Condition.wait(() -> !Equipment.stream().id(AerialData.ID_EQUIPPED_BIRD).isEmpty(), 200, 40);
        } else {
            Item knife = Inventory.stream().name("Knife").first();
            Item cut = Inventory.stream().id(AerialData.CUT_ITEMS).first();

            if (knife.valid() && cut.valid()) {
                if (knife.interact("Use")) {
                    Condition.wait(() -> Inventory.selectedItem().valid(), 200, 30);
                    if (Inventory.selectedItem().valid()) {
                        int x = Skills.level(Skill.Cooking.getIndex());
                        cut.interact("Use");
                        Condition.wait(() -> Inventory.stream().id(AerialData.CUT_ITEMS).isEmpty() || Skills.level(Skill.Cooking.getIndex()) != x, 300, 100);
                    }
                }

            }
        }
    }
}
