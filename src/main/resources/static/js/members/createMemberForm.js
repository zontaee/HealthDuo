// 중복회원 검사 0이면 중복회원 또는 무확인 1이면 중복회원검사 완료
let checkDuplicatedMember = 0
/**
 * 중복회원 검사
 * @returns {Promise<void>}
 */
async function duplicatedMember() {
    let memberId = document.getElementById("memberId").value;
    let checkWords = document.getElementById("duplicatedMember");

    let URL = "http://localhost:8080/duplicatedMember"
    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            memberId: memberId
        })
    }
    let response = await fetch(URL, options)
    let text = await response.text()
    if (text == 0) {
        checkDuplicatedMember = 1;
        checkWords.innerText =  "사용가능한 회원 아이디 입니다.";
    } else {
        checkDuplicatedMember = 0;
        checkWords.innerText =  "다른 아이디를 사용해 주세요.";
    }


}

/**
 * 비밀번호 일치 확인
 * @returns {boolean}
 */
function check() {
    let password1 = document.getElementById("password1").value;
    let password2 = document.getElementById("password2").value;
    if(checkDuplicatedMember == 1){
        if (password1 === "" || password2 === "") {
            alert("비밀번호를 입력해주세요.");
        } else {
            if (password1 === password2) {
                return true;
            } else
                alert("일치하는 비밀번호를 입력해주세요.");
            return false;
        }
    }else {
        alert("회원 중복 확인해주세요.")
        return  false

    }
}