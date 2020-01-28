package global.coda.hms.services;

import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.DoctorBean;
import global.coda.hms.beans.UserBean;
import global.coda.hms.mappers.AddressMapper;
import global.coda.hms.mappers.DoctorMapper;
import global.coda.hms.mappers.DoctorRepository;
import global.coda.hms.mappers.UserMapper;
import global.coda.hms.utilities.LoggerInitializer;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static global.coda.hms.constants.DoctorServiceConstants.DOCTOR_SERVICE_CLASSNAME;

@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DoctorRepository doctorRepository;

    Logger LOGGER = LoggerInitializer.initiateLogger(DOCTOR_SERVICE_CLASSNAME);

     public DoctorBean getDoctorById(int id) {
         LOGGER.traceEntry(Integer.toString(id));
         DoctorBean doctor = new DoctorBean();
         Optional<DoctorBean> doctorResolver = doctorRepository.findById(id);
         if(doctorResolver.isPresent()){
             doctor = doctorResolver.get();
         }
         LOGGER.traceExit(doctor.toString());
        return doctor;
    }

    public DoctorBean insertDoctor(DoctorBean newDoctor) {
         LOGGER.traceEntry(newDoctor.toString());
         DoctorBean insertedDoctor = doctorRepository.save(newDoctor);
         LOGGER.traceExit(insertedDoctor);
         return insertedDoctor;
    }

    public DoctorBean updateDoctor(DoctorBean newDoctor, int doctorId) {
        LOGGER.traceEntry(newDoctor.toString(), doctorId);
        DoctorBean recordDoctor = new DoctorBean();
        Optional<DoctorBean> resolver = doctorRepository.findById(doctorId);
        if(resolver.isPresent())
        {
            recordDoctor = resolver.get();
        }
        recordDoctor.setPhone(newDoctor.getPhone());
        recordDoctor.setSpeciality(newDoctor.getSpeciality());
        UserBean user = newDoctor.getUserDetails();
        user.setAge(newDoctor.getUserDetails().getAge());
        user.setGender(newDoctor.getUserDetails().getGender());
        user.setUsername(newDoctor.getUserDetails().getUsername());
        user.setPassword(newDoctor.getUserDetails().getPassword());
        user.setName(newDoctor.getUserDetails().getName());
        AddressBean address = user.getAddress();
        address.setFlatName(newDoctor.getUserDetails().getAddress().getFlatName());
        address.setFlatNumber(newDoctor.getUserDetails().getAddress().getFlatNumber());
        address.setStreetName(newDoctor.getUserDetails().getAddress().getStreetName());
        address.setAreaName(newDoctor.getUserDetails().getAddress().getAreaName());
        address.setCityName(newDoctor.getUserDetails().getAddress().getCityName());
        address.setStateName(newDoctor.getUserDetails().getAddress().getStateName());
        address.setPincode(newDoctor.getUserDetails().getAddress().getPincode());
        user.setAddress(address);
        recordDoctor.setUserDetails(user);
        DoctorBean updatedDoctor = doctorRepository.save(recordDoctor);
        LOGGER.traceExit(updatedDoctor.toString());
        return updatedDoctor;
    }

    public boolean deleteDoctor(int doctorId) {
        LOGGER.traceEntry(Integer.toString(doctorId));
        DoctorBean recordDoctor = new DoctorBean();
        Optional<DoctorBean> resolver = doctorRepository.findById(doctorId);
        if(resolver.isPresent())
        {
            recordDoctor = resolver.get();
        }else{
            return false;
        }
        recordDoctor.setIsDeleted(true);
        DoctorBean deletedRecord = doctorRepository.save(recordDoctor);
        LOGGER.traceExit(deletedRecord.toString());
        return true;
    }
}
