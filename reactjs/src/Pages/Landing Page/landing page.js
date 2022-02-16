import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {CssBaseline} from "@material-ui/core";
import Header from "../components/header";

const useStyles = makeStyles((theme) => ({
    root :{
        minHeight: "100vh",
        backgroundImage : "bg.jpg",
        backgroundRepeat : "no-repeat",
        backgroundSize : "cover"

    }
}));
export default function LandingPage() {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <CssBaseline/>
            <Header/>

        </div>
    )
}

