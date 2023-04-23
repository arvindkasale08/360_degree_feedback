import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const FEEDBACKS_URL = 'http://localhost:8080/api/review/v1/feedback';

const initialState = {
    myFeedbacks: [],
    assignedFeedbacks: [],
    reportingFeedbacks: []
}

export const fetchMyFeedbacks = createAsyncThunk('feedback/fetchMyFeedbacks', async (id) => {
    const response = await axios.get(FEEDBACKS_URL + "/me/" + id);
    return response.data.feedbacks
})

export const fetchReportingFeedbacks = createAsyncThunk('feedback/directReporting', async (id) => {
    const response = await axios.get(FEEDBACKS_URL + "/directReporting/" + id);
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
        .addCase(fetchReportingFeedbacks.fulfilled, (state, action) => {
            state.reportingFeedbacks = action.payload;
        })
    }
})

export const selectMyFeedbacks = (state) => state.feedbacks.myFeedbacks;

export const selectReportingFeedbacks = (state) => state.feedbacks.reportingFeedbacks;

export const selectFeedbackById = (state, id) => state.feedbacks.myFeedbacks.find(feedback => feedback.id === id);

export const selectReportingFeedbackById = (state, id) => state.feedbacks.reportingFeedbacks.find(feedback => feedback.id === id);

export default feedbackSlice.reducer