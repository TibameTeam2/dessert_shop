package com.employee.model;

import java.sql.Date;
import java.util.List;

import com.emp.model.EmpVO;
import com.employee_authority.model.EmployeeAuthorityDAO;
import com.member.model.MemberBean;

public class EmployeeService {
	
	private EmployeeDAO_interface dao;
	
	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	public EmployeeBean login(EmployeeBean employee) {
		EmployeeBean emp = dao.findByPrimaryKey(employee.getEmployee_account());
		if (emp == null) {
			return null;
		}
		if (employee.getEmployee_password().equals(emp.getEmployee_password()))
			return emp;
		return null;
	}






	//0201用的
	public EmployeeBean addEmp(String employee_account, String employee_name, String employee_password,
			String employee_position, byte[] employee_photo, Date hire_date, Integer employee_status) {
		
		EmployeeBean empBean = new EmployeeBean();
		
		empBean.setEmployee_account(employee_account);
		empBean.setEmployee_name(employee_name);
		empBean.setEmployee_password(employee_password);
		empBean.setEmployee_position(employee_position);
		empBean.setEmployee_photo(employee_photo);
		empBean.setHire_date(hire_date);
		empBean.setEmployee_status(employee_status);
		
		dao.insert(empBean);
		
		return empBean;
	}

	//後台員工用的
	public boolean addEmp(EmployeeBean emp) {
		try {
			dao.backend_insert(emp);
			return true;
		}catch (Exception e){
			e.printStackTrace(System.err);
			return false;
		}
	}

	public EmployeeBean updateEmp(String employee_name, String employee_password,String employee_position,
			byte[] employee_photo, Date hire_date, Integer employee_status, String employee_account) {
		
		EmployeeBean empBean = new EmployeeBean();
		
		empBean.setEmployee_name(employee_name);
		empBean.setEmployee_password(employee_password);
		empBean.setEmployee_position(employee_position);
		empBean.setEmployee_photo(employee_photo);
		empBean.setHire_date(hire_date);
		empBean.setEmployee_status(employee_status);
		empBean.setEmployee_account(employee_account);	
		
		dao.update(empBean);
		
		return empBean;
	}

	//基本的刪除 如果只要有外鍵參考就報錯 只有在0201用
	public void deleteEmp(String employee_account) {
		dao.delete(employee_account);
	}

	//刪除員工前 會先刪除權限
	public void backend_deleteEmp(String employee_account) {
		EmployeeAuthorityDAO authDao = new EmployeeAuthorityDAO();
		authDao.deleteByEmployee(employee_account);
//		dao.delete(employee_account);
	}


	public boolean backend_update(EmployeeBean employee){
		try {
			EmployeeBean emp = dao.findByPrimaryKey(employee.getEmployee_account());
			emp.setEmployee_name(employee.getEmployee_name());
			emp.setEmployee_position(employee.getEmployee_position());
			emp.setHire_date(employee.getHire_date());
			emp.setEmployee_status(employee.getEmployee_status());
			emp.setEmployee_auth(employee.getEmployee_auth());
			if(!employee.getEmployee_password().equals("")){
				emp.setEmployee_password(employee.getEmployee_password());
			}
			if(employee.getEmployee_photo()!=null){
				emp.setEmployee_photo(employee.getEmployee_photo());
			}
			dao.backend_update(emp);
			return true;
		}catch (Exception e){
			e.printStackTrace(System.err);
			return false;
		}
	}


	public EmployeeBean getOneEmp(String employee_account) {
		return dao.findByPrimaryKey(employee_account);
	}

	public List<EmployeeBean> getAll() {
		return dao.getAll();
	}
	

}
