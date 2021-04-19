package com.employee.model;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDAO_interface {
	
	public void insert(EmployeeBean emp_Insert);
	public void backend_insert(EmployeeBean emp_Insert);
	public void update(EmployeeBean emp_Update);
	public void backend_update(EmployeeBean emp_Update);
	public void delete(String employee_account);
	public void backend_delete(String employee_account, Connection con);
	public EmployeeBean findByPrimaryKey(String employee_account);
	public List<EmployeeBean> getAll();

}
