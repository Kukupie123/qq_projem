import {SIGNIN} from "../Util/APIUrls"
import {createHeader, createReq} from "../Util/RequestFormats"
import {createSignIn} from "../ReqResp/Signup-in/SignInReq";
import {parseResponse} from "../ReqResp/Signup-in/SignInResp"
import {AddCookie,GetCookie} from "../Util/CookieProcess"
export function SignIn(username, password) {
    //Setting up API Request
    const url = SIGNIN;
    const body = createSignIn(username, password)
    const header = createHeader("application/json", null)
    const request = createReq("POST", header, body)
    //Calling API
    fetch(url, request).then(r => r.json()).then(data => {
        const tokenJWT = parseResponse(data)
        //save token to cookie
        AddCookie("jwt",tokenJWT)
        console.log(GetCookie("jwt"))

    })
}