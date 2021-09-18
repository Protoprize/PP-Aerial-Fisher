package com.proto.aerial;

import com.proto.aerial.tasks.*;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
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

        taskList.add(new GetBird());
        taskList.add(new CutBait());
        taskList.add(new GetBait());
        taskList.add(new Sleep());
        taskList.add(new Fish());
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
