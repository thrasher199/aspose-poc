package com.mircolink.aspose.custom;

import com.aspose.words.net.System.Data.DataTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataTableExtractor implements ResultSetExtractor<DataTable> {
    @Override
    public DataTable extractData(ResultSet rs) throws SQLException, DataAccessException {
        return new DataTable(rs);
    }
}
