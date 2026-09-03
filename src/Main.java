import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Boolean askToStart(Scanner in){
        String answer = "";
        while(!(answer.equals("n") || answer.equals("e"))){
            System.out.println("Please, enter <n> - new game or <e> - exit");
            answer = in.nextLine();
        }
        if(answer.equals("n")){
            System.out.println("New game !");
            return true;
        }
        else{
            System.out.println("Okay, bye !");
            return false;
        }
    }

    public static ArrayList<String> fileToArray(String fileName) {
        ArrayList<String> array = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            array = new ArrayList<String>();
            while ((s = br.readLine()) != null) {
                array.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return array;
    }

    public static String rndWord(ArrayList<String> array){
        Random rnd = new Random();
        return array.get(rnd.nextInt(array.toArray().length -1));
    }

    public static String maskWord(String word){
        String mask = "";
        for(int i = 0; i < word.length(); i++){
            mask = mask.concat("*");
        }
        return mask;
    }

    public static void game(String word, String mask){
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "words.txt";
        ArrayList<String> array = fileToArray(fileName);
        boolean game = true;

        while(game){
            //System.out.println(askToStart(scanner)); // true or false in cmd
            game = askToStart(scanner);
            if(game){
                String word = rndWord(array);
                System.out.println(word); // random word
                System.out.println(maskWord(word));
            }
        }
    }
}