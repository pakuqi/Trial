package jp.personal.pakuqi.chatworktrial;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by oono on 2015/06/08.
 */
@RunWith(AndroidJUnit4.class)
public class MyTest1_1 extends ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity mloginActivity;

    public MyTest1_1(){
        super(LoginActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mloginActivity = getActivity();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void checkText() {
        onView(withId(R.id.message_text)).check(matches(withText("API TOKEN")));
    }

}
