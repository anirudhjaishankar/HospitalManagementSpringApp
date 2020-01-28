package global.coda.hms.controllers;

import global.coda.hms.beans.DoctorBean;
import global.coda.hms.beans.GenericResponse;
import global.coda.hms.services.DoctorService;
import global.coda.hms.utilities.LoggerInitializer;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static global.coda.hms.constants.ControllerConstants.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    public Logger LOGGER = LoggerInitializer.initiateLogger(DOCTOR_CONTROLLER_CLASSNAME);

    @RequestMapping("/read/{id}")
    @ResponseBody
    GenericResponse<DoctorBean> getDoctorById(@PathVariable("id") int id,  @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(Integer.toString(id));
        DoctorBean doctor = doctorService.getDoctorById(id);
        GenericResponse<DoctorBean> response = new GenericResponse<DoctorBean>();
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        response.setData(doctor);
        LOGGER.traceExit(response.toString());
        return response;
    }

    @PostMapping("/create")
    @ResponseBody
    GenericResponse<DoctorBean> createDoctor(@RequestBody DoctorBean newDoctor,  @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(newDoctor.toString());
        GenericResponse<DoctorBean> response = new GenericResponse<DoctorBean>();
        DoctorBean doctor = doctorService.insertDoctor(newDoctor);
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        response.setData(doctor);
        LOGGER.traceExit(response.toString());
        return response;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    GenericResponse<DoctorBean> updateDoctor(@RequestBody DoctorBean newDoctor, @PathVariable("id") int id, @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(newDoctor.toString());
        GenericResponse<DoctorBean> response = new GenericResponse<DoctorBean>();
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        DoctorBean doctor = doctorService.updateDoctor(newDoctor, id);
        response.setData(doctor);
        LOGGER.traceExit(response.toString());
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    GenericResponse<String> deleteDoctor(@PathVariable("id") int id, @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(Integer.toString(id));
        GenericResponse<String> response = new GenericResponse<String>();
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        if(doctorService.deleteDoctor(id)){
            response.setData(DELETE_SUCCESS_MESSAGE);
        }
        LOGGER.traceExit(response.toString());
        return response;
    }

}
