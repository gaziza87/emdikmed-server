package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {

    Organization findByCode(String code);

}
