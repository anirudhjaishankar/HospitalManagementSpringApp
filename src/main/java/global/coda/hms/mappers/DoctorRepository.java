package global.coda.hms.mappers;

import global.coda.hms.beans.DoctorBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorBean, Integer> {
}
