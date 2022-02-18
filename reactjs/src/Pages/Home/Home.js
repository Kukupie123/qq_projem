import {useEffect} from "react";
import {useNavigate} from "react-router";
import {doesCookieExist} from "../../Services/CookieService";
import Header from "../components/header";
import Image from "../../assets/bg.jpg"
//Styles
import {Button, makeStyles} from "@material-ui/core";
import {Input} from "@mui/material";

const styles = makeStyles(() => ({
    root: {
        backgroundImage: `url(${Image})`,
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        display: "flex",
        flexDirection: "column",
        height: "100vh",
        justifyContent:"end",
        alignContent:"space-between",
        width: "100vw",

    },

    button: {

        width: "100%",
        height:"10%",
        display: "flex",
        justifyContent: "center",
        alignContent: "center",
        margin: "10px 10px 10px 10px",
        background: "rgba(255 255 255 0.4f)",
    },

    inputDiv:{
        width:"50%",
        display:"flex",
        flexDirection:"column",
        background:"white",
        padding:"10px 10px 10px 10px",
        margin:"10px 10px 10px 10px"
    }
}))

export default function () {
    const style = styles()
    const nav = useNavigate()
    useEffect(() => {
        if (doesCookieExist('jwt') == false) {
            nav('/')
        }
    })
    return (
        <div className = {style.root}>
            <Header showDesc = {false}/>
            <CreateField/>
            <Button type = "button" variant = "contained" className = {style.button}>Create a new Project</Button>
            <Button type = "button" variant = "contained" className = {style.button}>View Projects</Button>

        </div>
    )
}

let CreateField = ()=>{
    return (
       <div className={styles().inputDiv}>
           <Input placeholder="Title"/>
           <Input placeholder="Description"/>
       </div>
    )
}
