package com.xyz.asynch.service.exconsumer.service;

import com.xyz.asynch.service.exconsumer.domain.NotificationAudit;
import org.springframework.stereotype.Service;

public interface CbrService {
    NotificationAudit insertAudit(NotificationAudit notificationAudit);
}
