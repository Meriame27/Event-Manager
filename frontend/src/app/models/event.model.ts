import { User } from "./user.model";

export interface Event {
    id?: number;
    name: string;
    date: string; 
    time: string;
    category: string;
    organizerId: number;
    isRegistered: boolean;
}
