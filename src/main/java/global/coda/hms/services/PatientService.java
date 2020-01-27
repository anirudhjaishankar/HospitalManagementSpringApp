package global.coda.hms.services;

import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.PatientBean;
import global.coda.hms.mappers.AddressMapper;
import global.coda.hms.mappers.PatientMapper;
import global.coda.hms.mappers.PatientRepository;
import global.coda.hms.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PatientRepository patientRepository;

    public PatientBean getPatientById(int id) {
        PatientBean patient = new PatientBean();
        Optional<PatientBean> patientBeanOptional = patientRepository.findById(id);
        if(patientBeanOptional.isPresent()){
            patient = patientBeanOptional.get();
        }
        return patient;
    }

    public int insertPatient(PatientBean newPatient) {
        PatientBean createdPatientBean = patientRepository.save(newPatient);
        if(createdPatientBean != null){
            return 1;
        }
        return 0;
//        AddressBean address = newPatient.getUserDetails().getAddress();
//        int addressRowsAffected = addressMapper.insertAddress(address);
//        int userRowsAffected = userMapper.insertUser(newPatient.getUserDetails());
    }

    public int updatePatient(PatientBean newPatient, int patientId) {
        AddressBean address = newPatient.getUserDetails().getAddress();
        int patientRowsAffected = patientMapper.updatePatient(newPatient, patientId);
        int userId = patientMapper.getUserIdFromPatient(patientId);
        userMapper.updateUser(newPatient.getUserDetails(), userId);
        int addressId = userMapper.getAddressIdFromUser(userId);
        int addressRowsAffected = addressMapper.updateAddress(address, addressId);
        return 0;
    }

    public int deletePatient(int patientId) {
        return patientMapper.deletePatient(patientId);
    }
}
