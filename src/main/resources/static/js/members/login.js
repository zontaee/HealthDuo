let checkCookie = document.cookie;
let value = checkCookie.split('=');
console.log(value[1]);
if(value[1] === undefined){

    value[1] ="";
}

window.onload = function (){
    let id = document.getElementById("loginId");
    id.setAttribute("value",value[1]);
}