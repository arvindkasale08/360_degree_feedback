import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const FEEDBACKS_URL = 'http://localhost:8080/api/review/v1/feedback';

const initialState = {
    myFeedbacks: [],
    assignedFeedbacks: [],
    directReportingFeedbacks: []
}

export const fetchMyFeedbacks = createAsyncThunk('feedback/fetchMyFeedbacks', async (id) => {
    const response = await axios.get(FEEDBACKS_URL + "/me/" + id);
    return response.data.feedbacks
})

const feedbackSlice = createSlice({
    name: 'feedbacks',
    initialState,
    reducers: {
    },
    extraReducers(builder) {
        builder
        .addCase(fetchMyFeedbacks.fulfilled, (state, action) => {
            state.myFeedbacks = action.payload;
        })
    }
})

export const selectMyFeedbacks = (state) => state.feedbacks.myFeedbacks;

export default feedbackSlice.reducer