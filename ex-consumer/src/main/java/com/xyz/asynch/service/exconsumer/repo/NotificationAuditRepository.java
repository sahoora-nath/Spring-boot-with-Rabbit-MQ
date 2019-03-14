package com.xyz.asynch.service.exconsumer.repo;

import com.xyz.asynch.service.exconsumer.domain.NotificationAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAuditRepository extends MongoRepository<NotificationAudit, String>{

}
