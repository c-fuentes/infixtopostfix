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
  //DOES NOT HANDLE Exponents
  public boolean precedence(String a, String b){
    if(
      (a.equals("*") && b.equals("(")) //Multiplication has lower precedence than Parenthesis
      || (a.equals("/") && b.equals("(")) //Division has lower precedence than Parenthesis
      || (a.equals("+") && b.equals("(")) //Addition has lower precedence than Parenthesis
      || (a.equals("-") && b.equals("(")) //Subtraction has lower precedence than Parenthesis
      || (a.equals("+") && b.equals("*")) //Addition has lower precedence than Multiplication
      || (a.equals("-") && b.equals("*")) //Subtraction has lower precedence than Multiplication
      || (a.equals("-") && b.equals("/")) //Subtraction has lower precedence than Division
      || (a.equals("+") && b.equals("/")) //Addition has lower precedence than Division
      ){
      return false;
    }
    return true; //any other possiblity String a will have higher precedence
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
    //loop that runs through the string, looking a each individual char
    for (int i = 0; i < result.length; i++) {
      //verifies that current char is not an operator (excluding ")" as it has a special function) or space
      if (!result[i].equals(" ") && !result[i].equals("(") && !result[i].equals("*") && !result[i].equals("/") && !result[i].equals("+") && !result[i].equals("-")){
        //will add any operands to the result aka postfix string
        if (!result[i].equals(")")){
          postfix += result[i];
        }
        //will empty the operator stack when a ")" is reached or we get to the end of the string, while loop will stop when the stack is empty
        while(!operatorStack.isEmpty() && ( (result[i].equals(")")) || (i == result.length - 1))){
          postfix += operatorStack.pop();
        }
      }else{//runs if its an operator
        if(!operatorStack.isEmpty() && (precedence(operatorStack.peek(), result[i]))){//runs when stack has values and precedence of previous operator is higher than current operator
          if(!operatorStack.peek().equals("(")){//adds operators when the previous operator is not "(" to postfix string
            postfix += operatorStack.pop();
          }else{
            operatorStack.pop();//pops off "(" when it has higher precedence than the incoming operator
          }
        }
        operatorStack.push(result[i]);//adds operators when stack is empty or when there is a lower precedence
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
  //WORK IN PROGRESS
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
    System.out.println(a.convertToPostfix("A + B / C"));
    System.out.println(a.convertToPostfix("A - B + C"));
    System.out.println(a.convertToPostfix("A + ( B * C )"));
  }
}