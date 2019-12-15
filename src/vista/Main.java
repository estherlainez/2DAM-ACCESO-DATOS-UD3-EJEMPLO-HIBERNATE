package vista;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controller.DepartamentoController;
import controller.EmpleadosController;
import modelo.Departamentos;
import modelo.Empleados;
import utilidades.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		DepartamentoController dc=new DepartamentoController();
		EmpleadosController ec=new EmpleadosController();
		int opcion=0;
		do {
			System.out.println("1.Insertar departamento");
			System.out.println("2.Insertar empleado");
			System.out.println("Elija la opcion");
			opcion=teclado.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Insertar departamento");
				System.out.println("Introduzca numero del departamento:");
				int num=teclado.nextInt();
				teclado.nextLine();
				System.out.println("Introduzca su nombre");
				String nom=teclado.nextLine();
				System.out.println("Introduzca la localidad");
				String loc=teclado.nextLine();
				Departamentos d=new Departamentos(num,nom,loc,null);
				if(dc.insertarDepartamento(d)==true) {
					System.out.println("El departamento fue guardado");
				}else {
					System.out.println("....Error");
				}
				break;
			case 2:
				System.out.println("Insertar empleado");
				try {
					System.out.println("Introduzca numero del empleado:");
					num=teclado.nextInt();
					System.out.println("Numero de departamento al que pertenece:");
					Departamentos dep = new Departamentos(3,"Administraccion y Finanzas","Zaragoza",null);
					int nuD=teclado.nextInt();
					dep.setDptoNo(nuD);
					teclado.nextLine();				
					System.out.println("Introduzca sus apellidos");
					String ap=teclado.nextLine();
					System.out.println("Introduzca su oficio");
					String of=teclado.nextLine();
					System.out.println("Introduzca su puesto, numero de dos cifras");
					short puesto=teclado.nextShort();
					teclado.nextLine();
					System.out.println("Introduzca fechaAlta (Actual), formato: DD/MM/YYYY");
					String fecha=teclado.nextLine();
					SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
					Date fechaHoy=formato.parse(fecha);
					System.out.println(fechaHoy);
					System.out.println("Introduzca su salario:");
					Float salario=teclado.nextFloat();
				
					Empleados e= new Empleados(num,dep,ap,of,puesto,fechaHoy,salario);
						
					if(ec.insertarEmpleado(e, dep)==true) {
						System.out.println("El empleado fue registrado");
					}else {
						System.out.println("....Error");
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Continuara....hemos salido de la app");
				break;
			}
		}while(opcion!=3);
		
	}

}
