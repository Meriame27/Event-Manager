import { User } from "./user.model";

export interface Feedback {
    id?: number;
    userId: number;
    eventId: number|undefined;
    rating: number;
}