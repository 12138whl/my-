package util;

import jdbc.Emp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class jdbctest {
    public static void main(String[] args) {
        List<Emp> list=new jdbctest().findAll2();
        System.out.println(list);
        System.out.println(list.size());
    }
    /**
     * 演示JDBC的工具类
     *
     * @return
     */
    public List<Emp> findAll2() {
        ResultSet rs = null;
        Connection conn = null;
        Statement stm = null;
        List<Emp> list = null;

        try {
            /*//1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "wmq12138");*/
            conn = JDBCUtils.getConn();
            //3.定义SQL一句
            String sql = "select * from emp;";
            //4.获取执行SQL的对象Statement
            stm = conn.createStatement();
            //5.执行SQL语句
            rs = stm.executeQuery(sql);
            //6.遍历结果集
            Emp emp = null;
            list = new ArrayList<Emp>();
            while (rs.next()) {
                //获取数据
                int id = rs.getInt("Id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                //创建emp对象,并且赋值
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }/* finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }*/
        JDBCUtils.close(rs,stm,conn);
        return list;
    }
}
