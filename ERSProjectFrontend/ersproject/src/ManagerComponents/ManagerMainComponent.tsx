import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom"
import { UserMainComponent } from "../UserComponents/UserMainComponent"
import { ReimbursementMainComponent } from "../ReimbursementComponents/ReimbursementMainComponent"
import { useState } from "react"
import "./ManagerCSS.css"
import { state } from "../Global/store"

export const ManagerMainComponent: React.FC = () => {


    const navigate = useNavigate()

    const[usersOrReimb, updateShown] = useState("")


    const showUsers = () => {
        updateShown("Users")
    }

    const showReimb = () => {
        updateShown("Reimbursement")
    }

    const logout = () => {
        state.userSessionData.userName = ""
        state.userSessionData.userRole = ""
        navigate("/")
    }

    if(state.userSessionData.userRole === "Manager"){
    return(

        <div className="MainContentDiv">

            <div>
                    <button className='LogoutButton' onClick={logout}>Logout</button>
            </div>

        <h1>Welcome Manager {state.userSessionData.userName}.  Please select if you wish to view User content or Reimbursement content.</h1>
        <br />
        <button onClick={showUsers} className="PrimaryButton">User Details</button>
        <button onClick={showReimb} className="PrimaryButton">Reimbursement Details</button>

        {usersOrReimb === "Users" ? <UserMainComponent />: ""}
        {usersOrReimb === "Reimbursement" ? <ReimbursementMainComponent />: ""}

        </div>

    )}
    else if(state.userSessionData.userRole === "Employee"){
        return(
            <div className="MainContentDiv">
                <div>
                    <button className='LogoutButton' onClick={logout}>Logout</button>
                </div>
                    <h1>You shouldn't be here if you are not a manager.  Please login as a manager or go to the employee section.</h1>
                    <button onClick={()=>navigate("/Employees")} className="PrimaryButton">Return To Employee Section</button>
            </div>
        )
    }
    else{
        return(
            <div className="MainContentDiv">
                <h1>You shouldn't be here if you are not signed in.  Please return to the main page.</h1>
                <button onClick={()=>navigate("/")} className="PrimaryButton">Return To Main Page</button>
            </div>
        )
    }

}