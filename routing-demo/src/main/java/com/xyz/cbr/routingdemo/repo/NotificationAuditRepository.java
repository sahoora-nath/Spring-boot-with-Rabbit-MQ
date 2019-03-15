package com.xyz.cbr.routingdemo.repo;

import com.xyz.cbr.routingdemo.entity.NotificationAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAuditRepository extends MongoRepository<NotificationAudit, String>{

}