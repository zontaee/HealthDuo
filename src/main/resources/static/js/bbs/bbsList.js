let getSesstion = "[[ ${session.memberId} ]]";


function secretBbsCheck(bbsno,member){

    if(getSesstion == member){
        window.location = "http://localhost:8080/content/" + bbsno;
    }else {
        alert(getSesstion);}
}