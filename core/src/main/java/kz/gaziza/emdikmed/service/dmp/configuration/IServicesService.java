package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
/**
 *
 * @author Alikhan
 */
public interface IServicesService {
    /**
     *
     * @param allRequestParams all params
     * @return search objects
     */
    Page<Service> readPageable(Map<String, String> allRequestParams);

    /**
     *
     * @param allRequestParams all params
     * @return search objects
     */
    Page<Service> searchPageable(Map<String, String> allRequestParams);

    /**
     *
     * @return
     */
    List<Service> readIterable();

    /**
     *
     * @param categoryId
     * @return
     */
    List<Service> readIterableByCategoryId(String categoryId);

    /**
     *
     * @param ids
     * @return
     */
    Object readCategorizedListByIdIn(List<String> ids);

    /**
     *
     * @return
     */
    Object readCategorizedList();

    /**
     *
     * @param id
     * @return
     */
    Service readOne(String id);

    /**
     *
     * @param service
     * @return
     */
    Service create(Service service);

    /**
     *
     * @param service
     * @return
     */
    Service update(Service service);

    /**
     *
     * @param id
     */
    void delete(String id);
    
}
