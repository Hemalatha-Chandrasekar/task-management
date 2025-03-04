package com.example.task_management.Controller;

import com.example.task_management.model.Task;
import com.example.task_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {


    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

//    @GetMapping("/{id}")
//    public Task getTaskById(@PathVariable Long id) {
//        return taskService.getTaskById(id).orElseThrow(() -> new RuntimeException("Task not found"));
//    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

//    @PutMapping("/{id}")
//    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
//        return taskService.updateTask(id, updatedTask);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable Long id) {
//        taskService.deleteTask(id);
//    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/tasks/{id}")
        public ResponseEntity<Void> deleteTask (@PathVariable Long id){
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
    }
}
