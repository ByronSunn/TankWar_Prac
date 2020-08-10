package tankwar;

import javax.swing.*;
import java.awt.*;

public class Tools {
    public static Image getImage(String ImageName){
        return new ImageIcon("assert/images/"+ImageName).getImage();
    }
}
