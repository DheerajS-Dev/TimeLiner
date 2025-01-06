package code.dheeraj.TimeLiner.Service;

import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    //    @Disabled
    @Test
    public void testUserEntries() {
        User user = userService.findByUserName("raj");
        Assertions.assertFalse(user.getJournalEntries().isEmpty());
    }

    //    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "raj",
            "nikhil",
            "Shyam"
    })
    public void testFindByUserName(String name) {
        Assertions.assertNotNull(userService.findByUserName(name));
    }

    @Disabled
    @ParameterizedTest
    @EnumSource
    public void testFindByUserName() {
        Assertions.assertNotNull(userService.findByUserName("raj"));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testCreateUser(User user) {
        Assertions.assertTrue(userService.createUser(user));
    }

    //    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,5,7",
            "3,7,10"
    })
    public void test(int a, int b, int expected) {
        Assertions.assertEquals(expected, a + b);
    }
}
