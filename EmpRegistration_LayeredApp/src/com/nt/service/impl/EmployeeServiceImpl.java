package com.nt.service.impl;

import com.nt.dao.IEmployeeDao;
import com.nt.dao.impl.EmployeeDaoImpl;
import com.nt.dto.EmployeeDTO;
import com.nt.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService{

	private IEmployeeDao dao;
	
	// We have to create only One Object of this class	
	public EmployeeServiceImpl() {
		dao = new EmployeeDaoImpl();
	}
	
	@Override
	public int insertEmp(EmployeeDTO dto) throws Exception {
		// grossSal = 10000 + 4000
		double grossSal = dto.getBasSal() + dto.getBasSal() * 0.4 ;
		// netSal = 14000 - (2800) ==> 13200
		double netSal = grossSal - (grossSal*0.2);

		dto.setGrossSal(grossSal);
		dto.setNetSal(netSal);
		
		return dao.insertEmp(dto);
	}

}
