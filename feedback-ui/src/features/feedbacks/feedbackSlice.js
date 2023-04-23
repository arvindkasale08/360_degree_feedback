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

export const fetchAssignedFeedbacks = createAsyncThunk('feedback/requested', async(id) => {
    const response = await axios.get(FEEDBACKS_URL + "/assignedToActor/" + id);
    return response.data.feedbacks
})

export const finalizeFeedback = createAsyncThunk('feedback/finalize', async({body, id}) => {
    console.log("In finalize feedback")
    const data = {
        "data": body
    }
    console.log("data is ", data)
    const response = await axios.post(FEEDBACKS_URL + "/" + id + "/finalize", data)
    return response.data
})

export const initializeFeedback = createAsyncThunk('feedback/initialize', async({actorId, requestorId, subjectId}) => {
    console.log("In Initialize Feedback")
    const data = {
        actorId,
        requestorId,
        subjectId
    }
    console.log(data)
    const response = await axios.post(FEEDBACKS_URL + "/initialize", data)
    return response.data
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
        .addCase(fetchAssignedFeedbacks.fulfilled, (state, action) => {
            state.assignedFeedbacks = action.payload;
        })
        .addCase(finalizeFeedback.fulfilled, (state, action) => {
            const id = action.payload.id;
            const assignedFeedbacks = state.assignedFeedbacks;
            const objWithIdIndex = assignedFeedbacks.findIndex((obj) => obj.id === id);

            if (objWithIdIndex > -1) {
                assignedFeedbacks.splice(objWithIdIndex, 1);
            }
            state.assignedFeedbacks = assignedFeedbacks;
        })
        .addCase(initializeFeedback.fulfilled, (state, action) => {
            // Do something later
        })
    }
})

export const selectMyFeedbacks = (state) => state.feedbacks.myFeedbacks;

export const selectReportingFeedbacks = (state) => state.feedbacks.reportingFeedbacks;

export const selectAssignedFeedbacks = (state) => state.feedbacks.assignedFeedbacks;

export const selectFeedbackById = (state, id) => state.feedbacks.myFeedbacks.find(feedback => feedback.id === id);

export const selectReportingFeedbackById = (state, id) => state.feedbacks.reportingFeedbacks.find(feedback => feedback.id === id);

export const selectAssignedFeedbackById = (state, id) => state.feedbacks.assignedFeedbacks.find(feedback => feedback.id === id);

export default feedbackSlice.reducer