import {SIGNIN} from "../Util/APIUrls"
import {createHeader, createReq, createSignInRequest, parseSignInResponse} from "./ReqRespService"
import {AddCookie, GetCookie} from "./CookieService"


/**
 * Creates url,body,header,request and uses it to make an API call to the server
 * If successful, the token is going to get saved in the cookie
 * @param username (String) userID
 * @param password (String) password
 * @constructor
 */
export function SignIn(username, password) {
    //Setting up API Request
    const url = SIGNIN;
    const body = createSignInRequest(username, password)
    const header = createHeader("application/json", null)
    const request = createReq("POST", header, body)
    //Calling API
    fetch(url, request).then(r => r.json()).then(data => {
        const tokenJWT = parseSignInResponse(data)
        //save token to cookie
        AddCookie("jwt", tokenJWT)
        console.log(GetCookie("jwt"))
    })
}