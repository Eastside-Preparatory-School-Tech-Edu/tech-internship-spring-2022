function request(obj) {
    return new Promise((resolve, reject) => {
        let xhr = new XMLHttpRequest();

        xhr.open(obj.verb || "GET", obj.url);
        xhr.onload = () => {
            if (xhr.status >= 200 && xhr.status < 300) {
                resolve(xhr.response);
            } else {
                reject(xhr.statusText);
            }
        };
        
//        xhr.setRequestHeader("Access-Control-Allow-Origin","https://epsauth.azurewebsites.net");
//        xhr.setRequestHeader("Access-Control-Allow-Origin","*");
        
        xhr.onerror = () => reject(xhr.statusText);
        xhr.send(obj.body);
    });
}

function request_summary() {
    let obj1 = {
        url : "/getCompleteSummary",
        verb : "GET",
        body : ""
    }
    
    request(obj1);
}