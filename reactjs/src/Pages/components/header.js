import {AppBar, IconButton, makeStyles, Toolbar} from "@material-ui/core";
import {SortSharp} from "@material-ui/icons";
import React from "react";


const useStyles = makeStyles((theme) => ({
    appBar: {
        background: "none",

    },

    appBarWrapper: {
        width: "80%",
        margin: "0 auto"
    },

    title: {
        flexGrow: "1",
        fontFamily: "fantasy",
        color: "#4c6ccd"

    },

    sort: {
        color: "whitesmoke",
        fontSize: "2rem"
    },


    gem: {
        color: "#5db3e7"
    }
}));

export default function Header() {
    const classes = useStyles();
    return (
        <div>
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
        </div>
    )
}