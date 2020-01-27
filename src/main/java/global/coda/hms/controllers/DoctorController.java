package global.coda.hms.controllers;

import global.coda.hms.beans.DoctorBean;
import global.coda.hms.services.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    DoctorService DoctorService;

    public DoctorController(DoctorService service) {
        this.DoctorService = service;
    }

    @RequestMapping("/read/{id}")
    @ResponseBody
    DoctorBean getDoctorById(@PathVariable("id") int id) {
        return DoctorService.getDoctorById(id);
    }

    @PostMapping("/create")
    @ResponseBody
    int createDoctor(@RequestBody DoctorBean Doctor) {
        return DoctorService.insertDoctor(Doctor);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    int updateDoctor(@RequestBody DoctorBean newDoctor, @PathVariable("id") int id) {
        return DoctorService.updateDoctor(newDoctor, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    int deleteDoctor(@PathVariable("id") int id) {
        return DoctorService.deleteDoctor(id);
    }

}
