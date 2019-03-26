import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
    private static final String PHONE_NUMBER_REGULAR_EXPRESSION = "^(\\s*)?(\\+)?([- _():=+]?" +
            "\\d[- _():=+]?){10,14}(\\s*)?$";
    private static final String EMAIL_REGULAR_EXPRESSION = "^\\w+[a-zA-Z0-9\\.\\_\\-]*@([a-zA-Z]+\\.)+[a-zA-Z]+$";
    private static final String DATE_REGULAR_EXPRESSION = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)";
    private static final String IP_VERSION_FOUR_REGULAR_EXPRESSION = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)" +
            "\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return checkForValid(phoneNumber, PHONE_NUMBER_REGULAR_EXPRESSION).matches();
    }

    public static boolean isEmailValid(String email) {
        return checkForValid(email, EMAIL_REGULAR_EXPRESSION).matches();
    }

    /**
     * Method checks if date is valid
     * The leap year means that:
     * if (year is not divisible by 4) then (it is a common year)
     * else if (year is not divisible by 100) then (it is a leap year)
     * else if (year is not divisible by 400) then (it is a common year)
     * else (it is a leap year)
     */
    public static boolean isDateValid(String date) {
        Matcher matcher = checkForValid(date, DATE_REGULAR_EXPRESSION);

        if (matcher.matches()) {
            matcher.reset();
            if (matcher.find()) {
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));
                if ("31".equals(day)) {
                    return Arrays.asList("1", "01", "3", "03", "5", "05", "7", "07", "8", "08", "10", "12")
                            .contains(month);
                    //check for February
                } else if ("2".equals(month) || "02".equals(month)) {
                    //check for leap year
                    if (year % 4 == 0) {
                        if (year % 100 == 0) {
                            if (year % 400 == 0) {
                                return Integer.parseInt(day) <= 29;
                            }
                            return Integer.parseInt(day) <= 28;
                        }
                        return Integer.parseInt(day) <= 29;
                    } else {
                        return Integer.parseInt(day) <= 28;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isIpValid(String ip) {
        return checkForValid(ip, IP_VERSION_FOUR_REGULAR_EXPRESSION).matches();
    }

    private static Matcher checkForValid(String item, String expression) {
        Pattern pattern = Pattern.compile(expression);
        return pattern.matcher(item);
    }
}
