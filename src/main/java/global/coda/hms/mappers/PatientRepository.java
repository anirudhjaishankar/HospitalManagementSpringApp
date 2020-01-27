package global.coda.hms.mappers;

import global.coda.hms.beans.PatientBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends CrudRepository<PatientBean, Integer>{}