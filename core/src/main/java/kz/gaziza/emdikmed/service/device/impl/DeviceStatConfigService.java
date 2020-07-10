package kz.gaziza.emdikmed.service.device.impl;

import kz.gaziza.emdikmed.model.device.DeviceStat;
import kz.gaziza.emdikmed.model.device.DeviceStatConfig;
import kz.gaziza.emdikmed.repository.device.DeviceStatConfigRepository;
import kz.gaziza.emdikmed.service.device.IDeviceStatConfigService;
import kz.gaziza.emdikmed.service.device.IDeviceStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceStatConfigService implements IDeviceStatConfigService {

    @Autowired
    DeviceStatConfigRepository deviceStatConfigRepository;
    @Autowired
    IDeviceStatService deviceStatService;

    @Override
    public Page<DeviceStatConfig> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 10;

        String sortBy = "id";

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where("id").is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("userId")) {
            query.addCriteria(Criteria.where("userId").is(allRequestParams.get("userId")));
        }

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;

        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return deviceStatConfigRepository.findAll(query, pageableRequest);
    }

    @Override
    public List<DeviceStatConfig> readIterable() {
        return deviceStatConfigRepository.findAll();
    }

    @Override
    public List<DeviceStatConfig> readAll() {
        return deviceStatConfigRepository.findAll();
    }

    @Override
    public DeviceStatConfig readByUserAccountId(String userAccountId) {
        return deviceStatConfigRepository.getByUserAccountId(userAccountId);
    }

    @Override

    public DeviceStatConfig read(String id) {
        return deviceStatConfigRepository.findById(id).get();
    }

    @Override
    public DeviceStatConfig create(DeviceStatConfig deviceStatConfig) {
        deviceStatConfig = deviceStatConfigRepository.save(deviceStatConfig);

        DeviceStat deviceStat = deviceStatService.getLastRecordByUserAccountId(deviceStatConfig.getUserAccountId());
        deviceStat.setHealthStatus(deviceStatService.defineHealthStatus(deviceStat));

        return deviceStatConfig;
    }

    @Override
    public DeviceStatConfig update(DeviceStatConfig deviceStatConfig) {
        return deviceStatConfigRepository.save(deviceStatConfig);
    }

    @Override
    public void delete(String id) {
        deviceStatConfigRepository.deleteById(id);
    }
}
