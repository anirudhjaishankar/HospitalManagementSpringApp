package global.coda.hms.controllers;

import global.coda.hms.beans.GenericResponse;
import global.coda.hms.beans.PatientBean;
import global.coda.hms.services.PatientService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static global.coda.hms.constants.ControllerConstants.*;
import static global.coda.hms.utilities.LoggerInitializer.initiateLogger;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    @Autowired
    PatientService patientService;
    public Logger LOGGER = initiateLogger(PATIENT_CONTROLLER_CLASSNAME);

    @RequestMapping("/read/{id}")
    @ResponseBody
    GenericResponse<PatientBean> getPatientById(@PathVariable("id") int id, @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(Integer.toString(id));
        PatientBean patientResult = patientService.getPatientById(id);
        GenericResponse<PatientBean> response = new GenericResponse<PatientBean>();
        response.setData(patientResult);
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        LOGGER.traceExit(response.toString());
        return response;
    }

    @RequestMapping("/read/getAllPatients")
    @ResponseBody
    GenericResponse<List<PatientBean>> getAllPatients(@RequestAttribute("requestId") String requestId){
        LOGGER.traceEntry();
        List<PatientBean> patientList = new ArrayList<PatientBean>();
        patientList = patientService.getAllPatients();
        LOGGER.traceExit(patientList.size());
        GenericResponse<List<PatientBean>> response= new GenericResponse<List<PatientBean>>();
        response.setRequestId(requestId);
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setData(patientList);
        LOGGER.traceExit(response.toString());
        return response;
    }

    @PostMapping("/create")
    @ResponseBody
    GenericResponse<PatientBean> createPatient(@RequestBody PatientBean patient, @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(patient.toString());
        GenericResponse<PatientBean> response = new GenericResponse<PatientBean>();
        PatientBean createdPatient = patientService.insertPatient(patient);
        if(createdPatient != null){
            response.setStatusCode(SUCCESS_STATUS_CODE);
            response.setData(createdPatient);
            response.setRequestId(requestId);
        }
        LOGGER.traceExit(response.toString());
        return response;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    GenericResponse<PatientBean> updatePatient(@RequestBody PatientBean updatedPatient, @PathVariable("id") int id, @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(updatedPatient.toString());
        GenericResponse<PatientBean> response = new GenericResponse<PatientBean>();
        PatientBean patient = patientService.updatePatient(updatedPatient,id);
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        response.setData(patient);
        LOGGER.traceExit(response.toString());
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    GenericResponse<String> deletePatient(@PathVariable("id") int id, @RequestAttribute("requestId") String requestId) {
        LOGGER.traceEntry(Integer.toString(id));
        GenericResponse<String> response = new GenericResponse<String>();
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        if(patientService.deletePatient(id)){
            response.setData(DELETE_SUCCESS_MESSAGE);
        }
        LOGGER.traceExit(response.toString());
        return response;
    }

}
