import { useEffect, useState } from "react"
import { LoginComponent } from "./LoginComponent"
import { NewEmployeeComponent } from "./NewEmployeeComponent"
import { state } from "../Global/store"
import { useNavigate } from "react-router-dom"

export const SplashComponent: React.FC = () => {


    const[reimbFocus, updateFocus] = useState("Login")

    const loginFocus = () => {
        updateFocus("Login")
    }
    const newEmployeeFocus = () => {
        updateFocus("New")
    }

    

    const navigate = useNavigate()


    if(state.userSessionData.userRole === "Manager"){
        return(
            <div  className="MainContentDiv">
                <h1>Welcome Manager {state.userSessionData.userName} please visit the Manager page</h1>
                <button onClick={()=>navigate("/Managers")}>Manager Page</button>
            </div>
        )
    }
    else if(state.userSessionData.userRole === "Employee"){
        return(
            <div  className="MainContentDiv">
                <h1>Welcome Employee {state.userSessionData.userName} please visit the Employee page</h1>
                <button onClick={()=>navigate("/Employees")}>Employee Page</button>
            </div>
        )
    }
    else{

   
    return(
        <div  className="MainContentDiv">
            <h3 id='PageTitle'>Welcome to the Employee Reimbursement System</h3>
            <div>
                <button onClick={loginFocus} className="PrimaryButton">Switch to login</button>
                <button onClick={newEmployeeFocus} className="PrimaryButton">Create a new account</button>
            </div>
            


            {reimbFocus === "Login" ? <LoginComponent />:""}
            {reimbFocus === "New" ? <NewEmployeeComponent refocus={loginFocus}/>:""}

        </div>
    )
    }
}