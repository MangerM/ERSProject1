import { useState } from "react"
import "./Reimbursement.css"
import { ReimbursementAll } from "./ReimbursementAll"
import { ReimbursementPending } from "./ReimbursementPending"
import { NewReimbursementComponent } from "./NewReimbursementComponent"

export const ReimbursementMainComponent: React.FC = () => {

    const[reimbFocus, updateFocus] = useState("")

    const showAll = () => {
        updateFocus("All")
    }
    
    const showPending = () => {
        updateFocus("Pending")
    }

    const showNew = () => {
        updateFocus("New")
    }

    return(
        <div className="MainContentDiv">

            {/** Need 3 buttons for reimbursements.  View all, View Pending, create new.  Under View all and View Pending need to include ability to change
             * description and clone existing request/resubmit request.
             */}
             <button onClick={showAll} className="subButtons">View All Reimbursements</button>
             <button onClick={showPending} className="subButtons">View All Pending Reimbursements</button>
             <button onClick={showNew} className="subButtons">Create a new Reimbursement</button>

             {reimbFocus === "All" ? <ReimbursementAll />:""}
             {reimbFocus === "Pending" ? <ReimbursementPending />:""}
             {reimbFocus === "New" ? <NewReimbursementComponent /> :""}

            
        </div>
    )
}