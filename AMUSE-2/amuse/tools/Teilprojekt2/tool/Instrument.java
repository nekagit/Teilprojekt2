import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    public int iD;
    public String name;
    public int styleID;
    public String styleName;
    public String pfad;
    public int lowestPitch;
    public int highestPitch;
    public ArrayList<String> Notes;
    public Instrument(int piD, String pname, int pstyleID, String pstyleName, String ppfad, int plowestPitch, int phighestPitch){
        iD = piD;
        name = pname;
        styleID = pstyleID;
        styleName = pstyleName
        pfad = ppfad;
        lowestPitch = plowestPitch;
        highestPitch = phighestPitch;
    }
    public String toString(){
        return iD + ", " + name + ", " + styleID + ", " + styleName + ", " + pfad + ", " + lowestPitch + ", " + highestPitch;
    }

    public void add
}