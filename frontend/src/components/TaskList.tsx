import React from 'react';
import { Task } from '../types';

interface TaskListProps {
    tasks: Task[];
    onEdit: (task: Task) => void;
    onDelete: (id: number) => void;
}

const TaskList: React.FC<TaskListProps> = ({ tasks, onEdit, onDelete }) => (
    <ul>
        {tasks.map(task => (
            <li key={task.id}>
                <span className={task.completed ? 'done' : 'notdone'}>
                    {task.description} [{task.completed ? "Done" : "Not done"}]
                </span>
                <button onClick={() => onEdit(task)}>Edit</button>
                <button onClick={() => onDelete(task.id)}>Delete</button>
            </li>
        ))}
    </ul>
);

export default TaskList;
