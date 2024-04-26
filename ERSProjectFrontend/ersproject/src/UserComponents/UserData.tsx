export const UserData: React.FC<any> = (user:any) => {



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
                {user.email}
            </td>
            <td>
                {user.role}
            </td>
            <td>
                {user.username}
            </td>
            <td>
                {user.password}
            </td>
        </tr>

    )
}