package dataStructures.stack;

import java.util.Locale;
import java.util.Stack;

public class PalindromeExersize {
    /*
    This class provides an exercise in which you are required to use the MyStackImp class you created.
    Your task is to implement the isPalindrome() method, which is capable of determining if a provided string
    is a Palindrome using a Stack. Returns True if the word/ phrase is a palindrome and False otherwise.

    You should take into account that not all letters are lowercase, and that the phrase or sentence may contain
    punctuation.

    A palindrome is a word or phrase that is read the same forwards and backwards:
    Take for example the word `racecar`. This is identical both forwards and backwards.
     */

    public static boolean isPalindrome(String potentialPalindrome)
    {
        Stack<Character> myStack = new Stack<>();
        int pointer = 1;

        //cleans input string of spaces, upper cases and punctuation
        String lowerString = potentialPalindrome.toLowerCase();
        String cleanString = "";

        for(int j=0; j<=lowerString.length()-1; j++){
            if(lowerString.charAt(j) < 'a' || lowerString.charAt(j) > 'z'){
                continue;
            }
            else{
                cleanString += lowerString.charAt(j);
            }
        }

        int stringLength = cleanString.length();

        //if string length is pair
        if(stringLength%2 == 0){

            // we push the last half of the char of the string on to the stack
            // string ABBA
            // push A B on stack
            for(int i=stringLength-1; i>=(stringLength/2); i--){
                myStack.push(cleanString.charAt(i));
            }

            // compare each element of stack to first half of string starting mid string
            // B (stack) -> B (string)
            // A (stack) -> (string)
            while(!myStack.isEmpty()){
                char element = myStack.pop();
                if(element == cleanString.charAt((stringLength/2)-pointer)){
                    pointer +=1;
                    continue;
                }
                else {
                    return false;
                }
            }
            return true;
        }

        //if string length is not pair
        else{

            // we push half the elements onto stack (but not middle el)
            // racecar
            // push r a c onto stack
            for(int i=stringLength-1; i>(stringLength/2); i--){
                myStack.push(cleanString.charAt(i));
            }

            // compare each element of stack to first half of string starting after middle el
            // ignore 'e'
            // c (stack) --> c (string)
            // a (stack) --> a (string)
            // r (stack) --> r (string)
            while(!myStack.isEmpty()){
                char element = myStack.pop();
                if(element == cleanString.charAt((stringLength/2)-pointer)){
                    pointer +=1;
                    continue;
                }
                else{
                    return false;
                }
             }
            return true;
        }
    }

}
