import java.io.IOException;

public class Teilprojekt2 {
    public static void main(String[] args) throws IOException {
        String[] argss = new String[4];
        argss[0] = "[\"C:\\Users\\install\\OneDrive - OrgaTech Solution Engineering Consulting GmbH\\Desktop\\x2go2\\Genre";
        argss[1] = "rock";
        argss[2] = "5";
        argss[3] = "5";
        ArffCreator.main(argss);
        initData();
        searchAllFiles();
        filterCriteria();
        outputFile();
    }
    public static void initData() {
        /// Instrument.arff/SampleLists, XML-Datei(anzahlInstrumente/Noten) ->
        // TODO Auslesen aller Samples(instrument_Note.wav)
        //  -> File[] mit den Pfaden aller Samples
        // File[], XMLInfos, -> todo andom instrument auswählen
        // -> File[] mit allesn Randomm ausgewählten Instrumenten
        //todo mithilfe von gausscher-Verteilung -> ArrayList Pfade zu allen Noten.wav Datein
        ///TODO AudioFileMixing -> wav Datei mit generierter Note
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
