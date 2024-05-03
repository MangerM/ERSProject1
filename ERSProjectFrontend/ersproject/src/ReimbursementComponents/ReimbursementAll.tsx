import axios from "axios"
import { useEffect, useState } from "react"
import { ReimbursementData } from "./ReimbursementData"
import { state } from "../Global/store"

export const ReimbursementAll: React.FC = () => {


    const[reimbursements, setReimbs] = useState([])

    const fetchReimbs = async () => {
        //Need to get current user ID to send for reimbursement list
        const response = axios.get("http://localhost:8080/Reimbursement", {withCredentials:true})
        .then((response) => {setReimbs(response.data)})
    }

    useEffect(() => {
        fetchReimbs()
    }, [])


    return(

        <div className="MainContentDiv">
            <button id="Refresh" onClick={fetchReimbs} className="tinySubButton">Refresh Reimbursement List</button>
            <table>
                <thead>
                    <tr>
                        <th>Reimbursement ID</th>
                        <th>Description</th>
                        <th>Amount in dollars</th>
                        <th>Status</th>
                        {state.userSessionData.userRole === "Manager" ? <th>Employee ID</th> : ""}
                        {state.userSessionData.userRole === "Manager" ? <th>Employee Username</th> : ""}
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {reimbursements.map((reimbursement:any) => {return(<ReimbursementData {...reimbursement} key={reimbursement.reimbID} fetchReimbs={fetchReimbs}/>)})}
                </tbody>
            </table>
        </div>
    )
}