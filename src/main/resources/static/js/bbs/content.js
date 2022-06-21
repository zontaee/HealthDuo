let addDivComment = document.getElementById("comment");
let bbsNo = "[[${bbsDTO.bbsNo}]]";

window.onload = CommentList();

/*<![CDATA[*/
/**
 *모든 댓글 불러오기
 */
async function CommentList() {
    let str = "";
    let URL = "http://localhost:8080/findComment"

    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            bbsNo: bbsNo
        })
    }
    let response = await fetch(URL, options)
    let comment = await response.json();

    for (let i = 0; i < comment.length; i++) {
        let childIdLevel = "L" + comment[i].commentGroup + "L" + comment[i].level + "L" + comment[i].checkInfo;
        let seq = comment[i].commentSequence;
        let cnt = comment[i].commentCnt;
        let div = "divId" + cnt;
        let childcommentsign = "";
        for (let j = 0; j < comment[i].level; j++) {
            childcommentsign += "-";

        }
        console.log(childcommentsign)
        str += "<div class=\'ms-3\'>";
        str += "<div class=\'fw-bold\'>" + childcommentsign + "작성자 : " + comment[i].memberId + "  작성일 : " + comment[i].date;
        str += '<button value="button" onclick="childCommentAdd(' + seq + ',\'' + childIdLevel + '\',\'' + cnt + '\'  )">댓글</button>';
        str += '<button value="button" onclick="commentDelete(' + comment[i].commentGroup + ',\'' + comment[i].commentSequence + '\' )">삭제</button>';
        str += "<br>"
        str += childcommentsign + "내용 : " + comment[i].content;
        str += "<div id='" + div + "'></div></div>";
        str += "</div><br>"
        addDivComment.innerHTML = str;
    }


}

/**
 *댓글생성 함수
 */
async function commentAdd() {

    let content = document.getElementById("content").value;

    if (content === "") {
        alert("댓글을 입력해주세요.")
    } else {
        console.log("[[${bbsDTO.bbsNo}]]");
        let URL = "http://localhost:8080/CommentWrite"


        let options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                CommemtContent: content,
                bbsNo: bbsNo
            })
        }
        await fetch(URL, options)
        alert("댓글 등록이 완료 되었습니다.")
        window.location.reload(true);
    }
}

/**
 *댓글 삭제 함수
 */
async function commentDelete(commentGroup,commentSequence) {
    let URL = "http://localhost:8080/commentDelete"
    let options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            commentGroup: commentGroup,
            commentSequence : commentSequence
        })
    }
    await fetch(URL, options)
    alert("댓글 삭제 완료 되었습니다.")
    window.location.reload(true);
}

/**
 * childnumber -> <div id=""> id에 추가하여 대댓글을 작성할떄 댓글을 작성할 댓글 바로아래 작성창 생성
 * childnumbertext -> <textarea> 밸류값 저장
 * 위 두변수다 댓글에 댓글을 작성하기위해 생성
 * 대댓글 작성폼생성 을 위한 함수
 */
function childCommentAdd(Seq, child, cnt) {
    let str = "";
    let seq = Seq;
    let div = "divId" + cnt;
    let childnumbertext = "childtext" + child;
    let childcontent = document.getElementById(div);
    str += "<form className='mb-4'>";
    str += "<textarea className='form-control' rows='3' id='" + childnumbertext + "' placeholder='댓글을 입력해주세요'></textarea>";
    str += '<button value="button" onclick="childCommentWirte(' + seq + ',\'' + child + '\')">입력</button>';
    str += "<button value='취소' th:onclick='com()'>취소</button><br>";
    childcontent.innerHTML = str;
}

/**
 * 대댓글 작성 데이터베이스 저장 함수
 */
async function childCommentWirte(Seq, child) {
    let seq = Seq;
    let childnumbertext = "childtext" + child;
    let content = document.getElementById(childnumbertext).value;
    let bbsNo = "[[${bbsDTO.bbsNo}]]";

    let URL = "http://localhost:8080/childCommentWrite"

    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            CommemtContent: content,
            bbsNo: bbsNo,
            childinfo: child,
            Seq: seq
        })
    }
    let response = await fetch(URL, options)
    alert("댓글 등록이 완료 되었습니다.")
    window.location.reload(true);

}