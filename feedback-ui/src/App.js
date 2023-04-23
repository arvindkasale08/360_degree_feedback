import MyFeedbackList from "./features/feedbacks/MyFeedbackList";
import MyFeedback from "./features/feedbacks/MyFeedback";
import ReportingFeedbackList from "./features/feedbacks/ReportingFeedbackList";
import ReportingFeedback from "./features/feedbacks/ReportingFeedback";
import Layout from "./components/Layout";
import Error from "./components/Error";
import { Routes, Route } from 'react-router-dom';


function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element= {<MyFeedbackList />}/>
        <Route path="myFeedback" element={<MyFeedbackList />}/>
        <Route path="myFeedback/:feedbackId" element={<MyFeedback />}/>
        <Route path="reportingFeedback" element={<ReportingFeedbackList />}/>
        <Route path="reportingFeedback/:feedbackId" element={<ReportingFeedback />}/>
      </Route>
      
      <Route path="*" element={<Error />} />
    </Routes>
  );
}

export default App;
