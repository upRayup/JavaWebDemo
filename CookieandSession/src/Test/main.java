package Test;

import org.junit.Test;
import Test.dao.UserDao;
import Test.userclass.User;

public class main {
    @Test
    public void userlogin(){
        User user=new User();
        user.setUsername("Ray");
        user.setPassword("123");
        user.setCheckCode("123");
        User loginuser = new UserDao().login(user);
        System.out.println(loginuser);
    }
}
