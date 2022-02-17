import cookie from 'json-cookie';

/**
 * Add Cookie
 * @param name (String)
 * @param value (String)
 */
export function AddCookie(name, value) {
    cookie.set(name, value)
}

/**
 * returns String value of the key
 * @param keyName (String) name of the cookie
 * @returns {*} (String) value of the cookie in String
 */
export function GetCookie(keyName) {
    const a = cookie.get(keyName)
    return a['token']
}

/**
 * Returns true if cookie key:value pair exists
 * @param keyName (String)
 * @returns {boolean}
 */
export function doesCookieExist(keyName) {
    const t = GetCookie(keyName)
    return !(t === undefined || t === "");
}
