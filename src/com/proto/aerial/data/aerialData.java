package com.proto.aerial.data;

import org.powbot.api.Area;
import org.powbot.api.Tile;

public class aerialData {
    public static int AlryTheAngler = 8521;
    public static String FishingSpot = "Fishing spot";
    public static int GroundWorm = 2162;
    public static int equippedBird = 22817;
    public static int thrownBird = 22816;
    public static int FishChunks = 22818;
    public static int[] CutItems = {22826, 22832, 22829, 22835};

    public static Area fishingArea = new Area(
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
