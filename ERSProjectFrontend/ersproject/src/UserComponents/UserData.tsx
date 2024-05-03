import axios from "axios"
import { useEffect, useState } from "react"
import { json } from "stream/consumers"
import { UserInterface } from "../Interfaces/UserInterface"
import { state } from "../Global/store"


export const UserData: React.FC<any> = (user:any) => {


    

    const[chosenAction, setAction] = useState("")
    let radioName = "UserAction" + user.userID

    const storeAction = (input:any) => {
        if(input.target.id === "Promote"){
            setAction("Promote")
        }else{
            setAction("Fire")
        }
    }

    

    const PromoteUser = async() => {
            
            const response = axios.patch("http://localhost:8080/Users/Promote/" + user.userID, "Manager", {headers: {"Content-Type": "text/plain"}, withCredentials:true})
            .then(() => {user.fetchUsers()})
            .then(() => {setAction("")})
            .then(() => alert("User " + user.firstName + " Promoted"))
            .catch((error) => {alert(error)})
            //run the html promote command then refresh the page
            
    }

    const FireUser = async() => {
            const response = axios.delete("http://localhost:8080/Users/" + user.userID, {withCredentials:true})
            .then(() => {user.fetchUsers()})
            .then(() => {setAction("")})
            .then(() => alert("User " + user.firstName + " Fired"))
            .catch((error) => {alert(error)})
            //run the html delete user command then refresh the page
    }

    return(

        <tr>
            <td>
                {user.userID}
            </td>
            <td>
                {user.firstName}
            </td>
            <td>
                {user.lastName}
            </td>
            <td>
                {user.userEmail}
            </td>
            <td>
                {user.userRole}
            </td>
            <td>
                {user.userName}
            </td>
            <td>
                <div>
                    {user.userRole === "Employee" ? <input type="radio" id="Promote" name={radioName} value="Promote" onClick={storeAction} className="ManagerAction"/> : ""}
                    {user.userRole === "Employee" ? "Promote" : ""}
                    {!(user.userName === state.userSessionData.userName) ? <input type="radio" id="Fire" name={radioName} value="Fire" onClick={storeAction} className="ManagerAction"/>: ""}
                    {!(user.userName === state.userSessionData.userName) ? "Fire": ""}
                </div>
                {chosenAction === "Promote" ? <button onClick={PromoteUser}>Submit</button> : ""}
                {chosenAction === "Fire" ? <button onClick={FireUser}>Submit</button> : ""}
            </td>
        </tr>

    )
}