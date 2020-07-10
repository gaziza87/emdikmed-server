package kz.gaziza.emdikmed.repository.device;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.device.DeviceStat;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceStatRepository extends ResourceUtilRepository<DeviceStat, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'parameterMap' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'checkDate' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'deviceName' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'userAccountId' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<DeviceStat> query(String searchString, Pageable pageable);

    @Query("{userAccountId: '?0', state: '?1'}")
    List<DeviceStat> getAllByUserAccountId(String patientId, State state);

    @Query("{userAccountId:'?0'}")
    List<DeviceStat> getAllByUserAccountIdSortedByCreatedDate(String userId, Sort sort);

    List<DeviceStat> findAllByUserAccountIdAndCheckDateBetweenOrderByCheckDateAsc(String userId, LocalDateTime d1, LocalDateTime d2);



}
