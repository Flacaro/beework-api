package com.beework.controllers;

import com.beework.models.Progetto;
import com.beework.models.Task;
import com.beework.services.ProgettiService;
import com.beework.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TasksController {

    @Autowired
    private TasksService tasksService;


}
