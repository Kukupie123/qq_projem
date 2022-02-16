import {AppBar, Collapse, Dialog, IconButton, makeStyles, Toolbar} from "@material-ui/core";
import {SortSharp} from "@material-ui/icons";
import React, {useEffect, useState} from "react";
import {Desc} from "../Landing Page/landing page";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import Login from "../Login/Login";
import {Link} from "react-scroll"

const useStyles = makeStyles((theme) => ({

    appBar: {
        background: "none",
    },
    root: {
        minHeight: "100vh",
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignContent: "center",
        flexDirection: "column"
    },

    appBarWrapper: {
        width: "80%",
        margin: "0 auto"
    },

    title: {
        flexGrow: "1",
        fontFamily: "fantasy",
        color: "#27c433"

    },

    sort: {
        color: "whitesmoke",
        fontSize: "2rem"
    },


    gem: {
        color: "#5db3e7"
    }
}));
const useStyles2 = makeStyles((theme) => ({
    root: {
        backgroundImage: "bg.jpg",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignContent: "center",
        width: "100vw",
        height: "100vh"

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


export default function Header() {
    const classes = useStyles();
    const classesD = useStyles2();
//set state objects
    const [checked, setCheck] = useState(false);
    //called at start
    useEffect(() => setCheck(true));
    return (
        <div className = {classes.root}>
            <AppBar className = {classes.appBar} elevation = "0">
                <Toolbar className = {classes.appBarWrapper}>
                    <h1 className = {[classes.title]}>
                        PRO
                        <span className = {classes.gem}>
                            GEM
                        </span>
                    </h1>
                    <IconButton>
                        <SortSharp className = {classes.sort}/>
                    </IconButton>
                </Toolbar>
            </AppBar>
            <Collapse in = {checked}{...(checked ? {timeout: 1000} : {})} collapsedHeight = "50"
                      className = {classesD.desc}>
                <div>
                    <h1 className = {classesD.text2}>Feature rich <br/>
                        <span className = {[classesD.text1]}> Project Management Application</span>
                        <br/>
                        <span className = {classesD.text3}>
                                            for your Projects
                                       </span>
                    </h1>
                    <Link to = "login-signup" smooth = {true} duration={300}>
                        <IconButton>
                            <ExpandMoreIcon className = {classesD.expand}/>
                        </IconButton>
                    </Link>
                </div>
            </Collapse>
        </div>
    )
}