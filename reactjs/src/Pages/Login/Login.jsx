//components
import "./login.css"
import Input from "../components/input";
import {Button} from "react-bootstrap";
//css
import "bootstrap/dist/css/bootstrap.css"
import {makeStyles} from "@material-ui/core";
//Service
import {SignIn} from "../../Services/Service_SignUpIn"

//variables
let email = null;
let password = null;

//functions
function handleEmail(event) {
    console.log(event.target.value)
    email = event.target.value;
}

function handlePassword(event) {
    password = event.target.value;
    console.log(password)
}

//Styles
const styles = makeStyles(() => ({
    root: {
        height: "100vh",
        minHeight: "100vh",
        width: "100vw",
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-between",
        alignContent: "center"
    },
    button: {
        color: "white",

    }
}));

const signInStyles = makeStyles(() => ({
    blurRoot: {
        height: "60%",
        display: "flex",
        alignItems: "end",
        justifyContent: "end",
        flexDirection: "column",
        background: "rgba(152, 122, 229, 0.15)",
        boxShadow: "0px 8px 32px rgba(25, 30, 76, 0.86)",
        backdropFilter: "blur(3.5px)",
        borderRadius: "10px",
    },
    inputContainer: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        height: "100%",
        width: "100%",
    }
}))

const Login = () => {
    const styles = signInStyles();
    return (
        <div className = {styles.blurRoot}>
            <div className = {styles.inputContainer}>
                <Input type = "email" placeholder = "EMAIL" onValueChange = {handleEmail}/>
                <Input type = "password" placeholder = "PASSWORD" onValueChange = {handlePassword}/>

                <div>
                    <Button onClick = {() => {
                        SignIn(email, password)
                    }}>Login</Button>
                </div>
            </div>

        </div>
    );
}

const Signup = () => {
    const styles = signInStyles();
    return (
        <div className = {styles.blurRoot}>
            <div className = {styles.inputContainer}>
                <Input type = "email" placeholder = "EMAIL"/>
                <Input type = "password" placeholder = "PASSWORD"/>

                <div>
                    <Button>Signup</Button>
                </div>
            </div>


        </div>
    );
}


export default function GG() {
    const classes = styles();

    return (
        <div className = {classes.root} id = "login-signup">
            <Login/>
            <Signup/>
        </div>
    )
}

