import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Path sectionCoordinatesText = Path.of("cover-img/cheque-section-coordinates.txt");
        Path chequeDataText = Path.of("input-text/cheque-data.txt");

        File coverImg = new File("cover-img/hdfc.png");

        File coverOutImg = new File("output-img/hdfc-cover.png");

        HashMap<String, HashMap<String, HashMap<String, Integer>>> sectionCoordinates = getSectionCoordinates(
                sectionCoordinatesText);

        HashMap<String, String> chequeData = getChequeDataFromText(chequeDataText);

        System.out.println("WRITING TEXT ON IMAGE");
        System.out.println(Arrays.asList(sectionCoordinates));
        System.out.println(Arrays.asList(chequeData));

        ImgOperation.writeTextOnImage(coverImg, sectionCoordinates, chequeData, coverOutImg);

        System.out.println("WRITING TEXT ON IMAGE DONE...");

    }

    private static HashMap<String, String> getChequeDataFromText(Path chequeDataText) {

        HashMap<String, String> chequeData = new HashMap<String, String>();

        // Creating an object of Scanner class
        Scanner sc;
        try {
            sc = new Scanner(chequeDataText);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strArr = line.split("-");
                chequeData.put(strArr[0],strArr[1]);

            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return chequeData;
    }

    private static HashMap<String, HashMap<String, HashMap<String, Integer>>> getSectionCoordinates(
            Path sectionCoordinatesText) {

        HashMap<String, HashMap<String, HashMap<String, Integer>>> sectionCoordinates = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();

        // Creating an object of Scanner class
        Scanner sc;
        try {
            sc = new Scanner(sectionCoordinatesText);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strArr = line.split(" ");
                sectionCoordinates.put(strArr[0],
                        new HashMap<String, HashMap<String, Integer>>() {
                            {
                                put("start",
                                        new HashMap<String, Integer>() {
                                            {
                                                put("x", Integer.valueOf(strArr[1]));
                                                put("y", Integer.valueOf(strArr[2]));

                                            }
                                        });
                                put("end",
                                        new HashMap<String, Integer>() {
                                            {
                                                put("x", Integer.valueOf(strArr[3]));
                                                put("y", Integer.valueOf(strArr[4]));

                                            }
                                        });
                            }

                        });

              
            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sectionCoordinates;
    }
}