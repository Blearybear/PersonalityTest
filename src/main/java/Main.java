//Extensions are purely optional. Some possible ideas are:
//
//Provide questions that the user can answer in the program
//Give more information about the personality dimension
//Give the user more specific information about their answers.
//Provide a nicer message to the user.
//Provide questions on the program itself so that the user doesn’t have to input a file name.

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static ArrayList<String> nameLines = new ArrayList<>(), codeLines = new ArrayList<>(), types = new ArrayList<>();
    private static ArrayList<PersonalityTest> personalityTests = new ArrayList<>();
    private static int[] infpMatch = {1, 1, 1, 2, 1, 2, 1, 1, -2, -2, -2, -2, -2, -2, -2, -2}; //Data for compatibility calculator based of MBTI types
    private static int[] enfpMatch = {1, 1, 2, 1, 2, 1, 1, 1, -2, -2, -2, -2, -2, -2, -2, -2};
    private static int[] infjMatch = {1, 2, 1, 1, 1, 1, 1, 2, -2, -2, -2, -2, -2, -2, -2, -2};
    private static int[] enfjMatch = {2, 1, 1, 1, 1, 1, 1, 1, 2, -2, -2, -2, -2, -2, -2, -2};
    private static int[] intjMatch = {1, 2, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, -1, -1, -1, -1};
    private static int[] entjMatch = {2, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] intpMatch = {1, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0, -1, -1, -1, 2};
    private static int[] entpMatch = {1, 1, 2, 1, 2, 1, 1, 1, 0, 0, 0, 0, -1, -1, -1, -1};
    private static int[] isfpMatch = {-2, -2, -2, 2, 0, 0, 0, 0, -1, -1, -1, -1, 0, 2, 0, 2};
    private static int [] esfpMatch = {-2, -2, -2, -2, 0, 0, 0, 0, -1, -1, -1, -1, 2, 0, 2, 0};
    private static int[] istpMatch = {-2, -2, -2, -2, 0, 0, 0, 0, -1, -1, -1, -1, 0, 2, 0, 2};
    private static int[] estpMatch = {-2, -2, -2, -2, 0, 0, 0, 0, -1, -1, -1, -1, 2, 0, 2, 0};
    private static int[] isfjMatch = {-2, -2, -2, -2, -1, 0, -1, -1, 0, 2, 0, 2, 1, 1, 1, 1};
    private static int[] esfjMatch = {-2, -2, -2, -2, -1, 0, -1, -1, 2, 0, 2, 0, 1, 1, 1, 1};
    private static int[] istjMatch = {-2, -2, -2, -2, -1, 0, -1, -1, 0, 2, 0, 2, 1, 1, 1, 1};
    private static int[] estjMatch = {-2, -2, -2, -2, -1, 0, 2, -1, 2, 0, 2, 0, 1, 1, 1, 1};
    private static String[] sixteenTypes = {"INFP", "ENFP", "INFJ", "ENFJ", "INTJ", "ENTJ", "INTP", "ENTP", "ISFP", "ESFP", "ISTP", "ESTP", "ISFJ", "ESFJ", "ISTJ", "ESTJ"};
    public static void main(String[] args)
    {
        ArrayList<String> lines = new ArrayList<>();
        while (lines.isEmpty()){ //makes the program immediately prompt the user again after potential errors
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            try {
                String content = new String(Files.readAllBytes(Paths.get(fileName))); //reads file into a string
                String[] line = (content.split("\n")); //splits the data by each indent and saves it into a 1D array
                for (int i = 0;i < line.length;i++){
                    lines.add(line[i]);
                }
            }
            catch (IOException e) { //Handles incorrect file name error elegantly
                System.out.println("Please enter a valid file name.");
            }
        }
        for (int i = 0; i < lines.size(); i++) { //separates the names and the personality test data into separate arraylists
            if (i % 2 == 0) {
                nameLines.add(lines.get(i));
            } else {
                codeLines.add(lines.get(i));
            }
        }
        for (int i = 0;i < codeLines.size();i++){ //Creates a new personalityTest object for each personalityTest present in the data
            personalityTests.add(new PersonalityTest(i));
            types.add(personalityTests.get(i).getType());
        }
//        printPersonalities();
        Scanner scanner = new Scanner(System.in);
        String name = "";
        while (!name.equals("m")){ //The method allowing users to identify the specific MBTI type of any existing person in the data
            System.out.println("Type Finder");
            System.out.println("[\"m\" to move on]\nEnter name: ");
            name = scanner.nextLine();
            int index = -1;
            for (int i = 0;i < nameLines.size();i++){
                if (nameLines.get(i).equals(name)){
                    index = i;
                }
            }
            if (index != -1){
                personalityDescription(index);
            }
            else if (!name.equals("m")){
                System.out.println("Name entered is invalid.");
            }
        }
        String firstName = "";
        String secondName = "";
        while (!firstName.equals("e")){
            System.out.println("Compatibility Calculator");
            System.out.println("[\"e\" to end program]\nEnter first name: ");
            firstName = scanner.nextLine();
            int firstIndex = -1;
            for (int i = 0;i < nameLines.size();i++){ //locating the position of name in the nameLines arraylist in order to retrieve corresponding personality test data
                if (nameLines.get(i).equals(firstName)){
                    firstIndex = i;
                }
            }
            if (firstIndex == -1){
                System.out.println("Name entered is invalid.");
            }
            else {
                System.out.println("Enter second name: ");
                secondName = scanner.nextLine();
                int secondIndex = -1;
                for (int i = 0;i < nameLines.size();i++){
                    if (nameLines.get(i).equals(secondName)){
                        secondIndex = i;
                    }
                }
                if (secondIndex == -1){
                    System.out.println("Name entered is invalid.");
                }
                else {
                    compatibility(firstIndex, secondIndex); //moves on to the next part of the program
                }
            }
        }
    }
    public ArrayList<String> getCode(){
        return codeLines;
    }
    public static void printPersonalities(){ //print names along with corresponding personality types
        for (int i = 0;i < codeLines.size();i++){
            System.out.println(nameLines.get(i));
            System.out.println(types.get(i));
        }
    }

    public static void personalityDescription(int index){ //print personality type descriptions based on type
        System.out.println("Type: " + types.get(index));
        if (types.get(index).contains("X")){
            System.out.println("Unfortunately as certain characteristic leanings are tested as exactly equal, no description can be given.");
        }
        else {
            switch (types.get(index)){
                case "ISTJ":
                    System.out.println("Quiet, serious, earn success by being thorough and dependable. Practical, matter-of-fact, realistic, and responsible. Decide logically what should be done and work toward it steadily, regardless of distractions. Take pleasure in making everything orderly and organized—their work, their home, their life. Value traditions and loyalty.");
                    break;
                case "ISFJ":
                    System.out.println("Quiet, friendly, responsible, and conscientious. Committed and steady in meeting their obligations. Thorough, painstaking, and accurate. Loyal, considerate, notice and remember specifics about people who are important to them, concerned with how others feel. Strive to create an orderly and harmonious environment at work and at home.");
                    break;
                case "INFJ":
                    System.out.println("Seek meaning and connection in ideas, relationships, and material possessions. Want to understand what motivates people and are insightful about others. Conscientious and committed to their firm values. Develop a clear vision about how best to serve the common good. Organized and decisive in implementing their vision.");
                    break;
                case "INTJ":
                    System.out.println("Have original minds and great drive for implementing their ideas and achieving their goals. Quickly see patterns in external events and develop long-range explanatory perspectives. When committed, organize a job and carry it through. Skeptical and independent, have high standards of competence and performance—for themselves and others.");
                    break;
                case "ISTP":
                    System.out.println("Tolerant and flexible, quiet observers until a problem appears, then act quickly to find workable solutions. Analyze what makes things work and readily get through large amounts of data to isolate the core of practical problems. Interested in cause and effect, organize facts using logical principles, value efficiency.");
                    break;
                case "ISFP":
                    System.out.println("Quiet, friendly, sensitive, and kind. Enjoy the present moment, what's going on around them. Like to have their own space and to work within their own time frame. Loyal and committed to their values and to people who are important to them. Dislike disagreements and conflicts; don't force their opinions or values on others.");
                    break;
                case "INFP":
                    System.out.println("Idealistic, loyal to their values and to people who are important to them. Want to live a life that is congruent with their values. Curious, quick to see possibilities, can be catalysts for implementing ideas. Seek to understand people and to help them fulfill their potential. Adaptable, flexible, and accepting unless a value is threatened.");
                    break;
                case "INTP":
                    System.out.println("Seek to develop logical explanations for everything that interests them. Theoretical and abstract, interested more in ideas than in social interaction. Quiet, contained, flexible, and adaptable. Have unusual ability to focus in depth to solve problems in their area of interest. Skeptical, sometimes critical, always analytical.");
                    break;
                case "ESTP":
                    System.out.println("Flexible and tolerant, take a pragmatic approach focused on immediate results. Bored by theories and conceptual explanations; want to act energetically to solve the problem. Focus on the here and now, spontaneous, enjoy each moment they can be active with others. Enjoy material comforts and style. Learn best through doing.");
                    break;
                case "ESFP":
                    System.out.println("Outgoing, friendly, and accepting. Exuberant lovers of life, people, and material comforts. Enjoy working with others to make things happen. Bring common sense and a realistic approach to their work and make work fun. Flexible and spontaneous, adapt readily to new people and environments. Learn best by trying a new skill with other people.");
                    break;
                case "ENFP":
                    System.out.println("Warmly enthusiastic and imaginative. See life as full of possibilities. Make connections between events and information very quickly, and confidently proceed based on the patterns they see. Want a lot of affirmation from others, and readily give appreciation and support. Spontaneous and flexible, often rely on their ability to improvise and their verbal fluency.");
                    break;
                case "ENTP":
                    System.out.println("Quick, ingenious, stimulating, alert, and outspoken. Resourceful in solving new and challenging problems. Adept at generating conceptual possibilities and then analyzing them strategically. Good at reading other people. Bored by routine, will seldom do the same thing the same way, apt to turn to one new interest after another.");
                    break;
                case "ESTJ":
                    System.out.println("Practical, realistic, matter-of-fact. Decisive, quickly move to implement decisions. Organize projects and people to get things done, focus on getting results in the most efficient way possible. Take care of routine details. Have a clear set of logical standards, systematically follow them and want others to also. Forceful in implementing their plans.");
                    break;
                case "ESFJ":
                    System.out.println("Warmhearted, conscientious, and cooperative. Want harmony in their environment, work with determination to establish it. Like to work with others to complete tasks accurately and on time. Loyal, follow through even in small matters. Notice what others need in their day-to-day lives and try to provide it. Want to be appreciated for who they are and for what they contribute.");
                    break;
                case "ENFJ":
                    System.out.println("Warm, empathetic, responsive, and responsible. Highly attuned to the emotions, needs, and motivations of others. Find potential in everyone, want to help others fulfill their potential. May act as catalysts for individual and group growth. Loyal, responsive to praise and criticism. Sociable, facilitate others in a group, and provide inspiring leadership.");
                    break;
                case "ENTJ":
                    System.out.println("Frank, decisive, assume leadership readily. Quickly see illogical and inefficient procedures and policies, develop and implement comprehensive systems to solve organizational problems. Enjoy long-term planning and goal setting. Usually well informed, well read, enjoy expanding their knowledge and passing it on to others. Forceful in presenting their ideas.");
                    break;
            }
        }
    }
    public static void compatibility(int firstIndex, int secondIndex){ //compatibility calculator based on MBTI types of two existing individuals in the data
        System.out.println("Compatibility Scale: [-2, -1, 0, 1, 2]");
        System.out.println("Type 1: " + types.get(firstIndex));
        System.out.println("Type 2: " + types.get(secondIndex));
        int index = -1;
        for (int i = 0;i < sixteenTypes.length;i++){
            if (types.get(firstIndex).equals(sixteenTypes[i])){
                index = i;
                break;
            }
        }
        int score = 0;
        switch (types.get(secondIndex)){
            case "INFP":
                score = infpMatch[index];
                break;
            case "ENFP":
                score = enfpMatch[index];
                break;
            case "INFJ":
                score = infjMatch[index];
                break;
            case "ENFJ":
                score = enfjMatch[index];
                break;
            case "INTJ":
                score = intjMatch[index];
                break;
            case "ENTJ":
                score = entjMatch[index];
                break;
            case "INTP":
                score = intpMatch[index];
                break;
            case "ENTP":
                score = entpMatch[index];
                break;
            case "ISFP":
                score = isfpMatch[index];
                break;
            case "ESFP":
                score = esfpMatch[index];
                break;
            case "ISTP":
                score = istpMatch[index];
                break;
            case "ESTP":
                score = estpMatch[index];
                break;
            case "ISFJ":
                score = isfjMatch[index];
                break;
            case "ESFJ":
                score = esfjMatch[index];
                break;
            case "ISTJ":
                score = istjMatch[index];
            case "ESTJ":
                score = estjMatch[index];
                break;
        }
        System.out.println("Compatibility score: " + score);
    }
}
