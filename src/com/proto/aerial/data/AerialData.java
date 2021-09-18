package com.proto.aerial.data;

import org.powbot.api.Area;
import org.powbot.api.Tile;

public class AerialData {
    public static final int ALRY_NPC = 8521;
    public static final String STRING_FISHING_SPOT = "Fishing spot";
    public static final int ID_GROUND_WORM = 2162;
    public static final int ID_EQUIPPED_BIRD = 22817;
    public static final int ID_THROWN_BIRD = 22816;
    public static final int ID_FISH_CHUNKS = 22818;
    public static final int[] CUT_ITEMS = {22826, 22832, 22829, 22835};

    public static final Area FISHING_AREA = new Area(
                    new Tile(1362, 3636, 0),
                    new Tile(1362, 3631, 0),
                    new Tile(1366, 3628, 0),
                    new Tile(1365, 3627, 0),
                    new Tile(1359, 3630, 0),
                    new Tile(1359, 3637, 0),
                    new Tile(1365, 3641, 0),
                    new Tile(1365, 3639, 0)
    );
}
