import {makeStyles} from "@material-ui/core";
import React from "react";

const useStyles = makeStyles((theme) => ({
    rott: {
        height: "100vh",
        minHeight:"100vh",
        color:"red"
    }
}));
export default function TEST() {
    let g = useStyles();
    return <div className = {g.rott}>
        SUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasdSUPasd
    </div>
}