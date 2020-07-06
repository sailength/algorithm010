import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LetterCombinations {

    private static List<String> result = new ArrayList<String>();
    static  Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public static List<String> letterCombinations(String digits) {
        rec(0, digits, "");
        return result;

    }

    public static void rec(int level, String digits, String now) {

        if (level == digits.length()) {
            result.add(now);
            return;
        }

        String input = phone.get(digits.charAt(level));

        if (input != null) {
            for (int i = 0; i < input.length(); i++) {
                rec(level + 1, digits, now + input.charAt(i));
            }
        } else {
            rec(level + 1, digits, now);
        }

    }

    public static void main(String[] args) {
        letterCombinations("23");
        System.out.println(result);
    }

}