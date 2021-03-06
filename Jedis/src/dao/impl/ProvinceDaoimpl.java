package dao.impl;

import dao.ProvinceDao;
import domain.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class ProvinceDaoimpl implements ProvinceDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        String sql="select * from province";
        List<Province> province = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return province;
    }
}
