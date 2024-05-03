import { useNavigate } from "react-router-dom"
import { state } from "../Global/store"
import { ReimbursementMainComponent } from "../ReimbursementComponents/ReimbursementMainComponent"

export const EmployeeMainComponent: React.FC = () => {

    const navigate = useNavigate()
    
    const logout = () => {
        state.userSessionData.userName = ""
        state.userSessionData.userRole = ""
        navigate("/")
    }

    if(state.userSessionData.userRole === "Employee"){

    
    return(
        <div className="MainContentDiv">
            <div>
                    <button className='LogoutButton' onClick={logout}>Logout</button>
            </div>
            
            <h1>Welcome Employee {state.userSessionData.userName}</h1>
            <br />
            <ReimbursementMainComponent />
        </div>
    )

    }
    else if(state.userSessionData.userRole === "Manager"){
        return(
            <div className="MainContentDiv">
                <div>
                    <button className='LogoutButton' onClick={logout}>Logout</button>
                </div>
                <h1>Welcome Manager.  You should be on the manager page, please go there.</h1>
                <button onClick={()=>navigate("/Managers")} className="PrimaryButton">Manager Page</button>
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