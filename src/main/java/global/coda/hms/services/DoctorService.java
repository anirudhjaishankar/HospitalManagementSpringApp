package global.coda.hms.services;

import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.DoctorBean;
import global.coda.hms.mappers.AddressMapper;
import global.coda.hms.mappers.DoctorMapper;
import global.coda.hms.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    UserMapper userMapper;

     public DoctorBean getDoctorById(int id) {
        return doctorMapper.getDoctorById(id);
    }

    public int insertDoctor(DoctorBean newDoctor) {
        AddressBean address = newDoctor.getUserDetails().getAddress();
        int addressRowsAffected = addressMapper.insertAddress(address);
        int userRowsAffected = userMapper.insertUser(newDoctor.getUserDetails());
        return doctorMapper.insertDoctor(newDoctor);
    }

    public int updateDoctor(DoctorBean newDoctor, int DoctorId) {
        AddressBean address = newDoctor.getUserDetails().getAddress();
        int DoctorRowsAffected = doctorMapper.updateDoctor(newDoctor, DoctorId);
        int userId = doctorMapper.getUserIdFromDoctor(DoctorId);
        userMapper.updateUser(newDoctor.getUserDetails(), userId);
        int addressId = userMapper.getAddressIdFromUser(userId);
        int addressRowsAffected = addressMapper.updateAddress(address, addressId);
        return 0;
    }

    public int deleteDoctor(int DoctorId) {
        return doctorMapper.deleteDoctor(DoctorId);
    }
}
