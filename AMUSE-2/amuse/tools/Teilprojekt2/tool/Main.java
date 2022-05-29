import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        initData();
    }
    public static void initData() throws IOException{
        try {
            File instrumentsArff = new File("C:\\Users\\marka\\eclipse-workspace\\Teilprojekt2\\AMUSE-2\\amuse\\tools\\Teilprojekt2\\assets\\toolInput\\Instruments.arff");
            System.out.println(extractInstruments(instrumentsArff));
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        /// Instrument.arff/SampleLists, XML-Datei(anzahlInstrumente/Noten) ->
        // TODO Auslesen aller Samples(instrument_Note.wav)
        //  -> File[] mit den Pfaden aller Samples
        // File[], XMLInfos, -> todo andom instrument auswählen
        // -> File[] mit allesn Randomm ausgewählten Instrumenten
        //todo mithilfe von gausscher-Verteilung -> ArrayList Pfade zu allen Noten.wav Datein
        ///TODO AudioFileMixing -> wav Datei mit generierter Note
    }

    public static List<Instrument> extractInstruments(File instrumentsArff)throws IOException{
        List<Instrument> extractedIntstruments = new ArrayList<Instrument>();
        BufferedReader rd = new BufferedReader(new FileReader(instrumentsArff));
        String line;
        while((line = rd.readLine()) != null){
            if(line.contains(",")){
                int iID = Integer.parseInt(line.substring(0,line.indexOf(",")));
                String iName = line.substring(1,line.indexOf(","));
                int iStyleID = Integer.parseInt(line.substring(2,line.indexOf(",")));
                String iPfad = line.substring(3,line.indexOf(","));
                int iLowestPitch = Integer.parseInt(line.substring(4,line.indexOf(",")));
                int iHighestPitch = Integer.parseInt(line.substring(5,line.indexOf(",")));
                extractedIntstruments.add(new Instrument(iID, iName, iStyleID, iPfad, iLowestPitch, iHighestPitch));
            }
        }
        rd.close();
        return extractedIntstruments;
    }

    public static void selectSamples(int anzahlInstrumente, int anzahlNoten) {

    }

    public static void searchAllFiles() {

    }

    public static void filterCriteria() {

    }

    public static void outputFile() {

    }
}
