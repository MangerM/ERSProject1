export const UserMainComponent: React.FC = () => {


    return(
        <div>
            <table>
                <thead>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Username</th>
                    <th>Password</th>
                </thead>
                {/* Here is where we create a new UserData component for each user in the fetched array*/}
            </table>
        </div>
    )
}