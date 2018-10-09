package hackerrank.algorithm.huffman;

class Decoding {
    void decode(String s, Node root) {
        StringBuilder sb = new StringBuilder();
        Node curr = root;
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
            if (curr.left == null && curr.right == null) {
                sb.append(curr.data);
                curr = root;
            }
        }
        System.out.println(sb.toString());
    }
}