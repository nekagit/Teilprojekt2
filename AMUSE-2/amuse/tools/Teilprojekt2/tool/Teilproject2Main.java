package tool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teilproject2Main {
    public static void main(String[] args) throws IOException {
        initData("C:\\Users\\marka\\eclipse-workspace\\Teilprojekt2\\AMUSE-2\\amuse\\tools\\Teilprojekt2\\assets\\toolInput\\Instruments.arff");
    }
    public static void initData(String instrumentsArffPath) throws IOException{
        List<Instrument> instruments = extractInstruments(new File(instrumentsArffPath));
        for(Instrument instrument : instruments){
            instrument.addNotes();
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
                String[] array = line.split(",");
                int iID = Integer.parseInt(array[0]);
                String iName = array[1];
                int iStyleID = Integer.parseInt(array[2]);
                String istyleName = array[3];
                String iPfad = array[4];
                int iLowestPitch = Integer.parseInt(array[5]);
                int iHighestPitch = Integer.parseInt(array[6]);
                extractedIntstruments.add(new Instrument(iID, iName, iStyleID, istyleName, iPfad, iLowestPitch, iHighestPitch));
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
