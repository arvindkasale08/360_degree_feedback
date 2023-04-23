import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const USERS_URL = 'http://localhost:7070/api/account/v1/users/';

const initialState = {
    users: [],
    currentUser: null,
    directReporting: null
}

export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
    const response = await axios.get(USERS_URL);
    return response.data.users
})

export const fetchDirectReportings = createAsyncThunk('users/getDirectReportings', async (managerId) => {
    const response = await axios.get("http://localhost:7070/api/account/v1/users/" + managerId + "/directReporting");
    return response.data.users
})

const usersSlice = createSlice({
    name: 'users',
    initialState,
    reducers: {
        addCurrentUser: {
            reducer(state, action) {
                state.currentUser = action.payload
            } 
        }
    },
    extraReducers(builder) {
        builder
        .addCase(fetchUsers.fulfilled, (state, action) => {
            state.users = action.payload;
        })
        .addCase(fetchDirectReportings.fulfilled, (state, action) => {
            state.directReporting = action.payload;
        })
    }
})

export const selectAllUsers = (state) => state.users.users;

export const { addCurrentUser } = usersSlice.actions;

export const getCurrentUser = (state) => state.users.currentUser;

export const getDirectReportings = (state) => state.users.directReporting;

export default usersSlice.reducer