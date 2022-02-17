import cookie from 'json-cookie';


export function AddCookie(name, value) {
    cookie.set(name, value)
}

export function GetCookie(keyName) {
    const a = cookie.get(keyName)
    return a['token']
}

export function doesCookieExist(keyName) {
    const t = GetCookie(keyName)
    if (t === undefined || t === "") {
        return false
    } else {
        return true;
    }
}
