import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, RouterProvider, Routes, useNavigate } from 'react-router-dom';
import { LoginComponent } from './GenericComponents/LoginComponent';
import { ManagerMainComponent } from './ManagerComponents/ManagerMainComponent';
import { EmployeeMainComponent } from './EmployeeComponents/EmployeeMainComponent';
import { SplashComponent } from './GenericComponents/SplashComponent';

function App() {

  

  return (
  <div className='MainBody'>

    <div className='MainHeader'>
      <BrowserRouter>
        <Routes>
          <Route path='' element={<SplashComponent />}></Route>
          <Route path='/Managers' element={<ManagerMainComponent />}></Route>
          <Route path='/Employees' element={<EmployeeMainComponent />}></Route>
        </Routes>
      </BrowserRouter>
    </div>

    




    </div>



    /* This is where we include the router linking to login information, reimbursement, and user information.  Likely Login and reimbursement will be here and manager component will be included too */
  )
}

export default App;
