import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ArffCreator {

        public static void main(String args []) throws IOException {
            File directoryPath = new File(args[0]);
            String positiveGenreName = args[1];
            int positiveGenreSetNumber = Integer.parseInt(args[2]);
            int negativeGenreSetNumber = Integer.parseInt(args[3]);
            int filesCount = 0;
            String fileNameTest = positiveGenreName.concat("_" + positiveGenreSetNumber + "_" + negativeGenreSetNumber + "_test").concat(".arff");
            String fileNameTraining = positiveGenreName.concat("_" + positiveGenreSetNumber + "_" + negativeGenreSetNumber + "_training").concat(".arff");

            /*
            * initData()
            * searchAllFiles()
            * filterCriteria()
            * outputFile()
            *
            *
            *
            * */


            String[] genreDirectorys = directoryPath.list();
            assert genreDirectorys != null;
            ArrayList<File[]> allFiles = new ArrayList<>();
            ArrayList<String> positiveFiles = new ArrayList<>();
            ArrayList<String> positiveFilesOutput = new ArrayList<>();
            ArrayList<String> negativeFilesOutput = new ArrayList<>();
            ArrayList<String> wholePaths = new ArrayList<>();

            loadAllFiles(genreDirectorys, allFiles, wholePaths, directoryPath, filesCount, positiveGenreName, positiveFiles);
            selectExperimentFiles(positiveFiles,wholePaths, positiveFilesOutput, positiveGenreSetNumber, negativeGenreSetNumber, negativeFilesOutput);
            writeAllFiles(fileNameTest, fileNameTraining, positiveFilesOutput, negativeFilesOutput, positiveGenreName);


        }

        public static void loadAllFiles(String[] genreDirectorys, ArrayList<File[]> allFiles, ArrayList<String> wholePaths, File directoryPath, int filesCount, String positiveGenreName, ArrayList<String> positiveFiles) {

            for (int i = 0; i < genreDirectorys.length; i++) {
                File currentDirectory = new File(directoryPath + "//"+ genreDirectorys[i] );
                File[] filesOfCurrentDirectory = currentDirectory.listFiles();
                allFiles.add(filesOfCurrentDirectory);
            }
            for (int i = 0; i < allFiles.size(); i++) {
                for(int j = 0; j < allFiles.get(i).length; j++) {
                    if(allFiles.get(i)[j].getAbsolutePath().contains(positiveGenreName)) {
                        positiveFiles.add( "," + "'" + allFiles.get(i)[j] +"', milliseconds, 0, -1, 1" + "\n");
                    } else {
                        wholePaths.add ( "," + "'" + allFiles.get(i)[j] +"', milliseconds, 0, -1, 0" + "\n");
                    }
                    filesCount++;
                }
            }
        System.out.println("size of all Files" + allFiles.get(0).length + allFiles.get(1).length + allFiles.get(2).length);
        }
        public static void writeAllFiles(String fileNameTest, String fileNameTraining ,ArrayList<String> positiveFilesOutput, ArrayList<String> negativeFilesOutput, String positiveGenreName ){
            String[] outputTest = new String[positiveFilesOutput.size()/2 + negativeFilesOutput.size()/2 + 13];
            int rows = positiveFilesOutput.size() + negativeFilesOutput.size();
            outputTest[0] = "@RELATION FileTable\n";
            outputTest[1] = "%rows="+ rows + "\n";
            outputTest[2] = "%columns=6\n";
            outputTest[3] = "\n";
            outputTest[4] = "@ATTRIBUTE Id NUMERIC\n";
            outputTest[5] = "@ATTRIBUTE Path STRING\n";
            outputTest[6] = "@ATTRIBUTE Unit {milliseconds,samples}\n";
            outputTest[7] = "@ATTRIBUTE Start NUMERIC\n";
            outputTest[8] = "@ATTRIBUTE End NUMERIC\n";
            outputTest[9] = "@ATTRIBUTE "+"'" +positiveGenreName+ "'" + " Numeric\n";
            outputTest[10] = "\n";
            outputTest[11] = "\n";
            outputTest[12] = "@DATA\n";
            int outputListIndex = 13;
            int counterListRemoverPositiveTest = positiveFilesOutput.size()/2;
            for (int positiveFilesIndexTest = counterListRemoverPositiveTest; positiveFilesIndexTest > 0; positiveFilesIndexTest--) {
                outputTest[outputListIndex] = positiveFilesOutput.get(positiveFilesIndexTest);
                positiveFilesOutput.remove(positiveFilesIndexTest);
                outputListIndex = outputListIndex + 1;
            }
            int counterListRemoverNegativeTest = negativeFilesOutput.size()/2;
            for (int negativeFilesIndexTest = counterListRemoverNegativeTest; negativeFilesIndexTest > 0; negativeFilesIndexTest--) {
                outputTest[outputListIndex] = negativeFilesOutput.get(negativeFilesIndexTest);
                negativeFilesOutput.remove(negativeFilesIndexTest);
                outputListIndex = outputListIndex + 1;
            }
            StringBuilder outputString = new StringBuilder();
            int fileIndex = 0;
            for ( int i = 0; i < outputTest.length; i++) {
                if(i >= 13){
                    outputString.append(String.valueOf(fileIndex)).append(outputTest[i]);
                    fileIndex++;
                }else {
                    outputString.append(outputTest[i]);
                }
            }
            try {
                FileWriter myWriter = new FileWriter(fileNameTest);
                myWriter.write(String.valueOf(outputString));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            String[] outputTraining = new String[positiveFilesOutput.size() + negativeFilesOutput.size() + 13];
            int rowsTraining = positiveFilesOutput.size() + negativeFilesOutput.size();
            outputTraining[0] = "@RELATION FileTable\n";
            outputTraining[1] = "%rows="+ rowsTraining + "\n";
            outputTraining[2] = "%columns=6\n";
            outputTraining[3] = "\n";
            outputTraining[4] = "@ATTRIBUTE Id NUMERIC\n";
            outputTraining[5] = "@ATTRIBUTE Path STRING\n";
            outputTraining[6] = "@ATTRIBUTE Unit {milliseconds,samples}\n";
            outputTraining[7] = "@ATTRIBUTE Start NUMERIC\n";
            outputTraining[8] = "@ATTRIBUTE End NUMERIC\n";
            outputTraining[9] = "@ATTRIBUTE "+"'" +positiveGenreName+ "'" + " Numeric\n";
            outputTraining[10] = "\n";
            outputTraining[11] = "\n";
            outputTraining[12] = "@DATA\n";
            int outputListIndexTraining = 13;
            int counterListRemoverPositiveTraining = positiveFilesOutput.size()-1;
            for (int positiveFilesIndexTraining = counterListRemoverPositiveTraining; positiveFilesIndexTraining >= 0; positiveFilesIndexTraining--) {
                System.out.println(positiveFilesOutput.size());
                System.out.println(positiveFilesIndexTraining);
                System.out.println(positiveFilesOutput.get(positiveFilesIndexTraining));
                outputTraining[outputListIndexTraining] = positiveFilesOutput.get(positiveFilesIndexTraining);
                positiveFilesOutput.remove(positiveFilesIndexTraining);
                outputListIndexTraining = outputListIndexTraining + 1;
            }
            int counterListRemoverNegativeTraining = negativeFilesOutput.size()-1;
            for (int negativeFilesIndexTraining = counterListRemoverNegativeTraining; negativeFilesIndexTraining >= 0; negativeFilesIndexTraining--) {
                outputTraining[outputListIndexTraining] = negativeFilesOutput.get(negativeFilesIndexTraining);
                negativeFilesOutput.remove(negativeFilesIndexTraining);
                outputListIndexTraining = outputListIndexTraining + 1;
            }
            StringBuilder outputStringTraining = new StringBuilder();
            int fileIndexTraining = 0;
            for ( int i = 0; i < outputTraining.length; i++) {
                if(i >= 13){
                    outputStringTraining.append(String.valueOf(fileIndexTraining)).append(outputTraining[i]);
                    fileIndexTraining++;
                }else {
                    outputStringTraining.append(outputTraining[i]);
                }
            }
            try {
                FileWriter myWriter = new FileWriter(fileNameTraining);
                myWriter.write(String.valueOf(outputStringTraining));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }

        public static void selectExperimentFiles(ArrayList<String> positiveFiles, ArrayList<String> wholePaths, ArrayList<String> positiveFilesOutput, int positiveGenreSetNumber, int negativeGenreSetNumber, ArrayList<String> negativeFilesOutput ){
            for(int i = 0; i < positiveGenreSetNumber*2; i++){
                if(positiveFiles.size() == 0){
                    System.out.println("too large number of positive cases");
                } else {
                    int randomPositiveFileIndex = ThreadLocalRandom.current().nextInt(0, positiveFiles.size()-i);
                    positiveFilesOutput.add(positiveFiles.get(randomPositiveFileIndex));
                    positiveFiles.remove(randomPositiveFileIndex);
                }
            }
            for(int j = 0; j < negativeGenreSetNumber*2; j++){
                if(wholePaths.size() == 0){
                    System.out.println("too large number of negative cases");
                } else {
                    int randomNegativeFileIndex = ThreadLocalRandom.current().nextInt(0, wholePaths.size()-j);
                    negativeFilesOutput.add(wholePaths.get(randomNegativeFileIndex));
                    wholePaths.remove(randomNegativeFileIndex);

                }
            }
        }

}