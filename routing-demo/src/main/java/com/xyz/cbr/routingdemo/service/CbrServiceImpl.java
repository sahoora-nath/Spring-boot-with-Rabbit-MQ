package com.xyz.cbr.routingdemo.service;

import com.xyz.cbr.routingdemo.entity.NotificationAudit;
import com.xyz.cbr.routingdemo.repo.NotificationAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CbrServiceImpl implements CbrService {

    @Autowired
    private NotificationAuditRepository repository;

    @Override
    public NotificationAudit insertAudit(NotificationAudit notificationAudit) {
        return repository.insert(notificationAudit);
    }
}