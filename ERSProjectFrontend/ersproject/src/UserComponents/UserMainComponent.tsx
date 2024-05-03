import { UserData } from "./UserData"
import axios from "axios"
import "./User.css"
import { UserInterface } from "../Interfaces/UserInterface"
import { useEffect, useState } from "react"

export const UserMainComponent: React.FC = () => {


    const[users, setUsers] = useState([])

    const fetchUsers = async () => {
        const response = axios.get("http://localhost:8080/Users", {withCredentials:true})
        .then((response) => {setUsers(response.data)})
    }

    useEffect(() => {
        fetchUsers()
    }, [])

    return(
        <div className="MainContentDiv">
            <button id="Refresh" onClick={fetchUsers}  className="tinySubButton">Refresh Users List</button>
            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Username</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {/* Here is where we create a new UserData component for each user in the fetched array*/}
                    {users.map((user:any) => {return(<UserData {...user} key={user.userID} fetchUsers={fetchUsers}/>)})}
                </tbody>
            </table>
        </div>
    )
}