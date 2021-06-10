package com.beework.services;

import com.beework.models.Notifica;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotificheService {
    private static NotificheService instance;

    private NotificheService() { }

    public static NotificheService getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            return new NotificheService();
        }
    }

    List<Notifica> notifiche = new ArrayList<>(Arrays.asList(
            new Notifica("1","Aggiunto progetto","Angelo ha aggiunto un progetto"),
            new Notifica("2","Aggiunto task","Ciccio ha aggiunto un task")
    ));


    public List<Notifica> getNotifiche() {
        return notifiche;
    }

}
