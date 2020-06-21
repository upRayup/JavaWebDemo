package text;

import org.junit.Test;
import text.dao.UserDao;
import text.userclass.User;

public class main {
    @Test
    public void userlogin(){
        User user=new User();
        user.setUsername("Ray");
        user.setPassword("123");
        User loginuser = new UserDao().login(user);
        System.out.println(loginuser);
    }
}
