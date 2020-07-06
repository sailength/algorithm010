import java.util.Stack;

class LargestRectangleArea {

    class Node {

        int index;

        int val;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }


    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, -1));
        int maxArea = 0;
        int i = 0;
        int length = heights.length;
        while (i < length) {
            while (stack.peek().getVal() >= heights[i]) {
                Node top = stack.pop();
                Node preNode = stack.peek();
                int area = top.getVal() * (i - preNode.getIndex() );
                if (maxArea < area) {
                    maxArea = area;
                }
            }
            stack.push(new Node(i+1, heights[i]));
            i++;
        }
        while (stack.peek().getVal() != -1) {
            Node node = stack.pop();
            int area = node.getVal() * (node.getIndex() - stack.peek().getIndex());
            if (maxArea < area) {
                maxArea = area;
            }

        }

        return maxArea;
    }
}