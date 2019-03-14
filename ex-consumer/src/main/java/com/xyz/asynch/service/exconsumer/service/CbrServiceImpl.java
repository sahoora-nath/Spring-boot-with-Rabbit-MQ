package com.xyz.asynch.service.exconsumer.service;

import com.xyz.asynch.service.exconsumer.domain.NotificationAudit;
import com.xyz.asynch.service.exconsumer.repo.NotificationAuditRepository;
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
