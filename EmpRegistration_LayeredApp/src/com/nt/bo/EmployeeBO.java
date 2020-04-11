package com.nt.bo;

import java.sql.Date;

public class EmployeeBO {

	private String empName;
	private String empAddr;
	private Date doj;
	private double basSal;
	private double grossSal;
	private double netSal;

	public EmployeeBO() {
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
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public double getBasSal() {
		return basSal;
	}
	public void setBasSal(double basSal) {
		this.basSal = basSal;
	}
	public double getGrossSal() {
		return grossSal;
	}
	public void setGrossSal(double grossSal) {
		this.grossSal = grossSal;
	}
	public double getNetSal() {
		return netSal;
	}
	public void setNetSal(double netSal) {
		this.netSal = netSal;
	}
	@Override
	public String toString() {
		return "EmployeeBO [empName=" + empName + ", empAddr=" + empAddr + ", doj=" + doj + ", basSal=" + basSal
				+ ", grossSal=" + grossSal + ", netSal=" + netSal + "]";
	}

}
