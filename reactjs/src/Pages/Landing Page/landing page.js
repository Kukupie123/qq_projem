import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import Header from "../components/header";

//Icon
import Login from "../Login/Login";
import {CssBaseline} from "@mui/material";

const useStyles = makeStyles(() => ({
    root: {
        backgroundImage: "bg.jpg",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        display: "flex",
        flexDirection: "column",
        height: "100vh",
        minHeight: "100vh"

    },
    desc: {
        textAlign: "center"
    },

    text1: {
        flexGrow: "1",
        fontFamily: "Bebas Neue",
        fontSize: "5rem",
        color: "#ee6a6a",
        border: "black",
        outline: "white",
        fontWeight: "bold",
    },

    text2: {
        flexGrow: "1",
        fontFamily: "Bebas Neue",
        fontSize: "3rem",
        color: "#ffffff",
        border: "black",
        outline: "white"
    },
    text3: {
        flexGrow: "1",
        fontSize: "3rem",
        fontFamily: "Bebas Neue",
        color: "#ffffff",
        border: "black",
        outline: "white"
    },

    expand: {
        fontSize: "5rem",
        color: "#27c433",

    }


}));

export default function LandingPage() {

    const classes = useStyles();
    return (
        <div className = {classes.root}>
            <CssBaseline/>
            <Header/>
           <div>
               <Login/>
           </div>
        </div>
    )
}

