import { User } from "./user.model";

export interface Feedback {
    id: number;
    user: User;
    eventId: number;
    rating: number;
}