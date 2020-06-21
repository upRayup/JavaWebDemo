package text.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    //数据库连接池对象
    private static DataSource ds;
    //Druid数据库连接池配置
    static{
        try {
            Properties pro=new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds=DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //返回数据库连接池对象
    public static DataSource getDatasource(){
        return ds;
    }
    //返回数据库连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
