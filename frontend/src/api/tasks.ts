import { Task, TaskCreateRequest } from '../types';

const API_URL = process.env.REACT_APP_API_URL || '';

export async function getTasks(): Promise<Task[]> {
    const res = await fetch(`${API_URL}/tasks`);
    return res.json();
}

export async function createTask(task: TaskCreateRequest): Promise<Task> {
    const res = await fetch(`${API_URL}/tasks`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(task)
    });
    return res.json();
}

export async function updateTask(id: number, task: TaskCreateRequest): Promise<Task> {
    const res = await fetch(`${API_URL}/tasks/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(task)
    });
    return res.json();
}

export async function deleteTask(id: number): Promise<void> {
    await fetch(`${API_URL}/tasks/${id}`, { method: 'DELETE' });
}
