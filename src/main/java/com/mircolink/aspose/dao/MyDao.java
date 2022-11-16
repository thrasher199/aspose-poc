package com.mircolink.aspose.dao;

import com.aspose.words.net.System.Data.DataTable;
import com.mircolink.aspose.custom.DataTableExtractor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MyDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MyDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public DataTable getCustomerData(int i){
        Map<String, Long> paramMap =  new HashMap<>();
        paramMap.put("id", (long) i);
        return namedParameterJdbcTemplate.query("select * from customer where id = :id",paramMap, new DataTableExtractor());
    }

    public DataTable getCustomerDetail(int i){
        Map<String, Long> paramMap = new HashMap<>();
        paramMap.put("customer_id", (long) i);
        String sql = "select * from customer_loan where customer_id = :customer_id";
        return namedParameterJdbcTemplate.query(sql, paramMap, new DataTableExtractor());
    }
}
