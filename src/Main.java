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

    public static void drawArray(String[][] array){
        for(String[] row : array) {
            for(String element : row){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void draw(String[][] array, int error){
        switch (error){
            case 0:
                drawArray(array);
                break;
            case 1:
                array[2][4] = "O";
                drawArray(array);
                break;
            case 2:
                array[2][4] = "O";
                array[3][3] = "|";
                drawArray(array);
                break;
            case 3:
                array[2][4] = "O";
                array[3][3] = "|";
                array[3][2] = "/";
                drawArray(array);
                break;
            case 4:
                array[2][4] = "O";
                array[3][3] = "|";
                array[3][2] = "/";
                array[3][4] = "|";
                drawArray(array);
                break;
            case 5:
                array[2][4] = "O";
                array[3][3] = "|";
                array[3][2] = "/";
                array[3][4] = "|";
                array[4][3] = "|";
                drawArray(array);
                break;
            case 6:
                array[2][4] = "O";
                array[3][3] = "|";
                array[3][2] = "/";
                array[3][4] = "|";
                array[4][3] = "|";
                array[4][4] = "|";
                drawArray(array);
                break;
        }
    }

    public static void game(String word, String mask, Scanner in, String[][] array){
        char letter;
        char[] arrWord = word.toCharArray();
        char[] arrMask = mask.toCharArray();
        int error = 0;

        while((error < 6) && !(word.equals(mask))){
            System.out.println(mask);
            System.out.println("Enter letter of gue)ss: ");
            letter = in.next().charAt(0);
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

    public static String[][] resetArray(String[][] arrayStart){
        String[][] result = new String[arrayStart.length][];
        for(int i = 0; i < arrayStart.length; i++){
            result[i] = new String[arrayStart[i].length];
            System.arraycopy(arrayStart[i], 0, result[i], 0, arrayStart.length - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "words.txt";
        ArrayList<String> array = fileToArray(fileName);
        boolean game = true;
        String[][] drawArrayEnd = {
                {"|", "-", "-", "-", "", ""},
                {"|", "", "", "", "|", ""},
                {"|", "", "", "", "O", ""},
                {"|", "", "/", "|", "|", ""},
                {"|", "", "", "|", "|", ""},
                {"|", "", "", "", "", ""},
                {"_", "_", "_", "_", "_", ""}
        };

        String[][] drawArrayStart = {
                {"|", "-", "-", "-", "", ""},
                {"|", "", "", "", "|", ""},
                {"|", "", "", "", "", ""},
                {"|", "", "", "", "", ""},
                {"|", "", "", "", "", ""},
                {"|", "", "", "", "", ""},
                {"_", "_", "_", "_", "_", ""}
        };

        while(game){
            //System.out.println(askToStart(scanner)); // true or false in cmd
            game = askToStart(scanner);
            if(game){
                String word = rndWord(array);
                System.out.println(word); // random word
                //System.out.println(maskWord(word));

                //draw(drawArrayStart, 6);

                String[][] arrayDraw = resetArray(drawArrayStart);
                game(word, maskWord(word), scanner, arrayDraw);
            }
        }
    }
}