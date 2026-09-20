import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final String START = "n";
    private static final String EXIT = "e";
    private static final String MASK_LETTER = "*";
    private static final char FIRST_LETTER = 'а';
    private static final char LAST_LETTER = 'я';
    private static final char SPECIAL_LETTER = 'ё';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "words.txt";
        ArrayList<String> words = readFile(fileName);
        boolean game = true;

        while(game){
            game = askToStart(scanner);
            if(game){
                String word = getRandomWord(words);
                System.out.println(HangmanRenderer.render(0));
                doGame(word, scanner);
            }
        }
    }

    private static Boolean askToStart(Scanner in){
        String answer = "";
        while(!(answer.equals(START) || answer.equals(EXIT))){
            System.out.println("Please, enter <n> - new game or <e> - exit");
            answer = in.nextLine().toLowerCase();
        }
        if(answer.equals(START)){
            System.out.println("New game !");
            return true;
        }
        System.out.println("Okay, bye !");
        return false;
    }

    private static ArrayList<String> readFile(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            words = new ArrayList<>();
            while ((s = br.readLine()) != null) {
                words.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return words;
    }

    private static String getRandomWord(ArrayList<String> array){
        Random rnd = new Random();
        return array.get(rnd.nextInt(array.toArray().length -1));
    }

    private static String maskWord(String word){
        return MASK_LETTER.repeat(word.length());
    }

    private static void doGame(String word, Scanner in){
        String mask = maskWord(word);
        StringBuilder maskBuilder = new StringBuilder(mask);
        char letter;
        int error = 0;
        Set<Character> usedChars = new HashSet<>();

        while((error < 6) && !(word.equals(maskBuilder.toString()))){
            System.out.println(maskBuilder);
            letter = inputLetter(in);

            if(usedChars.add(letter)){ // true = добавилась, false = уже была
                boolean change = false;
                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == letter){
                        maskBuilder.setCharAt(i,letter);
                        change = true;
                    }

                }
                if(!change){
                    error++;
                    System.out.println("This letter is not in the word");
                }
            }
            else{
                System.out.println("Error ! You already enter this letter !");
            }
            System.out.println("Error = " + error);
            System.out.println(HangmanRenderer.render(error));
            System.out.println("Used letter's: " + usedChars);
        }
        if(word.equals(maskBuilder.toString())){
            System.out.println("You win !");
        }
        else{
            System.out.println("You lose !");
            System.out.println("This word: " + word);
            System.out.println("Used letter's: " + usedChars);
            in.nextLine();
        }
    }

    private static char inputLetter(Scanner scanner){
        char letter = 0;
        String str = "";
        boolean check = false;
        while(!check){
            System.out.println("Enter letter of guess: ");
            str = scanner.next();
            if(str.length() > 1){
                System.out.println("Need ONE letter ! No word !");
                continue;
            }
            letter = str.charAt(0);
            if((letter >= FIRST_LETTER && letter <= LAST_LETTER) || letter == SPECIAL_LETTER){
                check = true;
            }
            else{
                System.out.println("Letter must be lowercase, from 'a' to 'я'.");
            }
        }
        return letter;
    }
}