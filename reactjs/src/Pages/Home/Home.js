import {useEffect} from "react";
import {useNavigate} from "react-router";
import {doesCookieExist} from "../../Services/CookieService";

export default function () {
    const nav = useNavigate()
    useEffect(() => {
        if(doesCookieExist('jwt')==false){
            nav('/')
        }
    })
    return <h1>THIS IS MY HOME</h1>
}