package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TableService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getTableColumns(String tableName) {
        String columInfos="";
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                String columInfo="Column: " + columnName + ", Type: " + columnType + ", Size: " + columnSize;
                columInfos+=(columInfo+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(columInfos);
        return columInfos;
    }



}