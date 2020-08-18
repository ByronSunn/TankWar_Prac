package tankwar;

import java.awt.*;

public enum Direction {

    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R"),

    UPLEFT("LU"),
    UPRIGHT("RU"),
    DOWNLEFT("LD"),
    DOWNRIGHT("RD");

    private final String abbrev;

    Direction(String abbrev) {
        this.abbrev = abbrev;
    }

    Image getImage(String prefix){
        return Tools.getImage(prefix+abbrev+".gif");
    }

}
