package practicum.interview.old;

import java.util.Stack;

public class ImplementQueueUsingStacks {

    private final Stack<Integer> inStack;

    private final Stack<Integer> outStack;

    public ImplementQueueUsingStacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (empty()) {
            return 0;
        }
        if (outStack.empty()) {
            shiftElements();
        }
        return outStack.pop();
    }

    public int peek() {
        if (empty()) {
            return 0;
        }
        if (outStack.empty()) {
            shiftElements();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

    private void shiftElements() {
        while(!inStack.empty()) {
            outStack.push(inStack.pop());
        }
    }
}
