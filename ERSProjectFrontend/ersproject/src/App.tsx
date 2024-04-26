import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, RouterProvider, Routes } from 'react-router-dom';
import { LoginComponent } from './GenericComponents/LoginComponent';

function App() {
  return (
  <div className='MainBody'>

    <div className='MainHeader'>
      <h3 id='PageTitle'>Employee Reimbursement System</h3>
      <BrowserRouter>
        <Routes>
          <Route path='' element={<LoginComponent />}></Route>
        </Routes>
      </BrowserRouter>
    </div>

    




    </div>



    /* This is where we include the router linking to login information, reimbursement, and user information.  Likely Login and reimbursement will be here and manager component will be included too */
  )
}

export default App;
