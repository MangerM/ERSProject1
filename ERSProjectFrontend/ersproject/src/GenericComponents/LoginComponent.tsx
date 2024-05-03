import { useState } from "react"
import {LoginInterface} from "../Interfaces/LoginInterface"
import "./Login.css"
import axios from "axios"
import { useNavigate } from "react-router-dom"
import {state} from "../Global/store"

export const LoginComponent: React.FC = () => {

    const [user, setUser] = useState<LoginInterface>({
        userName:"",
        userPass:""
    })

    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

        if(input.target.name === "username"){
            setUser((user) => ({...user, userName:input.target.value}))
        } else {
            setUser((user) => ({...user, userPass:input.target.value}))
        }
        

    }

    const navigate = useNavigate();

    const login = async () => {

        
        const responseData = await axios.post("http://localhost:8080/Users/Login", user, {withCredentials:true})
        .then((responseData) => {

            state.userSessionData = responseData.data

            if(state.userSessionData.userRole === "Manager"){
                navigate("/Managers")
            }else{
                navigate("/Employees")
            }
            //use our useNavigate hook to switch views to the Catch Pokemon Component
            //if user is a manager then go to /manager else go to /user

            
        })
        .catch((error) => {
            alert(error.response.data)
        })

    }

    return(
            <div className="LoginDiv">
                <div>Username:&nbsp;&nbsp;&nbsp;<input type="text" name="username" placeholder="username" onChange={storeValues}/></div>
                <div>Password:&nbsp;&nbsp;&nbsp;<input type="password" name="password" placeholder="password" onChange={storeValues}/></div>
                <div className="loginButtonDiv">
                    {user.userName && user.userPass ? <button className="loginButton" onClick={login}>Login</button> : ""}
                </div>
            </div>
    )
}