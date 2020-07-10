package kz.gaziza.emdikmed.service.device.impl;

import com.google.gson.Gson;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.device.DeviceStat;
import kz.gaziza.emdikmed.model.device.DeviceStatConfig;
import kz.gaziza.emdikmed.model.device.DeviceStatConfigObjValue;
import kz.gaziza.emdikmed.repository.device.DeviceStatConfigRepository;
import kz.gaziza.emdikmed.repository.device.DeviceStatRepository;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DeviceStatService implements IDeviceStatService {

    @Autowired
    DeviceStatRepository deviceStatRepository;
    @Autowired
    DeviceStatConfigRepository deviceStatConfigRepository;

    @Override
    public Page<DeviceStat> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 5;

        String sortBy = "id";

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where("id").is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("parameterMap")) {
            query.addCriteria(Criteria.where("parameterMap").is(allRequestParams.get("parameterMap")));
        }
        if (allRequestParams.containsKey("checkDate")) {
            query.addCriteria(Criteria.where("checkDate").regex(allRequestParams.get("checkDate")));
        }
        if (allRequestParams.containsKey("deviceName")) {
            query.addCriteria(Criteria.where("deviceName").regex(allRequestParams.get("deviceName")));
        }
        if (allRequestParams.containsKey("userAccountId")) {
            query.addCriteria(Criteria.where("userAccountId").regex(allRequestParams.get("userAccountId")));
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


        return deviceStatRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<DeviceStat> search(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 10;

        String sortBy = "id";

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

        return deviceStatRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<DeviceStat> readIterableByUserAccountId(String userAccountId) {
        return deviceStatRepository.getAllByUserAccountId(userAccountId, State.ACTIVE);
    }

    @Override
    public List<DeviceStat> readAll() {
        return deviceStatRepository.findAll();
    }

    @Override
    public DeviceStat getLastRecordByUserAccountId(String userAccountId) {
        System.out.println(new Gson().toJsonTree(deviceStatRepository.getAllByUserAccountIdSortedByCreatedDate(userAccountId, Sort.by(Sort.Direction.DESC, "createdDate"))));
        return deviceStatRepository.getAllByUserAccountIdSortedByCreatedDate(userAccountId, Sort.by(Sort.Direction.DESC, "createdDate")).get(0);
    }

    @Override
    public DeviceStat get(String id) {
        return deviceStatRepository.findById(id).get();
    }

    @Override
    public DeviceStat create(DeviceStat deviceStat) {
        deviceStat.setHealthStatus(defineHealthStatus(deviceStat));
        return deviceStatRepository.save(deviceStat);
    }

    @Override
    public DeviceStat update(DeviceStat deviceStat) {
        deviceStat.setHealthStatus(defineHealthStatus(deviceStat));
        return deviceStatRepository.save(deviceStat);
    }

    @Override
    public void delete(String id) {
        deviceStatRepository.deleteById(id);
    }

    @Override
    public int defineHealthStatus(DeviceStat deviceStat) {
        if (deviceStat == null) {
            return 6; // Устройство не зарегистрировано
        }

        if (deviceStat.getId() != null) {
            Map<String, Object> parameters = deviceStat.getParameterMap();

            AtomicBoolean hasValue = new AtomicBoolean(false);

            parameters.forEach((s, o) -> {
                if (o != null) hasValue.set(true);
            });

            if (!hasValue.get()) {
                return 5; // Нет данных о браслете;
            }
        }

        DeviceStatConfig deviceStatConfig = deviceStatConfigRepository.getByUserAccountId(deviceStat.getUserAccountId());

        AtomicInteger healthStatus = new AtomicInteger();

        if (deviceStatConfig != null) {
            AtomicBoolean everythingIsGood = new AtomicBoolean(true);
            AtomicBoolean somethingWrong = new AtomicBoolean(false);
            AtomicBoolean maybeWrongInTheFuture = new AtomicBoolean(false);

            Map<String, DeviceStatConfigObjValue> parametersMap = deviceStatConfig.getParameterMap();
            parametersMap.keySet().forEach(parameter -> {
                DeviceStatConfigObjValue referenceValues = parametersMap.get(parameter);
                Object valueFromDevice = deviceStat.getParameterMap().get(parameter);

                if (referenceValues.isActive() && valueFromDevice != null) {
                    if (valueFromDevice instanceof Integer) {
                        Integer referenceValue1 = (Integer) referenceValues.getValue1();
                        Integer referenceValue2 = (Integer) referenceValues.getValue2();
                        Integer value = (Integer) valueFromDevice;

                        Integer differenceWithMax = (Integer) referenceValues.getValue2() - (Integer) valueFromDevice;
                        Integer differenceWithMin = (Integer) valueFromDevice - (Integer) referenceValues.getValue1();

                        Integer differenceBetweenMinAndMax = (Integer) referenceValues.getValue2() - (Integer) referenceValues.getValue1();
                        Double twentyPercentAllowable = differenceBetweenMinAndMax * 0.2;

                        if (value < referenceValue1 || value > referenceValue2) {
                            everythingIsGood.set(false);
                            somethingWrong.set(true);
                        } else {
                            if (differenceWithMax <= twentyPercentAllowable || differenceWithMin <= twentyPercentAllowable) {
                                everythingIsGood.set(false);
                                maybeWrongInTheFuture.set(true);
                            }
                        }

                    } else if (valueFromDevice instanceof Double) {
                        Double referenceValue1 = (Double) referenceValues.getValue1();
                        Double referenceValue2 = (Double) referenceValues.getValue2();
                        Double value = (Double) valueFromDevice;

                        Double differenceWithMax = (Double) referenceValues.getValue2() - (Double) valueFromDevice;
                        Double differenceWithMin = (Double) valueFromDevice - (Double) referenceValues.getValue1();

                        Double differenceBetweenMinAndMax = (Double) referenceValues.getValue2() - (Double) referenceValues.getValue1();
                        Double twentyPercentAllowable = differenceBetweenMinAndMax * 0.2;

                        if (value < referenceValue1 || value > referenceValue2) {
                            everythingIsGood.set(false);
                            somethingWrong.set(true);
                        } else {
                            if (differenceWithMax <= twentyPercentAllowable || differenceWithMin <= twentyPercentAllowable) {
                                everythingIsGood.set(false);
                                maybeWrongInTheFuture.set(true);
                            }
                        }
                    }
                }

                if (everythingIsGood.get()) {
                    healthStatus.set(3); // Показание в норме;
                } else if (!somethingWrong.get() && maybeWrongInTheFuture.get()) {
                    healthStatus.set(2); // Показания в пределах границ
                } else {
                    healthStatus.set(1); // Показания отклонены от нормы;
                }
            });
        } else {
            healthStatus.set(4); // Устройство не настроено;
        }

        return healthStatus.get();
    }
}
