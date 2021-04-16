package com.employee.model;

import java.sql.Date;
import java.util.List;

import com.emp.model.EmpVO;
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
	
	public void deleteEmp(String employee_account) {
		dao.delete(employee_account);
	}

	public EmployeeBean getOneEmp(String employee_account) {
		return dao.findByPrimaryKey(employee_account);
	}

	public List<EmployeeBean> getAll() {
		return dao.getAll();
	}
	

}
