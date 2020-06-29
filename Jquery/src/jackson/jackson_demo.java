package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class jackson_demo {
    //Java对象转为Json
    @Test
    public void test1() throws IOException {
        //创建Java对象
        Person person=new Person();
        person.setName("Ray");
        person.setAge(22);
        person.setGender("男");
        //创建ObjectMapper对象
        ObjectMapper mapper=new ObjectMapper();
//        String jackson_person = mapper.writeValueAsString(person);
//        System.out.println(jackson_person);
        mapper.writeValue(new File("D://a.txt"),person);
    }

    @Test
    public void test2() throws IOException {
        //创建Java对象
        Person person=new Person();
        person.setName("Ray");
        person.setAge(22);
        person.setGender("男");
        person.setBirthday(new Date());
        //创建ObjectMapper对象
        ObjectMapper mapper=new ObjectMapper();
        String jackson_person = mapper.writeValueAsString(person);
        System.out.println(jackson_person);
    }
}
