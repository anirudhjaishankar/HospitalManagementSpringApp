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
        return patientMapper.insertPatient(newPatient);
    }

    public int updatePatient(PatientBean newPatient, int patientId) {
        AddressBean address = newPatient.getAddress();
        int patientRowsAffected = patientMapper.updatePatient(newPatient, patientId);
        int userId = patientMapper.getUserIdFromPatient(patientId);
        userMapper.updateUser(newPatient, userId);
        int addressId = userMapper.getAddressIdFromUser(userId);
        int addressRowsAffected = addressMapper.updateAddress(address, addressId);
        return 0;
    }

    public int deletePatient(int patientId) {
        return patientMapper.deletePatient(patientId);
    }
}
