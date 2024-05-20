import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonalityTest
{
    private int number;
    private String[][] groups;
    private String type;
    public PersonalityTest(int number){
        this.number = number;
        Main main = new Main();
        groups = new String[main.getCode().size()][(main.getCode().size()) * 10]; // for every personality test there are 10 lines of 7 characters
        for (int i = 0;i < groups.length;i++){
            groups[i] = main.getCode().get(i).split("(?<=\\G.{" + 7 + "})"); //splitting every 7 characters
        }
//        for (int i = 0;i < groups.length;i++){ //(TEST CODE)
//            for (int j = 0;j < groups[0].length;j++){
//                System.out.println(groups[i][j]);
//            }
//        }
        int eiA = 0, eiB = 0, snA = 0, snB = 0, tfA = 0, tfB = 0, jpA = 0, jpB = 0;
        for (int j = 0;j < groups[number].length;j++){ // counting number of each option
//          System.out.println(groups[number][j]);
            switch (groups[number][j].charAt(0)){ //checking position 1/7 of the string
                case 'a':
                case 'A':
                    eiA++;
                    break;
                case 'b':
                case 'B':
                    eiB++;
                    break;
            }
            for (int i = 1;i < 3;i++){ //checking next 2-3/7 positions of the string
                switch (groups[number][j].charAt(i)){
                    case 'a':
                    case 'A':
                        snA++;
                        break;
                    case 'b':
                    case 'B':
                        snB++;
                        break;
                }
            }
            for (int i = 3;i < 5;i++){ //checking next 4-5/7 positions of the string
                switch (groups[number][j].charAt(i)){
                    case 'a':
                    case 'A':
                        tfA++;
                        break;
                    case 'b':
                    case 'B':
                        tfB++;
                        break;
                }
            }
            for (int i = 5;i < 7;i++){ //checking last 6-7/7 positions of the string
                switch (groups[number][j].charAt(i)){
                    case 'a':
                    case 'A':
                        jpA++;
                        break;
                    case 'b':
                    case 'B':
                        jpB++;
                        break;
                }
            }
        }
        type = ""; //calculating percentages
        double peiB = Math.round(eiB/(double) (eiA + eiB) * 100); //casting denominator to double so final will be double
        if (peiB == 50.0){
            type += "X";
        }
        else if (peiB > 50.0){
            type += "I";
        }
        else type += "E";
        double psnB = Math.round(snB/(double) (snA + snB) * 100);
        if (psnB == 50.0){
            type += "X";
        }
        else if (psnB > 50.0){
            type += "N";
        }
        else type += "S";
        double ptfB = Math.round(tfB/(double) (tfA + tfB) * 100);
        if (ptfB == 50.0){
            type += "X";
        }
        else if (ptfB > 50.0){
            type += "F";
        }
        else type += "T";
        double pjpB = Math.round(jpB/(double) (jpA + jpB) * 100);
        if (pjpB == 50.0){
            type += "X";
        }
        else if (pjpB > 50.0){
            type += "P";
        }
        else type += "J";
    }
    public String getType(){
        return type;
    }





    /*
     * DONT CHANGE THE FOLLOWING FUNCTION, its for testing.
     */
    public static void run(File inputFile, File outPutFile) throws IOException
    {
        Scanner in = new Scanner(inputFile);
        FileWriter out = new FileWriter(outPutFile);
//      Main.printPersonalities();
    }
}
