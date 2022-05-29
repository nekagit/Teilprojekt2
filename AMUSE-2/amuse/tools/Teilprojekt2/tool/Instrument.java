import java.io.IOException;

public class Instrument {
    public int iD;
    public String name;
    public int styleID;
    public String pfad;
    public int lowestPitch;
    public int highestPitch;
    public Instrument(int piD, String pname, int pstyleID, String ppfad, int plowestPitch, int phighestPitch){
        iD = piD;
        name = pname;
        styleID = pstyleID;
        pfad = ppfad;
        lowestPitch = plowestPitch;
        highestPitch = phighestPitch;
    }
    public String toString(){
        return iD + ", " + name + ", " + styleID + ", " + pfad + ", " + lowestPitch + ", " + highestPitch;
    }
}