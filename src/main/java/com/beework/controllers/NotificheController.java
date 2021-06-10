package com.beework.controllers;

import com.beework.models.Notifica;
import com.beework.services.NotificheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificheController {
    @Autowired
    private NotificheService notificheService;

    @RequestMapping("utenti/{username}/notifiche")
    public List<Notifica> getNotifiche(){
        return notificheService.getNotifiche();
    }

}
