import React, { useState, useEffect } from 'react';
import { Task, TaskCreateRequest } from '../types';

interface Props {
    onSave: (task: TaskCreateRequest, id?: number) => void;
    editingTask?: Task | null;
}

const TaskForm: React.FC<Props> = ({ onSave, editingTask }) => {
    const [description, setDescription] = useState('');
    const [completed, setCompleted] = useState(false);

    useEffect(() => {
        if (editingTask) {
            setDescription(editingTask.description);
            setCompleted(editingTask.completed);
        } else {
            setDescription('');
            setCompleted(false);
        }
    }, [editingTask]);

    function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        onSave({ description, completed }, editingTask?.id);
    }

    return (
        <form onSubmit={handleSubmit}>
            <input value={description} onChange={e => setDescription(e.target.value)} placeholder="Description" required />
            <label>
                <input type="checkbox" checked={completed} onChange={e => setCompleted(e.target.checked)} />
                Completed
            </label>
            <button type="submit">{editingTask ? 'Update' : 'Create'}</button>
        </form>
    );
};

export default TaskForm;
