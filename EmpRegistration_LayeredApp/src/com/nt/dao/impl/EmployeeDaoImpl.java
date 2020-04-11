package com.nt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.dao.IEmployeeDao;
import com.nt.dto.EmployeeDTO;

public class EmployeeDaoImpl implements IEmployeeDao{

	private static final String INSERT_EMP_QUERY = " INSERT INTO LAYERED_EMPLOYEE VALUES(EMPID_SEQ.NEXTVAL,?,?,?,?,?,?)";

	// Helper class to get the Pooled connection from Server 
	private Connection getPooledConn() throws Exception{
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		
		// 1. create InitialContext obj
		ic = new InitialContext();
		// 2. get Datasource obj
		ds = (DataSource) ic.lookup("java:/comp/env/DsJndi");
		// 3. get Pooled Conn obj
		con = ds.getConnection();
		return con;
	}
	
	@Override
	public int insertEmp(EmployeeDTO dto) throws Exception {
		
		Connection con = null;
		PreparedStatement pst = null;
		int count = 0;
		// get Pooled jdbc Conn obj
		con = getPooledConn();
		pst = con.prepareStatement(INSERT_EMP_QUERY);
		
		pst.setString(1, dto.getEmpName());
		pst.setString(2, dto.getEmpAddr());
		pst.setDate(3, dto.getDoj());
		pst.setDouble(4, dto.getBasSal());
		pst.setDouble(5, dto.getGrossSal());
		pst.setDouble(6, dto.getNetSal());
		
		count = pst.executeUpdate();
		
		pst.close();
		con.close();
		
		return count;
	}
	
}// class
