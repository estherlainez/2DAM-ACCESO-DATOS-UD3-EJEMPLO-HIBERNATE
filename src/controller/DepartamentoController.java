package controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Departamentos;
import utilidades.HibernateUtil;

public class DepartamentoController {

	public boolean insertarDepartamento(Departamentos e) {
		try {
			SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
			Session sesion=sessionFactory.openSession();
			Transaction ts=sesion.beginTransaction();
			
			Departamentos dep=new Departamentos();
			dep.setDptoNo(e.getDptoNo());
			dep.setDnombre(e.getDnombre());
			dep.setLoc(e.getLoc());
			
			sesion.save(dep);
			ts.commit();
			sesion.close();
			return true;
		} catch (ConstraintViolationException e1) {
			System.out.println("Departamento duplicado");
			e1.printStackTrace();
			return false;
		}
	
		
	}
}
