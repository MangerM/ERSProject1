import { useState } from "react"
import { state } from "../Global/store"
import axios from "axios"

export const ReimbursementData: React.FC<any> = (Reimbursement:any) => {

    const[chosenAction, setAction] = useState("")

    const storeAction = (input:any) => {
        if(input.target.id === "Approve"){
            setAction("Approve")
        }else{
            setAction("Deny")
        }
    }

    const [newDesc, setNewDesc] = useState("")

    const setDesc = (input:any) => {
        setNewDesc(input.target.value)
    }

    const Approve = async() => {
            
            const response = axios.patch("http://localhost:8080/Reimbursement/Resolve/" + Reimbursement.reimbID, "Approved", {headers: {"Content-Type": "text/plain"}, withCredentials:true})
            .then(() => {Reimbursement.fetchReimbs()})
            .then(() => {setAction("")})
            .then(() => alert("Reimbursement " + Reimbursement.reimbID + " Approved"))
            .catch((error) => {alert(error.response.data)})
            //run the html resolve command then refresh the page
            
    }

    const Deny = async() => {
            
            const response = axios.patch("http://localhost:8080/Reimbursement/Resolve/" + Reimbursement.reimbID, "Denied", {headers: {"Content-Type": "text/plain"}, withCredentials:true})
            .then(() => {Reimbursement.fetchReimbs()})
            .then(() => {setAction("")})
            .then(() => alert("Reimbursement " + Reimbursement.reimbID + " Denied"))
            .catch((error) => {alert(error.response.data)})
            //run the html resolve command then refresh the page
            
    }

    const Clone = async () => {

        const response = axios.post("http://localhost:8080/Reimbursement/Renew/" + Reimbursement.reimbID, "", {withCredentials:true})
        .then((response) => {alert(response.data)})
        .then(() => {Reimbursement.fetchReimbs()})
        .catch((error) => {alert(error.response.data)})

    }

    const updateDesc = async () => {
        const response = axios.patch("http://localhost:8080/Reimbursement/" + Reimbursement.reimbID, newDesc, {headers: {"Content-Type": "text/plain"}, withCredentials:true})
        .then((response) => {alert(response.data)})
        .then(() => {Reimbursement.fetchReimbs()})
        .catch((error) => {alert(error.response.data)})
    }

    const optionName = "State" + Reimbursement.reimbID

    return(

        <tr>
            <td>{Reimbursement.reimbID}</td>
            <td>{Reimbursement.reimbDesc}</td>
            <td>{Reimbursement.reimbAmount}</td>
            <td>{Reimbursement.reimbStatus}</td>
            {state.userSessionData.userRole === "Manager" ? <td>{Reimbursement.reimbUserID}</td> : ""}
            {state.userSessionData.userRole === "Manager" ? <td>{Reimbursement.reimbUserName}</td> : ""}
            <td>

                {/** We have two different actions that anyone can do, if pending they can edit the description (and they are the user who submitted)
                 * 
                 * a user can "clone" a request re-submitting it
                 * 
                 * and managers can resolve pending requests
                 */}

                {state.userSessionData.userRole === "Manager" && Reimbursement.reimbStatus === "Pending" && !(state.userSessionData.userName === Reimbursement.reimbUserName) ? <div className="managerActions">
                    <div className="ManagerDiv">
                        {state.userSessionData.userRole === "Manager" && Reimbursement.reimbStatus === "Pending" && !(state.userSessionData.userName === Reimbursement.reimbUserName) ? 
                            <input type="radio" name={optionName} id="Approve" onClick={storeAction} className="ManagerAction"/>: ""}
                        {state.userSessionData.userRole === "Manager" && Reimbursement.reimbStatus === "Pending"  && !(state.userSessionData.userName === Reimbursement.reimbUserName) ? 
                            "Approve     ": ""}
                    </div>
                
                    <div className="ManagerDiv">
                        {state.userSessionData.userRole === "Manager" && Reimbursement.reimbStatus === "Pending" && !(state.userSessionData.userName === Reimbursement.reimbUserName) ? 
                            <input type="radio" name={optionName} id="Deny" onClick={storeAction} className="ManagerAction"/>: ""}
                        {state.userSessionData.userRole === "Manager" && Reimbursement.reimbStatus === "Pending"  && !(state.userSessionData.userName === Reimbursement.reimbUserName) ? 
                            "Deny": ""}
                    </div>
                    <div className="ManagerDiv">
                        {chosenAction === "Approve"  && !(state.userSessionData.userName === Reimbursement.reimbUserName)? <button onClick={Approve} className="ManagerSubmit">Submit</button> : ""}
                        {chosenAction === "Deny"  && !(state.userSessionData.userName === Reimbursement.reimbUserName)? <button onClick={Deny} className="ManagerSubmit">Submit</button> : ""}
                    </div>
                </div> : ""}

                <div className="Renewal">
                    {state.userSessionData.userName === Reimbursement.reimbUserName ? <button onClick={Clone} className="RenewalSubmit">Submit for Renewal</button> : ""}
                </div>

                <div className="Updating">
                    {state.userSessionData.userName === Reimbursement.reimbUserName && Reimbursement.reimbStatus === "Pending" ?
                        <textarea name="newDesc" placeholder="New Description" onChange={setDesc}></textarea> : ""}
                    {state.userSessionData.userName === Reimbursement.reimbUserName && Reimbursement.reimbStatus === "Pending" ?
                        <button onClick={updateDesc} className="UpdateSubmit">Update Desc</button> : ""}
                </div>

                

                

            </td>
        </tr>
    )
}