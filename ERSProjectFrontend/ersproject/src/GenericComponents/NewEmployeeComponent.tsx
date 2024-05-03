import { useState } from "react"
import {NewUserInterface} from "../Interfaces/NewUserInterface"
import axios from "axios"
import "./Login.css"

export const NewEmployeeComponent: React.FC<any> = (refocus:any) => {


    const [employee, setEmployee] = useState<NewUserInterface>({
        firstName:"",
        lastName:"",
        userName:"",
        userPass:"",
        userEmail:""
    })

    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

        if(input.target.name === "firstName"){
            setEmployee((employee) => ({...employee, firstName:input.target.value}))
        } 
        if(input.target.name === "lastName"){
            setEmployee((employee) => ({...employee, lastName:input.target.value}))
        } 
        if(input.target.name === "userName"){
            setEmployee((employee) => ({...employee, userName:input.target.value}))
        } 
        if(input.target.name === "userPass"){
            setEmployee((employee) => ({...employee, userPass:input.target.value}))
        } 
        if(input.target.name === "userEmail"){
            setEmployee((employee) => ({...employee, userEmail:input.target.value}))
        } 

    }

    const newEmployee = async () => {
        const result = await axios.post("http://localhost:8080/Users", employee, {withCredentials:true})
        .then((result) => alert(result.data))
        .then(()=>refocus.refocus())
        .catch((error) => alert(error.response.data))
    }

    return(
        <div>
            <div className="whole">
                <p>
                    First Name&nbsp;&nbsp;
                    <input type="text" name="firstName" onChange={storeValues} placeholder="First Name"></input>
                </p>
            </div>
            <div>
                <p>Last Name&nbsp;&nbsp;
                <input type="text" name="lastName" onChange={storeValues} placeholder="Last Name"></input></p>
            </div>
            <div>
                <p>Username&nbsp;&nbsp;
                <input type="text" name="userName" onChange={storeValues} placeholder="Username"></input></p>
            </div>
            <div>
                <p>Password&nbsp;&nbsp;
                <input type="password" name="userPass" onChange={storeValues} placeholder="Password"></input></p>
            </div>
            <div>
                <p>Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" name="userEmail" onChange={storeValues} placeholder="Email"></input></p>
            </div>
            
            <div className="loginButtonDiv">
                {employee.firstName && employee.lastName && employee.userEmail && employee.userName && employee.userPass ? <button onClick={newEmployee} className="loginButton">Create New Employee Account</button> : ""}
            </div>
        </div>
    )
}