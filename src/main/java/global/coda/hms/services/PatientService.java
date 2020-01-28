package global.coda.hms.services;

import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.PatientBean;
import global.coda.hms.mappers.AddressMapper;
import global.coda.hms.mappers.PatientMapper;
import global.coda.hms.mappers.PatientRepository;
import global.coda.hms.mappers.UserMapper;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        patient.setUserDetails(newPatient.getUserDetails());
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
        }
        patientRepository.delete(patientDelete);
        boolean deleted = patientRepository.existsById(patientId);
        LOGGER.traceExit(deleted);
        return deleted;
    }
}
