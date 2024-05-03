import { useState } from "react"
import { OutgoingReimbursementInterface } from "../Interfaces/OutgoingReimbursementInterface"
import axios from "axios"

export const NewReimbursementComponent: React.FC = () => {


    const [newReimb, setReimb] = useState<OutgoingReimbursementInterface>({
        reimbDesc:"",
        reimbAmount:0
    })

    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

        if(input.target.name === "description"){
            setReimb((newReimb) => ({...newReimb, reimbDesc:input.target.value}))
        } else {
            setReimb((newReimb) => ({...newReimb, reimbAmount:input.target.value}))
        }
        

    }

    const Submit = () => {
        const response = axios.post("http://localhost:8080/Reimbursement", newReimb, {withCredentials:true})
        .then((response) => alert(response.data))
        .catch((error) => alert(error.response.data))
        
    }


    return(
        <div className="MainContentDiv">
            <div className="inputField">
            <div className="ReimbursementInputDesc">
                <h3>Reimbursement Description</h3>
                <textarea name="description" placeholder="Enter a description of the reimbursement" onChange={storeValues}></textarea>
            </div>
            <div className="ReimbursementInputAmount">
                <h3>Reimbursement Amount</h3>
                <input type="number" name="amount" onChange={storeValues}></input>
            </div>
            </div>
            {newReimb.reimbAmount && newReimb.reimbDesc ? <button onClick={Submit} className="subButtons">Submit</button> : ""}
        </div>
    )
}