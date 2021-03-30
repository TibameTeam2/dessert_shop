package com.employee.model;

import java.util.List;

public interface EmployeeDAO_interface {
	
	public void insert(EmployeeBean emp_Insert);
	public void update(EmployeeBean emp_Update);
	public void delete(String employee_account);
	public EmployeeBean findByPrimaryKey(String employee_account);
	public List<EmployeeBean> getAll();

}
