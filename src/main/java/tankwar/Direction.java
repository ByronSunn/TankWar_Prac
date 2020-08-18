package tankwar;

import java.awt.*;

public enum Direction {

    UP("U",0,-1),
    DOWN("D",0,1),
    LEFT("L",-1,0),
    RIGHT("R",1,0),

    UPLEFT("LU",-1,-1),
    UPRIGHT("RU",1,-1),
    DOWNLEFT("LD",-1,1),
    DOWNRIGHT("RD",1,1);

    private final String abbrev;
    final int xFactor;
    final int yFactor;


    Direction(String abbrev, int xFactor, int yFactor) {
        this.abbrev = abbrev;
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    Image getImage(String prefix){
        return Tools.getImage(prefix+abbrev+".gif");
    }

}
