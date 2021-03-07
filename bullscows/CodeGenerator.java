package bullscows;

import java.nio.file.WatchKey;
import java.util.Random;


public class CodeGenerator {
    private final int numberOfDigits;
    static Random r = new Random();
    private String secretCode;
    private CharactersRange charactersRange;

    public CodeGenerator(int numberOfDigits,int range){
        this.numberOfDigits = numberOfDigits;
        charactersRange = new CharactersRange(range);
        secretCode = this.SecretCode();
    }
    public String SecretCode() {
        StringBuilder secretNumber = new StringBuilder();
        char[] characters = charactersRange.getCharacters();
        int i=0;
        for(char a : characters) {
            if(i++!=numberOfDigits) secretNumber.append(a);
            else break;
            //i++;
        }
        secretCode = secretNumber.toString();
        StringBuilder Stars = new StringBuilder();
        for(int j =0;j<numberOfDigits;j++) Stars.append('*');
        System.out.println("The secret is prepared: "+Stars.toString()+" ("+charactersRange.getPrintedRange()+")");
        return secretCode;
    }

    public char charAt(int i){
        return secretCode.charAt(i);
    }

    public String getSecretCode() {
        return secretCode;
    }
}
