import java.util.Stack;
public class Example {
    public static void main(String[] args){
        //Stack stack1 = new Stack<>();
        Stack<String>stack2 = new Stack<>();
       // stack1.push(1);
       // stack1.push(2);
       // stack1.push(3);
        stack2.push("4");
        stack2.push("5");
        stack2.push("6");
       // System.out.println(stack1);
        System.out.println(stack2);
        //Pop operation
        System.out.println("Stack after pop operation: "+stack2.pop());
        System.out.println(stack2);
        //peek operation
        System.out.println("The element at the top of the "+"stack is "+stack2.peek());
        //Empty
        System.out.println("Is stack empty ? "+stack2.empty());
        
    }
}
