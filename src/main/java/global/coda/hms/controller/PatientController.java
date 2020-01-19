package global.coda.hms.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import global.coda.hms.beans.PatientBean;
import global.coda.hms.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientService patientService;

	public PatientController(PatientService service) {
		this.patientService = service;
	}

	@RequestMapping("/read/{id}")
	@ResponseBody
	PatientBean getPatientById(@PathVariable("id") int id) {
		return patientService.getPatientById(id);
	}
	
	@PostMapping("/create")
	@ResponseBody
	int createPatient(@RequestBody PatientBean patient ) {
		return patientService.insertPatient(patient);
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	int updatePatient(@RequestBody PatientBean newPatient, @PathVariable("id") int id ) {
	    return patientService.updatePatient(newPatient, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	int deletePatient(@PathVariable("id") int id) {
	    return patientService.deletePatient(id);
	}

}
