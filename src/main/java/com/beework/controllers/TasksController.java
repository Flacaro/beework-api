package com.beework.controllers;

import com.beework.models.Progetto;
import com.beework.models.Task;
import com.beework.services.ProgettiService;
import com.beework.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @RequestMapping("progetti/{progettoId}/tasks")
    public List<Task> getTasks(){
        return tasksService.getTasks();
    }

    @RequestMapping("progetti/{progettoId}/tasks/{taskId}")
    public Task getTask(@PathVariable String id){
        return tasksService.getTask(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "progeprogetti/{progettoId}/taskstti")
    public void aggiungiTask(@RequestBody Task task) {
        tasksService.aggiungiTask(task);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "progetti/{progettoId}/tasks/{taskId}/{id}")
    public void eliminaTask(@PathVariable String id){
        tasksService.eliminaTask(id);
    }

}
