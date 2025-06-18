import React, {useEffect, useState} from 'react';
import './App.css';

import {Task, TaskCreateRequest} from './types';
import {createTask, deleteTask, getTasks, updateTask} from './api/tasks';
import TaskForm from './components/TaskForm';
import TaskList from "./components/TaskList";

function App() {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [editingTask, setEditingTask] = useState<Task | null>(null);

    useEffect(() => {
        load();
    }, []);

    function load() {
        getTasks().then(setTasks);
    }

    function handleSave(taskData: TaskCreateRequest, id?: number) {
        (id
                ? updateTask(id, taskData)
                : createTask(taskData)
        ).then(() => {
            setEditingTask(null);
            load();
        });
    }

    function handleEdit(task: Task) {
        setEditingTask(task);
    }

    function handleDelete(id: number) {
        deleteTask(id).then(load);
    }

    return (
        <div className="container">
            <h1>Task Manager</h1>
            <TaskForm onSave={handleSave} editingTask={editingTask}/>
            <TaskList tasks={tasks} onEdit={handleEdit} onDelete={handleDelete}/>
        </div>
    );
}

export default App;
