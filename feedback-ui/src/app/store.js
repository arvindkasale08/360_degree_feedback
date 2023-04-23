import { configureStore } from "@reduxjs/toolkit";
import usersReducer from '../features/users/usersSlice';
import feedbacksReducer from '../features/feedbacks/feedbackSlice';


export const store = configureStore({
    reducer: {
        users: usersReducer,
        feedbacks: feedbacksReducer 
    }
})