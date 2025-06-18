export interface Task {
    id: number;
    description: string;
    completed: boolean;
}

export interface TaskCreateRequest {
    description: string;
    completed: boolean;
}
