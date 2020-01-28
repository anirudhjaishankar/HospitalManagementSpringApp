package global.coda.hms.services;

import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.PatientBean;
import global.coda.hms.beans.UserBean;
import global.coda.hms.mappers.AddressMapper;
import global.coda.hms.mappers.PatientMapper;
import global.coda.hms.mappers.PatientRepository;
import global.coda.hms.mappers.UserMapper;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static global.coda.hms.constants.PatientServiceConstants.PATIENT_SERVICE_CLASSNAME;
import static global.coda.hms.utilities.LoggerInitializer.initiateLogger;

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

    public Logger LOGGER = initiateLogger(PATIENT_SERVICE_CLASSNAME);

    public PatientBean getPatientById(int id) {
        LOGGER.traceEntry(Integer.toString(id));
        PatientBean patient = new PatientBean();
        Optional<PatientBean> patientBeanOptional = patientRepository.findById(id);
        if(patientBeanOptional.isPresent()){
            patient = patientBeanOptional.get();
        }
        LOGGER.traceExit(patient.toString());
        return patient;
    }

    public List<PatientBean> getAllPatients(){
        LOGGER.traceEntry();
        List<PatientBean> patientList = (ArrayList<PatientBean>) patientRepository.findAll();
        LOGGER.traceExit(patientList.size());
        return patientList;
    }

    public PatientBean insertPatient(PatientBean newPatient) {
        LOGGER.traceEntry(newPatient.toString());
        PatientBean createdPatientBean = patientRepository.save(newPatient);
        LOGGER.traceExit(createdPatientBean.toString());
        return createdPatientBean;
    }

    public PatientBean updatePatient(PatientBean newPatient, int patientId) {
        LOGGER.traceEntry(newPatient.toString(), patientId);
        PatientBean patient = patientRepository.findById(patientId).get();
        patient.setBloodGroup(newPatient.getBloodGroup());
        patient.setPhone(newPatient.getPhone());
        UserBean user = patient.getUserDetails();
        user.setAge(newPatient.getUserDetails().getAge());
        user.setGender(newPatient.getUserDetails().getGender());
        user.setUsername(newPatient.getUserDetails().getUsername());
        user.setPassword(newPatient.getUserDetails().getPassword());
        AddressBean address = user.getAddress();
        address.setFlatName(newPatient.getUserDetails().getAddress().getFlatName());
        address.setFlatNumber(newPatient.getUserDetails().getAddress().getFlatNumber());
        address.setStreetName(newPatient.getUserDetails().getAddress().getStreetName());
        address.setAreaName(newPatient.getUserDetails().getAddress().getAreaName());
        address.setCityName(newPatient.getUserDetails().getAddress().getCityName());
        address.setStateName(newPatient.getUserDetails().getAddress().getStateName());
        address.setPincode(newPatient.getUserDetails().getAddress().getPincode());
        user.setAddress(address);
        patient.setUserDetails(user);
        PatientBean updatedPatient = patientRepository.save(patient);
        LOGGER.traceExit(updatedPatient);
        return updatedPatient;
    }

    public boolean deletePatient(int patientId) {
        LOGGER.traceEntry(Integer.toString(patientId));
        PatientBean patientDelete = new PatientBean();
        Optional<PatientBean> patient = patientRepository.findById(patientId);
        if(patient.isPresent()){
            patientDelete = patient.get();
        }else{
            return false;
        }
        patientDelete.setIsDeleted(true);
        patientRepository.save(patientDelete);
        LOGGER.traceExit(true);
        return true;
    }
}
