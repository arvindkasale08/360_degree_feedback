import Error from "./components/Error";
import { Routes, Route } from 'react-router-dom';


function App() {
  return (
    <Routes>
      <Route path="*" element={<Error />} />
    </Routes>
  );
}

export default App;
