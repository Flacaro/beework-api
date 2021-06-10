package com.beework.services;

import com.beework.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TasksService {

    private CommentiService commentiService = CommentiService.getInstance();

    List<Task> tasks = new ArrayList<> (Arrays.asList(
            new Task("1", "telefono casa", "25-06-2021", "urgente",
                    false,"La casa è diventata bella", "Bellazio", commentiService.getCommenti()),
             new Task("2", "telefoni casa", "26-06-2021", "basso",
                              false,"La casa è bella", "Bellazio", commentiService.getCommenti())
    ));


    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(String id){
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public void aggiungiTask(Task task){
        tasks.add(task);
    }
    public void eliminaTask(String id){
        tasks.stream().filter(t -> t.getId().equals(id));

    }
}
