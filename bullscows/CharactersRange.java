package bullscows;

import java.util.*;

public class CharactersRange {
    private char[] characters;
    private final char[] numbers = "1234567890".toCharArray();
    private final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    String printedRange;
    //int range;


    public CharactersRange(int range){
        //this.range = range;
        characters = new char[range];
        if(range > 10) {
            //this.range = range;
            for(int i=0,j=0;i<characters.length;i++){
                if(i<numbers.length) characters[i] = numbers[i];
                else {
                    characters[i] = alphabet[j++];
                }
            }
            printedRange = "0-9, a-" + characters[characters.length-1];
        }
        else {
            for(int i=0;i<characters.length;i++) characters[i] = numbers[i];
            printedRange = "0-9";
        }
        shuffleCharacters();
    }

    protected void shuffleCharacters(){
        ArrayList<Character> list = new ArrayList<>();
        for(char a : characters) list.add(a);
        Collections.shuffle(list);
        int i =0;
        for(char a : list) {
            characters[i++] = a;
        }
    }

    public char[] getCharacters() {
        return characters;
    }

    public String getPrintedRange() {
        return printedRange;
    }
}
