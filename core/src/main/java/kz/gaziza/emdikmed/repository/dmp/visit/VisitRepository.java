package kz.gaziza.emdikmed.repository.dmp.visit;

import kz.gaziza.emdikmed.model.dmp.visit.Visit;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends ResourceUtilRepository<Visit, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'name' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'visitContentId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'organizationId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'doctorId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'checkFullFill' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'visitDate' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'prevVisitId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'counter' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<Visit> query(String searchString, Pageable pageable);

    @Query("{patientId: '?0', organizationId: '?1'}")
    List<Visit> findAllByPatientIdAndOrganizationId(String patientId, String organizationId);

    @Query("{doctorId: '?0', organizationId: '?1'}")
    List<Visit> findAllByDoctorIdAndOrganizationId(String doctorId, String organizationId);

    @Query("{doctorId: '?0'}")
    List<Visit> findAllByDoctorId(String doctorId);

    @Query("{patientId: '?0'}")
    List<Visit> findAllByPatientId(String doctorId);

}
