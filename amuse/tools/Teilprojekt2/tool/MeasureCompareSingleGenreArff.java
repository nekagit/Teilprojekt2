import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MeasureCompareSingleGenreArff {

    public static void main(String[] args) throws IOException {
        // rootDirectory -> Measures /// fileExtension -> arff für measures /// -> allArffFiles da sind alle zu vergleichenden Datein drin
        File measurePath = new File(args[0]);
        String fileExtension = ".arff";
        String varibaleForExtraction = args[1];
        ArrayList<File> allArffFiles = new ArrayList<File>();
        List<String> allExtractedData = new ArrayList<String>();
        List<File> allTestCaseDirectories = checkForDirectories(new ArrayList<File>(Arrays.asList(Objects.requireNonNull(measurePath.listFiles()))));
        for(File testCaseDirectory : allTestCaseDirectories) {

            /// Initializes the DataStrucutre with GenreName Positive and Negativecases
            initializeAllTestCases(allExtractedData, testCaseDirectory, varibaleForExtraction);
            /// checkAllDirecotriesForArff nutzt alle Methoden um die Suche nach den Arff datein zu ermöglichen um sie global zu speichern in
            /// allArffFiles damit später die Ekstraktion der Kennzahlen und der spätere Vergelich ermöglicht wird
            checkAllDirecotriesForArff(allArffFiles, testCaseDirectory, fileExtension);

            for( File file : allArffFiles){
                extractDataFromArff(file, allExtractedData, varibaleForExtraction);
            }

        }

        System.out.println(allExtractedData);
    }

    private static void initializeAllTestCases(List<String> allExtractedData, File testCaseDirectory,String varibaleForExtraction ) {
        String testCase = testCaseDirectory.getName();
        int lastIndexBeforeCases = testCase.lastIndexOf("n");
        String cases = testCase.substring(lastIndexBeforeCases + 1);
        int positive = Integer.parseInt(cases.split(",")[0]);
        String toChopEnding = cases.split(",")[1];
        StringBuilder sb = new StringBuilder(toChopEnding);
        sb.deleteCharAt(sb.length()-1);
        int negative = Integer.parseInt(String.valueOf(sb));
        List<File> genreDirectory = checkForDirectories(new ArrayList<File>(Arrays.asList(Objects.requireNonNull(testCaseDirectory.listFiles()))));
        String genreName = genreDirectory.get(0).getName();
        allExtractedData.add(genreName);
        allExtractedData.add("Positive Testcases: " + positive);
        allExtractedData.add("Negative Testcases: " + negative);
        allExtractedData.add("Variable used: " + varibaleForExtraction);
    }

    private static void extractDataFromArff(File arffFile, List<String> allExtractedData, String varibaleForExtraction) throws IOException {
        List<String> extractedData = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(arffFile));
        String line;
        while((line = in.readLine()) != null)
        {
            if(varibaleForExtraction.equals("balanced")) {

                if(line.contains("Balanced relative error")){
                    extractedData.add(line);
                }

            } else if(varibaleForExtraction.equals("precision")) { //TODO HIER MUSS DER STRING ERSETZT WERDEN FÜR DIE EXTRAKTION

                if(line.contains("Balanced relative error")){
                    extractedData.add(line);
                }

            } else if(varibaleForExtraction.equals("recall")) { //TODO HIER MUSS DER STRING ERSETZT WERDEN FÜR DIE EXTRAKTION

                if(line.contains("Balanced relative error")){
                    extractedData.add(line);
                }

            } else {
                System.out.println("wrong variable parameter");
            }

        }
        in.close();
        int last1 = extractedData.get(0).lastIndexOf(",");
        int last2 = extractedData.get(1).lastIndexOf(",");
        allExtractedData.add(arffFile.getName());
        allExtractedData.add(extractedData.get(0).substring(last1 + 2));
        allExtractedData.add(extractedData.get(1).substring(last2 + 2));

    }

    public static void checkAllDirecotriesForArff(ArrayList<File> allArffFiles, File measurePath, String fileExtension) {
        List<File> currentFilesInDirectory = new ArrayList<File>(Arrays.asList(Objects.requireNonNull(measurePath.listFiles())));
        checkForArff(fileExtension, allArffFiles, currentFilesInDirectory);
        List<File> allDirectoriesInCurrentDirectory = currentFilesInDirectory.stream().filter(File::isDirectory).collect(Collectors.toList());

        if(allDirectoriesInCurrentDirectory.isEmpty()){

                //System.out.println("NO DIRECTORIES AVAILABLE");
            }else {
                for (File file : allDirectoriesInCurrentDirectory) {
                    checkAllDirecotriesForArff(allArffFiles, new File(file.getAbsolutePath()),fileExtension);
                }
            }
    }

    public static void checkForArff(String fileExtension, ArrayList<File> allArffFiles, List<File> currentFilesInDirectory) {
        List<File> arffFiles = currentFilesInDirectory.stream().filter(File::isFile).collect(Collectors.toList());
        System.out.println(currentFilesInDirectory);
        for(int i = 0; i < arffFiles.size(); i++) {
            if(getFileExtension(arffFiles.get(i)).contains(fileExtension)){
               // System.out.println("arff gefunden");
                allArffFiles.add(arffFiles.get(i));
                System.out.println("\n\n");
                System.out.println("ich füge arff files ein");
                System.out.println(arffFiles);
                System.out.println(arffFiles.get(i));
            } else {
               // System.out.println("kein arff gefunden");
            }
        }
    }

    private static List<File> checkForDirectories(List<File> currentFilesInDirectory) {
        return currentFilesInDirectory.stream().filter(File::isDirectory).collect(Collectors.toList());
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}
