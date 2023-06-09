package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Transactional
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private DbService dbService;

    @Test
    public void getAllTasksTest(){
        // Given
        Task task = new Task(1L,"Test", "Testing");
        List<Task> taskList = Arrays.asList(task);
        when(dbService.getAllTasks()).thenReturn(taskList);
        // When
        List<Task> resultList = dbService.getAllTasks();
        // Then
        assertEquals(1, resultList.size());
        assertEquals("Test", resultList.get(0).getTitle());
        assertEquals("Testing", resultList.get(0).getContent());
        assertNotNull(resultList);
    }

    @Test
    public void findByIdTest() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L,"Test", "Testing");
        when(dbService.getTask(task.getId())).thenReturn(task);
        // When & Then
        assertDoesNotThrow(() -> dbService.getTask(task.getId()));
        assertDoesNotThrow(() -> dbService.getTask(null));
    }

    @Test
    public void saveTaskTest() {
        // Given
        Task task = new Task(1L,"Test", "Testing");
        when(dbService.saveTask(task)).thenReturn(task);
        // When
        Task taskReceived = dbService.saveTask(task);
        // Then
        assertEquals(1L, taskReceived.getId());
        assertEquals("Test", taskReceived.getTitle());
        assertEquals("Testing", taskReceived.getContent());
    }
}