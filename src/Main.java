import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select e.employee_id 'Id del empleado', concat(e.first_name, \" \", e.last_name), concat(m.first_name, \" \", m.last_name) 'Jefe empleado',j.job_title, d.department_name, l.city, c.country_name, r.region_name from employees e,employees m, jobs j, departments d, locations l, countries c, regions r where e.job_id=j.job_id and e.department_id=d.department_id and d.location_id=l.location_id and l.country_id=c.country_id and c.region_id=r.region_id and e.manager_id=m.employee_id;")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                while (rs.next()) {
                    int idEmpleado = rs.getInt(1);
                    String empleado = rs.getString(2);
                    String jefe_empleado= rs.getString(3);
                    String nombre_trabajo= rs.getString(4);
                    String nombre_departamento=rs.getString(5);
                    String ciudad=rs.getString(6);
                    String pais=rs.getString(7);
                    String region=rs.getString(8);
                    System.out.println("ID EMPLEADO: "+idEmpleado+ " EMPLEADO: "+empleado+" JEFE: "+jefe_empleado+" TRABAJO:"+nombre_trabajo+" DEPARTAMENTO: "+nombre_departamento+" CIUDAD: "+ciudad+ " PAIS: "+pais+" REGION: "+region);
                }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}