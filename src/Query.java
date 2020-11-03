import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalDate.now;

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
        String sql="select job_title,job_id from jobs;";
        String sql2="select concat(m.first_name,\" \",m.last_name)'Jefe', m.employee_id from employees e,employees m where e.manager_id=m.employee_id group by m.employee_id;";
        String sql3="select department_name, department_id from departments;";

        HashMap<String, String> hash_departamentos = new HashMap<String, String>();
        HashMap<String, String> hash_jobs = new HashMap<String, String>();
        HashMap<String, String> hash_jefes = new HashMap<String, String>();

        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPass());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while(rs.next()){
                String empleos=rs.getString(1);
                String id_empleos=rs.getString(2);
                lista_empleos.add(empleos);
                hash_jobs.put(empleos,id_empleos);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn2 = DriverManager.getConnection(getUrl(), getUser(), getPass());
             Statement stmt2 = conn2.createStatement();
             ResultSet rs2 = stmt2.executeQuery(sql2)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while(rs2.next()){
                String id_jefes=rs2.getString(2);
                String jefes=rs2.getString(1);
                lista_jefes.add(jefes);
                hash_jefes.put(jefes,id_jefes);
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
                String id_de_departamentos=rs3.getString(2);
                lista_departamentos.add(departamentos);
                hash_departamentos.put(departamentos,id_de_departamentos);
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
        String [] parts= jefe_empleado.split(" ");
        String nombre_jefe_empleado= parts[0];
        String apellido_jefe_empleado= parts[1];
        System.out.println("Ingrese el departamento del empleado: ");
        i=1;
        for(String dp : lista_departamentos){
            System.out.println(i+": "+dp);
            i++;
        }
        System.out.print("--> ");
        String departamento=entrada.nextLine();
        System.out.print("Ingrese el sueldo: ");
        String sueldito= entrada.nextLine();
        String date="1987-06-17 00:00:00";

        String id_dpto=hash_departamentos.get(departamento);
        String id_jefe=hash_jefes.get(jefe_empleado);
        String id_del_trabajo=hash_jobs.get(empleo);
        String xd="123";
        String sql4="insert into employees(first_name, last_name, email, salary, job_id, department_id, manager_id,hire_date)"+"values("+nombre+","+apellido+","+correo+","+sueldito+","+"select job_id from jobs where job_title="+empleo+","+"select department_id from departments where department_name="+departamento+","+"select e.employee_id from employees e where e.first_name="+nombre_jefe_empleado+" and e.last_name="+apellido_jefe_empleado+","+date+");";
        String sql5="insert into employees(first_name, last_name, email, salary, job_id, department_id, manager_id,hire_date)"+"values('"+nombre+"','"+apellido+"','"+correo+"','"+sueldito+"','"+id_del_trabajo+"','"+id_dpto+"','"+id_jefe+"','"+now()+"')";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn4 = DriverManager.getConnection(getUrl(),getUser(),getPass());
            Statement stmt4 = conn4.createStatement();
            stmt4.executeUpdate(sql5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
