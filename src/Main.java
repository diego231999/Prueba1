import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        HashMap<String, String> identificadores = new HashMap<String, String>();
        Query query=new Query();
        b1:
        while(true){
            System.out.println("-----------Bienvenido al sistema---------");
            System.out.println("-----------------------------------------");
            System.out.println("Ingrese la accion que desse realizar");
            System.out.println("1: Ingresar datos de un nuevo empleado");
            System.out.println("2: Mostrar lista completa de empleados existentes");
            System.out.println("3: Filtrar un empleado en particular");
            System.out.println("4: Salir");
            System.out.print("----Opcion -> ");
            String opcion= entrada.nextLine();
            switch(opcion){
                case "1":
                    query.insertEmployee();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break b1;
                default:
                    System.out.println("Ingrese un valor correcto");
                    break;
            }
        }
        String sql2="select e.employee_id, concat(m.first_name,\" \",m.last_name) 'Jefe' from employees e, employees m where e.manager_id=m.employee_id;";
        identificadores=query.setId(sql2);


    }

}