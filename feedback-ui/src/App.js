import MyFeedbackList from "./features/feedbacks/MyFeedbackList";
import Layout from "./components/Layout";
import Error from "./components/Error";
import { Routes, Route } from 'react-router-dom';


function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element= {<MyFeedbackList />}/>
      </Route>
      <Route path="*" element={<Error />} />
    </Routes>
  );
}

export default App;
