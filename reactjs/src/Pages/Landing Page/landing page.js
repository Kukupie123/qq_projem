import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {CssBaseline} from "@material-ui/core";
import Header from "../components/header";

//Icon
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";

const useStyles = makeStyles((theme) => ({
    root: {
        minHeight: "100vh",
        backgroundImage: "bg.jpg",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignContent: "center"

    },
    desc: {
        paddingTop: "5rem",
        textAlign: "center"
    },

    text1: {
        flexGrow: "1",
        fontFamily: "Bebas Neue",
        fontSize: "5rem",
        color: "#27c433",
        border: "black",
        outline: "white",
        fontWeight: "bold",
    },

    text2: {
        flexGrow: "1",
        fontFamily: "Bebas Neue",
        fontSize:"3rem",
        color: "#27232f",
        border: "black",
        outline: "white"
    },
    text3: {
        flexGrow: "1",
        fontSize:"3rem",
        fontFamily: "Bebas Neue",
        color: "#c9a2ec",
        border: "black",
        outline: "white"
    },

    expand:{
        fontSize:"5rem",
        color: "#27c433",

    }


}));


function Desc() {
    const classes = useStyles();
    return <div className = {classes.desc}>
        <h1 className = {classes.text2}>Feature rich <br/>
            <span className = {[classes.text1]}> Project Management Application</span>
            <br/>
            <span className = {classes.text3}>
                                            for your team
                                       </span>
        </h1>
        <ExpandMoreIcon className={classes.expand}/>
    </div>
}

export default function LandingPage() {
    const classes = useStyles();
    return (
        <div className = {classes.root}>
            <CssBaseline/>
            <Header/>
            <Desc/>
        </div>
    )
}

