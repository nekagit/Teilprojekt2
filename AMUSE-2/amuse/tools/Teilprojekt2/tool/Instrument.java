import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Instrument {
    public int iD;
    public String name;
    public int styleID;
    public String styleName;
    public String path;
    public int lowestPitch;
    public int highestPitch;
    public ArrayList<String> Notes;
    public Instrument(int piD, String pname, int pstyleID, String pstyleName, String ppath, int plowestPitch, int phighestPitch){
        iD = piD;
        name = pname;
        styleID = pstyleID;
        styleName = pstyleName
        path = ppath;
        lowestPitch = plowestPitch;
        highestPitch = phighestPitch;
    }
    public String toString(){
        return iD + ", " + name + ", " + styleID + ", " + styleName + ", " + path + ", " + lowestPitch + ", " + highestPitch;
    }

    public void addNotes(){
        BufferedReader rd = new BufferedReader(new FileReader(path));
        String line;
        while((line = rd.readLine()) != null){
            if(line.contains(",")){
                String[] array = line.split(",");
                Notes.add(array[1]);
            }
        }
        rd.close();
    }
}