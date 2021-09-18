package com.proto.aerial;

import com.proto.aerial.tasks.*;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptLoader;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;


@ScriptManifest(description = "Fishing for hunter XP", name = "PP Aerial Fisher")
public class Main extends AbstractScript {

    private ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        new Main().startScript();
    }

    @Override
    public void onStart() {
        Paint p = new PaintBuilder()
                .trackSkill(Skill.Hunter)
                .trackSkill(Skill.Fishing)
                .x(30)
                .y(65)
                .build();

        addPaint(p);

        taskList.add(new getBird());
        taskList.add(new cutBait());
        taskList.add(new getBait());
        taskList.add(new sleep());
        taskList.add(new fish());
    }

    @Override
    public void poll() {
        if(Inventory.stream().name("Knife").isEmpty()) {
            ScriptManager.INSTANCE.stop();
        }
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    ScriptManager.INSTANCE.stop();
                    break;
                }
                return;
            }
        }
    }




}
