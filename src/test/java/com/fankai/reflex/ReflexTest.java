package com.fankai.reflex;

import lombok.Data;
import org.junit.jupiter.api.Test;

public class ReflexTest {
    @Test
    public void test() throws Exception{
        Class<?> userClass = Class.forName("com.fankai.reflex.User");
        User user = new User();
        Class<? extends User> userClass1 = user.getClass();
        Class<User> userClass2 = User.class;
        System.out.println(userClass);
        System.out.println(userClass1);
        System.out.println(userClass2);
        Class<?> aClass = super.getClass();
        System.out.println(aClass);
    }

}

@Data
class User{
    private String name;
    private Integer age;
}
