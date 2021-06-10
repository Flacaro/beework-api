package com.beework.services;

import com.beework.models.Progetto;
import com.beework.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TasksService {
    private CommentiService commentiService;

    List<Task> tasks = new ArrayList<> (Arrays.asList(
            new Task("1", "telefono casa", "25-06-2021", "urgente",
                    false,"La casa è diventata bella", "Bellazio", commentiService.getCommenti()),
            new Progetto("2","Ricerca sul citoplasma","cellule")
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
    public void modificaTask(String id, Task task) {
        for(int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            if (t.getId().equals(id)) {
                tasks.set(i, task);
                return;
            }
        }
    }
}
