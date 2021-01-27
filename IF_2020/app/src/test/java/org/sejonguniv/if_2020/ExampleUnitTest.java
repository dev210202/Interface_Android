package org.sejonguniv.if_2020;

import org.junit.Test;
import org.sejonguniv.if_2020.model.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        User user = new User("", "", "", "");
        assert 4 == user.itemSize();
    }
}