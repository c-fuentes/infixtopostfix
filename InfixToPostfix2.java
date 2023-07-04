/**
  @author Celso Fuentes
  @version 1.0 
  Dr.Ken Nguyen
  Data Structures and Algorithms
  HW4- Stack InfixToPostFix Processing
  
  Requirements: (a) Implement a Java program that take in an infix expression 
  from the user and output the expression in postfix notation. 
  (b) [15 points bonus] If all the operands in the input are numeric, 
  then your program should evaluate the expression and display its value.
  
  @purpose - to become familiar with stack operations 
*/
import java.util.Stack;

public class InfixToPostfix2{
  //attributes
  private String postfix = "";
  
  /**
    @author Celso Fuentes
    @version 1.0
    Dr. Ken Nguyen
    Data Structures and Algorithms
    
    Checks if the first given operator has a higher precedence than the second given operator
    @param a - first given operator
    @param b - second given operator
    
    Algorithm:
      1. Conditonal to check for lower precedence
      2. When conditional is true return false
      3. Otherwise, return true
  */
  public boolean precedence(String a, String b){
    if((a.equals("*") && b.equals("(")) || (a.equals("/") && b.equals("(")) || (a.equals("+") && b.equals("(")) || (a.equals("-") && b.equals("(")) || 
    (a.equals("+") && b.equals("*")) || (a.equals("-") && b.equals("*")) || (a.equals("-") && b.equals("/")) || (a.equals("+") && b.equals("/"))){
      return false;
    }
    return true;
  }
  
  /**
    @author Celso Fuentes
    @version 1.0
    Dr. Ken Nguyen
    Data Structures and Algorithms
    
    Translates infix to postfix
    @param - infix string
    @return - Postfix string
    
    Algorithm:
      1. Create a stack to hold operators
      2. Create for loop to iterate through the infix string
      3. Conditional to check if its not a space or operator
      4. If true, add char to postfix string if it is not a )
      5. In the initial conditional, create a while loop to "empty" the stack if it has values and the current char is ) or at the end of the infix string
      6. When the intial conditional is false, create a conditional to check if the stack is not empty and if the last operator has precedence over the current operator
      7. If true and the top of the stack is not a ( pop the top element and add it to the postfix string
      8. If the top of the stack is a ( just pop the element
      9. If false, push the current char to the stack
      10. Return postfix string
  */
  public String convertToPostfix (String infix) {
    //Translates A*B+C to AB*C+, uses a stack to do so
    Stack<String> operatorStack = new Stack();
    //currently counts spaces as a part of length, might wanna make a method that counts length without spaces
    String[] result = infix.split("\\s");
    for (int i = 0; i < result.length; i++) {
      if (!result[i].equals(" ") && !result[i].equals("(") && !result[i].equals("*") && !result[i].equals("/") && !result[i].equals("+") && !result[i].equals("-")){//runs if its not an operator
        if (!result[i].equals(")")){
          postfix += result[i];
        }
        while(!operatorStack.isEmpty() && ( (result[i].equals(")")) || (i == result.length - 1))){
          postfix += operatorStack.pop();
        }
      }else{//runs if its an operator
        if(!operatorStack.isEmpty() && (precedence(operatorStack.peek(), result[i]))){
          if(!operatorStack.peek().equals("(")){
            postfix += operatorStack.pop();
          }else{
            operatorStack.pop();
          }
        }
        operatorStack.push(result[i]);
      }
    }
    return postfix;
  }
  
  /**
    @author Celso Fuentes
    @version 1.0
    Dr. Ken Nguyen
    Data Structures and Algorithms
    
    Evaluates the postfix expression and returns its values
    @param - postfix String 
    @return - answer as a double 
    
    Algorithm:
      1. Create while loop that continues if ____ is < the length of the string
      1. Check if parenthesis, if so ???
      2. Check if multiplication, if so multiply
      3. Check if division, if so divide
      4. Check if addition, if so add
      5. Check if subtraction, if so subtract
  */
  public double calculate(String postfix){
    //Uses the postfix to evaluate the expression with multiple conditionals to perform pemdas
    return 0;//just for compiling purposes
  }
  
  /*
    Testing to see if it functions correctly
  */
  public static void main(String[] args){
    InfixToPostfix a = new InfixToPostfix();
    
    System.out.println(a.convertToPostfix("A * ( B + C )"));
  }
}