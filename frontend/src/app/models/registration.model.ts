import { User } from "./user.model";

export interface Registration {
    id: number;
    user: User;
    eventId: number;
}