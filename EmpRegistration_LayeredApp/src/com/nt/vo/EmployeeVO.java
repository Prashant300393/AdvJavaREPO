package com.nt.vo;

/**
 * 	VO is a Java Bean class which Holds either inputs or outputs and all its properites as String because Data
 *  comes from FORM page as String only..Servlet will convert VO class into DTO class 
 */

public class EmployeeVO {

	private String empName;
	private String empAddr;
	private String doj;
	private String basSal;
	
	public EmployeeVO() {
		super();
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddr() {
		return empAddr;
	}

	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getBasSal() {
		return basSal;
	}

	public void setBasSal(String basSal) {
		this.basSal = basSal;
	}

	@Override
	public String toString() {
		return "EmployeeVO [empName=" + empName + ", empAddr=" + empAddr + ", doj=" + doj + ", basSal=" + basSal + "]";
	}
	
}
