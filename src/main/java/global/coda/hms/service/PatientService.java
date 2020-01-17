package global.coda.hms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.coda.hms.Mapper.AddressMapper;
import global.coda.hms.Mapper.PatientMapper;
import global.coda.hms.Mapper.UserMapper;
import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.PatientBean;

@Service
public class PatientService {
	
	@Autowired
	PatientMapper patientMapper;
	
	@Autowired 
	AddressMapper addressMapper;
	
	@Autowired
	UserMapper userMapper;
	


	public PatientBean getPatientById(int id) {
		return patientMapper.getPatientById(id);
	}
	
	public int insertPatient(PatientBean newPatient) {
		AddressBean address = newPatient.getAddress();
		int addressRowsAffected = addressMapper.insertAddress(address);
		int userRowsAffected = userMapper.insertUser(newPatient);
		return patientMapper.insertPatients(newPatient);
	}
}
