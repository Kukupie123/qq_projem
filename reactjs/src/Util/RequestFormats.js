export function createReq(method, header, body) {
    return {
        method: method,
        headers: header,
        body: JSON.stringify(body)
    }
}

export function createHeader(contentType, authorization) {
    let header = {}
    header["Content-Type"] = contentType;
    if (authorization != null) header["Authorization"] = authorization
    return header
}