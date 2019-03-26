import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RegExpUtilTest {
    private List<String> listOfPhoneNumbers;
    private List<String> listOfEmails;
    private List<String> listOfDates;
    private List<String> listOfIps;

    /**
     * First three elements in every collection are valid, the other two are not.
     * */
    @Before
    public void init() {
        listOfPhoneNumbers = new ArrayList<String>() {{
            add("+380501234567");
            add("+38(050)1234567");
            add("+38(050)123-45-67");
            add("+38050)l12-345-67");
            add("+360501h234567");
        }};
        listOfEmails = new ArrayList<String>() {{
            add("tauren4j@gmail.com");
            add("tauren@gmail.com");
            add("4tauren@gmail.com");
            add("tauren-@@.com");
            add("tauren-@.com.com");
        }};
        listOfDates = new ArrayList<String>() {{
            add("26.03.2019");
            add("29.02.2020");
            add("05/09/1997");
            add("29/02/2019");
            add("32/01/2019");
        }};
        listOfIps = new ArrayList<String>() {{
            add("192.168.0.1");
            add("127.0.0.1");
            add("4.4.4.4");
            add("256.102.16.17");
            add("45..12.1");
        }};
    }

    @Test
    public void shouldReturnBooleanIfValidPhoneNumbersOrNot() {
        for (int i = 0; i < 3; i++) {
            assertTrue(RegExpUtil.isPhoneNumberValid(listOfPhoneNumbers.get(i)));
        }
        for (int i = 3; i < 5; i++) {
            assertFalse(RegExpUtil.isPhoneNumberValid(listOfPhoneNumbers.get(i)));
        }
    }

    @Test
    public void shouldReturnBooleanIfValidEmailOrNot() {
        for (int i = 0; i < 3; i++) {
            assertTrue(RegExpUtil.isEmailValid(listOfEmails.get(i)));
        }
        for (int i = 3; i < 5; i++) {
            assertFalse(RegExpUtil.isEmailValid(listOfEmails.get(i)));
        }
    }

    @Test
    public void shouldReturnBooleanIfValidDateOrNot() {
        for (int i = 0; i < 3; i++) {
            assertTrue(RegExpUtil.isDateValid(listOfDates.get(i)));
        }
        for (int i = 3; i < 5; i++) {
            assertFalse(RegExpUtil.isDateValid(listOfDates.get(i)));
        }
    }

    @Test
    public void shouldReturnBooleanIfValidIpOrNot() {
        for (int i = 0; i < 3; i++) {
            assertTrue(RegExpUtil.isIpValid(listOfIps.get(i)));
        }
        for (int i = 3; i < 5; i++) {
            assertFalse(RegExpUtil.isIpValid(listOfIps.get(i)));
        }
    }
}
