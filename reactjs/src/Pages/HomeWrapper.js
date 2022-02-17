import {doesCookieExist} from "../Services/CookieService";
import {useNavigate} from "react-router";
import {useEffect} from "react";


export default function Home() {
    const nav = useNavigate()
    useEffect(() => {
        if (doesCookieExist('jwt') === false)
            nav("/land")
        else nav("/home")
    })

    return <h1>Hello user</h1>


}