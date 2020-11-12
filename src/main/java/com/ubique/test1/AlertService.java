package com.ubique.test1;

import java.util.Date;
import java.util.UUID;

public class AlertService {
    private final MapAlertDAO storage = new MapAlertDAO();
    private AlertDAO alertDAO;
    public AlertService(AlertDAO alertDAO) {
        this.alertDAO = alertDAO;
    }

    public UUID raiseAlert() {
        return  alertDAO.addAlert(new Date());
    }
    public Date getAlertTime(UUID id) {
        return alertDAO.getAlert(id);
    }
}
