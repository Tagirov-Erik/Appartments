package com.gmail.eriktagirov;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<K, T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public List<T> getAll(Class<T> cls) {
        List<T> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        T client = cls.newInstance();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);

                            Field field = cls.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(client, rs.getObject(columnName));
                        }
                        res.add(client);
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void selectByParameters(Filter filter) throws IllegalAccessException, SQLException {

        StringBuilder sql_param = new StringBuilder();
        Field[] fields = filter.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(filter) == null)
                continue;
            sql_param.append(f.getName()).append(" >= \"").append(f.get(filter)).append("\"").append(" AND ");
        }

        sql_param.setLength(sql_param.length() - 4);


        String sql = "SELECT * FROM " + table + " WHERE " + sql_param.toString();
        System.out.println(sql);

        PreparedStatement ps = conn.prepareStatement(sql);

        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.println(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }

    }
}
