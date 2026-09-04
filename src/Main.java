import java.io.BufferedReader;
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

    public static void draw(List<List<String>> array, int error){
        switch (error){
            case 0:
                showArrayList(array);
                break;
            case 1:
                array.get(2).set(4, "O");
                showArrayList(array);
                break;
            case 2:
                array.get(3).set(3, "|");
                showArrayList(array);
                break;
            case 3:
                array.get(3).set(2, "/");
                showArrayList(array);
                break;
            case 4:
                array.get(3).set(4, "|");
                showArrayList(array);
                break;
            case 5:
                array.get(4).set(3, "|");
                showArrayList(array);
                break;
            case 6:
                array.get(4).set(4, "|");
                showArrayList(array);
                break;
        }
    }

    public static void game(String word, String mask, Scanner in, List<List<String>> array){
        char letter;
        char[] arrWord = word.toCharArray();
        char[] arrMask = mask.toCharArray();
        int error = 0;

        while((error < 6) && !(word.equals(mask))){
            System.out.println(mask);
            letter = validationLetter(in);
            int indexWord = word.indexOf(String.valueOf(letter));

            if(indexWord >= 0){
                int indexMask = mask.indexOf(String.valueOf(letter));
                if(indexMask == -1){
                    for(int i = 0; i < arrWord.length; i++){
                        if(letter == arrWord[i]){
                            arrMask[i] = letter;
                        }
                    }
                    mask = new String(arrMask);
                    System.out.println("Error = " + error);
                    draw(array, error);
                }
                else{
                    System.out.println("Error ! You already enter this letter !");
                    System.out.println("Error = " + error);
                    draw(array, error);
                }
            }
            else{
                error++;
                System.out.println("This letter is not in the word");
                System.out.println("Error = " + error);
                draw(array, error);
            }
        }
        if(word.equals(mask)){
            System.out.println("You win !");
        }
        else{
            System.out.println("You lose !");
        }
    }

    public static char validationLetter(Scanner scanner){
        char letter = 0;
        boolean check = false;
        while(!check){
            System.out.println("Enter letter of guess: ");
            letter = scanner.next().charAt(0);
            if((letter >= 'а' && letter <= 'я') || letter == 'ё'){
                check = true;
            }
            else{
                System.out.println("Letter must be lowercase, from 'a' to 'я'.");
            }
        }
        return letter;
    }

    public static List<List<String>> startArrayList(){
        List<List<String>> arr = new ArrayList<>();
        arr.add(new ArrayList<>(List.of("|", "-", "-", "-", "", "")));
        arr.add(new ArrayList<>(List.of("|", "", "", "", "|", "")));
        arr.add(new ArrayList<>(List.of("|", "", "", "", "", "")));
        arr.add(new ArrayList<>(List.of("|", "", "", "", "", "")));
        arr.add(new ArrayList<>(List.of("|", "", "", "", "", "")));
        arr.add(new ArrayList<>(List.of("|", "", "", "", "", "")));
        arr.add(new ArrayList<>(List.of("_", "_", "_", "_", "_", "")));
        return arr;
    }

    public static void showArrayList(List<List<String>> arr){
        for(int i = 0; i < arr.size(); i++){
            for(int j = 0; j < arr.get(i).size(); j++){
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "words.txt";
        ArrayList<String> array = fileToArray(fileName);
        boolean game = true;

        while(game){
            game = askToStart(scanner);
            if(game){
                String word = rndWord(array);

                List<List<String>> arr = startArrayList();
                showArrayList(arr);

                game(word, maskWord(word), scanner, arr);
            }
        }
    }
}