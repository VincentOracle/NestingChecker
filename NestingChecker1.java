/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here.>
 *
 * Note external code may reduce your score but appropriate citation is required
 * to avoid academic integrity violations. Please see the Course Syllabus as
 * well as the university code of academic integrity:
 *    https://catalog.upenn.edu/pennbook/code-of-academic-integrity/
 *
 * Signed,yes
 * Author: WERE VINCENT
 * Penn email: <oumawere2001@seas.upenn.edu>
 * Date:2023-3-8
 */

import java.util.Queue;
import java.util.Stack;

public class NestingChecker {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 123674589918L;

    /**
     * TODO Implement this method!
     * Takes a nullable Queue of nullable elements and calculates its nesting status
     *
     * @param elements Nullable Queue of nullable Nestable elements
     * @return Non-null NestingReport describing the exact nesting status of the queue
     */


    /*
    This implementation first checks if the input queue is null and if it is, 
    it returns a NestingReport with a status of NULL_INPUT. It then iterates
     through the elements in the queue and checks if the element is null,
      if it is it returns a NestingReport with a status of NULL_ITEM. 
      If the element is an open element, it pushes it onto the stack, 
      otherwise it checks if the stack is empty, if it is it returns a 
      NestingReport with a status of INVALID_CLOSE. If the stack is not empty, 
      it pops the top element from the stack and checks if it matches the current close element, 
      if it does not, it returns a NestingReport with a status of INVALID_CLOSE.
      After iterating through all the elements in the queue, it checks if the stack is empty.
      If it is, it returns a NestingReport with a status of VALID,
     otherwise it returns a NestingReport with a status of NOT_TERMINATED.
     */ 

public static NestingReport checkNesting(Queue<? extends Nestable> elements) {
Stack<Nestable> stack = new Stack<>();
if(elements == null) {
    return new NestingReport(NestingReport.Status.NULL_INPUT, null, stack);
}
for(Nestable element : elements) {
if(element == null) {
return new NestingReport(NestingReport.Status.NULL_ITEM, element, stack);
}

/*The isOpen() method is a method that appears in the Nestable interface in the provided code.
 It is not a built-in method in Java,
 but rather it is a custom method that is defined as part of the Nestable interface.
It is used to check whether a Nestable object is an opening element or not.
 It returns a boolean value, true if the object is an opening element and false if it is not. */

 
//if(element.isOpen()) {
//stack.push(element);
//}else{

if stack.isFull(){
       stack.push(element); 
    }
} 
else {
   if(stack.empty() || !stack.peek().matches(element)) {
    return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, stack);
}
    stack.pop();
}

}
}
if(stack.empty()) {
    return new NestingReport(NestingReport.Status.VALID, null, stack);
    } else {
    return new NestingReport(NestingReport.Status.NOT_TERMINATED, null, stack);
}
//}


// Add the Nestable interface and NestingReport class in the same file.
interface Nestable {
boolean isOpen();
boolean matches(Nestable other);
}

class NestingReport {
public enum Status {
VALID, NULL_INPUT, NULL_ITEM, INVALID_CLOSE, NOT_TERMINATED
}
}
