/*
 *

 * Author: WERE VINCENT
 * Penn email: <oumawere2001@seas.upenn.edu>
 * https://catalog.upenn.edu/pennbook/code-of-academic-integrity/
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

    if (elements == null) {
        return new NestingReport(NestingReport.Status.NULL_INPUT, null, stack);
    }

     for (Nestable element : elements) {
        if (element == null) {
            return new NestingReport(NestingReport.Status.NULL_ITEM, element, stack);
        }

        if (element.isOpen()) {
            stack.push(element);
        } else {
            if (stack.empty()) {
                return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, stack);
            }

            Nestable top = stack.pop();
            if (!element.matches(top)) {
                return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, stack);
            }
        }
    }

    if (stack.empty()) {
        return new NestingReport(NestingReport.Status.VALID, null, stack);
    } else {
        return new NestingReport(NestingReport.Status.NOT_TERMINATED, null, stack);
    }

        // return new NestingReport(NestingReport.Status.VALID, null, stack);
    }
}
