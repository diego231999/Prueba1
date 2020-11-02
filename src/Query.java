import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Query {
    private String user;
    private String pass;
    private String url;
    Connection connection;


    public void listarEmployees() {

    }

    public void filterEmployees() {

    }

    public HashMap<String, String> setId(String sql) {
        setUser("root");
        setPass("root");
        setUrl("jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima");
        HashMap<String, String> identificadores = new HashMap<String, String>();
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPass());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             Class.forName("com.mysql.cj.jdbc.Driver");
             while(rs.next()){
                 String idEmpleados=rs.getString(1);
                 String boss_name=rs.getString(2);
                 identificadores.put(boss_name,idEmpleados);
                 System.out.println(identificadores);

             }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return identificadores;
    }

    public void insertEmployee() {
        ArrayList<String> lista_empleos = new ArrayList<String>();
        ArrayList<String> lista_jefes = new ArrayList<String>();
        ArrayList<String> lista_departamentos = new ArrayList<String>();
        Scanner entrada=new Scanner(System.in);
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre=entrada.nextLine();
        System.out.print("Ingrese el apellido del empleado: ");
        String apellido=entrada.nextLine();
        System.out.print("Ingrese el email del empleado: ");
        String correo=entrada.nextLine();
        setUser("root");
        setPass("root");
        setUrl("jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima");
        String sql="select job_title from jobs;";
        String sql2="select concat(m.first_name,\" \",m.last_name) 'Jefe' from employees e,employees m where e.manager_id=m.employee_id group by m.employee_id;";
        String sql3="select department_name from departments;";

        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPass());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while(rs.next()){
                String empleos=rs.getString(1);
                lista_empleos.add(empleos);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn2 = DriverManager.getConnection(getUrl(), getUser(), getPass());
             Statement stmt2 = conn2.createStatement();
             ResultSet rs2 = stmt2.executeQuery(sql2)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while(rs2.next()){
                String trabajos=rs2.getString(1);
                lista_jefes.add(trabajos);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn3 = DriverManager.getConnection(getUrl(), getUser(), getPass());
             Statement stmt3 = conn3.createStatement();
             ResultSet rs3 = stmt3.executeQuery(sql3)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while(rs3.next()){
                String departamentos=rs3.getString(1);
                lista_departamentos.add(departamentos);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Ingrese el trabajo del empleado: ");
        int i=1;
        for(String empleo_nombre : lista_empleos){
            System.out.println(i+": "+empleo_nombre);
            i++;
        }
        System.out.print("--> ");
        String empleo=entrada.nextLine();
        System.out.println("Ingrese el jefe del empleado: ");
        i=1;
        for(String jefe : lista_jefes){
            System.out.println(i+": "+jefe);
            i++;
        }
        System.out.print("--> ");
        String jefe_empleado=entrada.nextLine();
        System.out.println("Ingrese el departamento del empleado: ");
        i=1;
        for(String dp : lista_departamentos){
            System.out.println(i+": "+dp);
            i++;
        }
        System.out.print("--> ");
        String departamento=entrada.nextLine();

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
