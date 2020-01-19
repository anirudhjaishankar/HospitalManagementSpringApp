package global.coda.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.coda.hms.Mapper.AddressMapper;
import global.coda.hms.Mapper.DoctorMapper;
import global.coda.hms.Mapper.UserMapper;
import global.coda.hms.beans.AddressBean;
import global.coda.hms.beans.DoctorBean;

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
        AddressBean address = newDoctor.getAddress();
        int addressRowsAffected = addressMapper.insertAddress(address);
        int userRowsAffected = userMapper.insertUser(newDoctor);
        return doctorMapper.insertDoctor(newDoctor);
    }

    public int updateDoctor(DoctorBean newDoctor, int DoctorId) {
        AddressBean address = newDoctor.getAddress();
        int DoctorRowsAffected = doctorMapper.updateDoctor(newDoctor, DoctorId);
        int userId = doctorMapper.getUserIdFromDoctor(DoctorId);
        userMapper.updateUser(newDoctor, userId);
        int addressId = userMapper.getAddressIdFromUser(userId);
        int addressRowsAffected = addressMapper.updateAddress(address, addressId);
        return 0;
    }
    
    public int deleteDoctor(int DoctorId) {
        return doctorMapper.deleteDoctor(DoctorId);
    }
}
