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

function mark_complete(name, key2) {
    level = window.prompt(name + " level for " + key2);
    
    if(level > 5 || level < 0 || isNaN(level)){
        alert("Invalid input: must be a number between 1 and 5.");
        return;
    }
    
    request({
        verb: "GET",
        url: "/addFormStatus/" + name + "/" + key2 + "/" + level
    })
    
    window.location.reload();
}

let options = document.getElementsByClassName("options-container")[0];

function toggle_options() {
    if( options.style.display == "block" ) {
        options.style.display = "none"
    } else {
        options.style.display = "block"
    }
}

let add_person_field = document.getElementById("add-person-field");

function add_person(){
    request({
        verb: "GET",
        url: "/addParticipant/" + add_person_field.value
    })
    
    window.location.reload();
}

let add_form_field = document.getElementById("add-form-field");

function add_form(){
    request({
        verb: "GET",
        url: "/addRequiredForm/" + add_form_field.value
    })
    
    window.location.reload();
}