package controller;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Departamentos;
import modelo.Empleados;
import utilidades.HibernateUtil;

public class EmpleadosController {
	public boolean insertarEmpleado(Empleados e, Departamentos d) {
		try {
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session sesion=sessionFactory.openSession();
			Transaction ts=sesion.beginTransaction();
			Departamentos dep=new Departamentos();
			dep.setDptoNo(d.getDptoNo());
			Empleados emp=new Empleados();
			emp.setDepartamentos(dep);
			emp.setEmpNo(e.getEmpNo());
			emp.setApellido(e.getApellido());
			emp.setOficio(e.getOficio());
			emp.setDir(e.getDir());
			emp.setFechaAlta(Date.valueOf(LocalDate.now()));
			emp.setSalario(e.getSalario());
			sesion.save(emp);
			ts.commit();
			sesion.close();
			return true;
		} catch (ConstraintViolationException e1) {
			System.out.println("Empleado duplicado");
			e1.printStackTrace();
			return false;
		}catch(TransientPropertyValueException e1) {
			System.out.println("El departamento no existe");
			e1.getStackTrace();
			return false;
		}
		
		
	}

}
