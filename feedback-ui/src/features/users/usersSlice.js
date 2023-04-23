import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const USERS_URL = 'http://localhost:7070/api/account/v1/users/';

const initialState = {
    users: [],
    currentUser: null,
    directReporting: null,
    searchSubjectUsers: [],
    searchActorUsers: []
}

export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
    const response = await axios.get(USERS_URL);
    return response.data.users
})

export const searchSubjectUsers = createAsyncThunk('users/searchSubject', async (searchText) => {
    const response = await axios.get(USERS_URL + "search?searchText="+ searchText);
    return response.data.users
})

export const searchActorUsers = createAsyncThunk('users/searchActor', async (searchText) => {
    const response = await axios.get(USERS_URL + "search?searchText="+ searchText);
    return response.data.users
})

export const fetchDirectReportings = createAsyncThunk('users/getDirectReportings', async (managerId) => {
    const response = await axios.get(USERS_URL + managerId + "/directReporting");
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
        },
        clearSubjectSearch: {
            reducer(state, action) {
                state.searchSubjectUsers = []
            }
        },
        clearActorSearch: {
            reducer(state, action) {
                state.searchActorUsers = []
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
        .addCase(searchSubjectUsers.fulfilled, (state, action) => {
            state.searchSubjectUsers = action.payload;
        })
        .addCase(searchActorUsers.fulfilled, (state, action) => {
            state.searchActorUsers = action.payload;
        })
    }
})

export const selectAllUsers = (state) => state.users.users;

export const selectSubjectSearchUsers = (state) => state.users.searchSubjectUsers;

export const selectActorSearchUsers = (state) => state.users.searchActorUsers;

export const { addCurrentUser, clearSubjectSearch, clearActorSearch } = usersSlice.actions;

export const getCurrentUser = (state) => state.users.currentUser;

export const getDirectReportings = (state) => state.users.directReporting;

export default usersSlice.reducer