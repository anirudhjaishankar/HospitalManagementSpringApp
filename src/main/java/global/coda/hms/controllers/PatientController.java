package global.coda.hms.controllers;

import global.coda.hms.beans.GenericResponse;
import global.coda.hms.beans.PatientBean;
import global.coda.hms.services.PatientService;
import global.coda.hms.utilities.LoggerInitializer;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import static global.coda.hms.utilities.LoggerInitializer.initiateLogger;
import static global.coda.hms.constants.PatientServiceConstants.PATIENT_SERVICE_CLASSNAME;
import static global.coda.hms.constants.ControllerConstants.SUCCESS_STATUS_CODE;

@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientService patientService;
    public Logger LOGGER = initiateLogger(PATIENT_SERVICE_CLASSNAME);

    public PatientController(PatientService service) {
        this.patientService = service;
    }

    @RequestMapping("/read/{id}")
    @ResponseBody
    PatientBean getPatientById(@PathVariable("id") int id, @RequestAttribute("requestId") int requestId) {
        LOGGER.traceEntry(Integer.toString(id));
        PatientBean patientResult = patientService.getPatientById(id);
        GenericResponse<PatientBean> response = new GenericResponse<PatientBean>();
        response.setData(patientResult);
        response.setStatusCode(SUCCESS_STATUS_CODE);
        response.setRequestId(requestId);
        LOGGER.traceExit(response.toString());
        return patientResult;
    }

    @PostMapping("/create")
    @ResponseBody
    int createPatient(@RequestBody PatientBean patient) {
        LOGGER.traceEntry();
        return patientService.insertPatient(patient);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    int updatePatient(@RequestBody PatientBean newPatient, @PathVariable("id") int id) {
        return patientService.updatePatient(newPatient, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    int deletePatient(@PathVariable("id") int id) {
        return patientService.deletePatient(id);
    }

}
