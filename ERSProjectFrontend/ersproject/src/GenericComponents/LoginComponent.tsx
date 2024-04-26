import "./Login.css"

export const LoginComponent: React.FC = () => {

    return(
        <div className="LoginDiv">
            <div className="input-container">
                <input type="text" name="username" placeholder="username" className="LoginInput"/>
            </div>
            <div className="input-container">
                <input type="password" name="password" placeholder="password" className="LoginInput"/>
            </div>
            <div className="loginButtonDiv">
                <button className="loginButton">Login</button>
            </div>
        </div>
    )
}