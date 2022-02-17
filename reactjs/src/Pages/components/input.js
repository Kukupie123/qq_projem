import styled from "styled-components";
import {makeStyles} from "@material-ui/core";

const styles = makeStyles(() => ({
        style: {
            background: "rgba(255,255,255,0.15)",
            boxShadow: "0px 8px 32px 0 rgba(31,32,135,0.37)",
            borderRadius: "2rem",
            width: "80%",
            height: "3rem",
            padding: "1rem",
            border: "none",
            outline: "none",
            margin: "1rem",
            color: "#3c354e",
            fontSize: "1rem",
            fontWeight: "bold",
            textColor: "#ffff"
        }
    }
))

export default function Input({type, placeholder, onValueChange}) {
    return (

        <input className={styles().style} type={type} placeholder={placeholder} onChange={onValueChange}/>
    )
}


const StyledInput = styled.input`
background : rgba(255,255,255,0.15);
box-shadow: 0px 8px 32px 0 rgba(31,32,135,0.37);
border-radius:2rem;
width: 80%;
height : 3rem;
padding : 1rem;
border: none;
outline: none;
margin : 1rem;
color : #3c354e;
font-size:1rem;
font-weight:bold;
text-color: #ffff;
&:focus
{
display: inline-block;
box-shadow: 0 0 0 0.2rem #b9abe0;
backdrop-filter:blur(12rem);
border-radius: 2rem;
}
&::placeholder {
color : #b9abe099;
font-weight: 100;
font-size : 1rem;
}
`;