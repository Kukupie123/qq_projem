/**
 * create an request body object
 * @param method (String) the method such as get, post
 * @param header (Object) header in json format object
 * @param body (Object) body of the request
 * @returns {{headers, method, body: string}}
 */
export function createReq(method, header, body) {
    return {
        method: method,
        headers: header,
        body: JSON.stringify(body)
    }
}

/**
 * Create a header object
 * @param contentType (String) application/json
 * @param authorization (String) bearer jwtToken
 * @returns header object with properties that are not null
 */
export function createHeader(contentType, authorization) {
    let header = {}
    header["Content-Type"] = contentType;
    if (authorization != null) header["Authorization"] = authorization
    return header
}

/**
 * Create Sign in request body
 * @param userID (String)
 * @param password (String)
 * @returns {{password, email}}
 */
export function createSignInRequest(userID, password) {
    return {
        "email": userID,
        "password": password
    }
}

/**
 * Create object with jwt token
 * @param response (JSON) response that has been parsed as json
 * @returns {{token}}
 */
export function parseSignInResponse(response) {
    return {
        token: response['token']
    }
}
