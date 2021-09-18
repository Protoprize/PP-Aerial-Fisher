package com.proto.aerial.tasks;

import com.proto.aerial.aerialData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

public class cutBait extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && !Inventory.stream().id(aerialData.CutItems).isEmpty();
    }

    @Override
    public void execute() {
        if (!Equipment.stream().id(aerialData.thrownBird).isEmpty()) {
            Condition.wait(() -> !Equipment.stream().id(aerialData.equippedBird).isEmpty(), 200, 40);
        } else {
            Item knife = Inventory.stream().name("Knife").first();
            Item cut = Inventory.stream().id(aerialData.CutItems).first();

            if (knife.valid() && cut.valid()) {
                if (knife.interact("Use")) {
                    Condition.wait(() -> Inventory.selectedItem().valid(), 200, 30);
                    if (Inventory.selectedItem().valid()) {
                        int cookinglvl = Skills.level(Skill.Cooking.getIndex());
                        cut.interact("Use");
                        Condition.wait(() -> Inventory.stream().id(aerialData.CutItems).isEmpty() || Skills.level(Skill.Cooking.getIndex()) != cookinglvl, 300, 100);
                    }
                }

            }
        }
    }
}
