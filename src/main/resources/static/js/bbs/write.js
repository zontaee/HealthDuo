/*
/!**
 * 지역 선택 메소드 -> 특별시와 도를 구분하여 셀렉트박스 생성
 * @param region
 * @returns {Promise<void>}
 *!/
let firstSelect = document.getElementById("one");
let secondSelect = document.getElementById("two");
let thridSelect = document.getElementById("three");

async function changeRegion(region) {
    let str = "";
    let str2 = "";
    let str3 = "";
    let URL = "http://localhost:8080/findCity"

    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            region: region
        })
    }
    let response = await fetch(URL, options)
    let nextinfo = await response.json();
    let firstSelect = document.getElementById("one");
    let secondSelect = document.getElementById("two");
    let thridSelect = document.getElementById("three");

    //초기화
    firstSelect.innerHTML = str;
    secondSelect.innerHTML = str;
    thridSelect.innerHTML = str;


    if (region == "서울특별시") {

        str += '<select class="form-select" id="fullCity" name="fullCity" field="구"  onchange="changeStreet(this.value)">';
        str += '<option value="">구</option>';
        for (let i = 0; i < nextinfo.length; i++) {
            str += '<option value="' + nextinfo[i] + '"> ' + nextinfo[i] + ' </option>'
        }
        str += '</select>'

        firstSelect.innerHTML = str;

        str2 += '<select class=" form-select" id="street" name="street" field="동">'
        str2 += '<option value="">동</option>'
        str2 += '</select>'
        secondSelect.innerHTML = str2;
    } else {
        str += '<select class="form-select" id="city" name="city" field="시"  onchange="changefullCity(this.value)">';
        str += '<option value="">시</option>';
        for (let i = 0; i < nextinfo.length; i++) {
            str += '<option value="' + nextinfo[i] + '"> ' + nextinfo[i] + ' </option>'
        }
        str += '</select>'

        firstSelect.innerHTML = str;


        str2 += '<select class="form-select" id="fullCity" name="fullCity" field="구"  onchange="changeStreet(this.value)">'
        str2 += '<option value="">구</option>'
        str2 += '</select>'
        secondSelect.innerHTML = str2;

        str3 += '<select class="form-select" id="street" name="street" field="동">'
        str3 += '<option value="">동</option>'
        str3 += '</select>'
        thridSelect.innerHTML = str3;

    }

}

async function changefullCity(city) {

    let streetSelect = document.getElementById("street");
    //street 정보 삭제
    let elementsByTagName = streetSelect.getElementsByTagName("option");
    console.log(elementsByTagName.length);
    for (let i = elementsByTagName.length - 1; i >= 0; i--) {
        elementsByTagName[i].remove();
    }
    let str = ""
    let fullCityId = document.getElementById("fullCity");
    let URL = "http://localhost:8080/findfullCity"

    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            city: city
        })
    }
    let response = await fetch(URL, options)
    let nextinfo = await response.json();


    for (let i = 0; i < nextinfo.length; i++) {
        str += '<option value="' + nextinfo[i] + '"> ' + nextinfo[i] + ' </option>'
    }
    fullCityId.innerHTML = str;

}

async function changeStreet(fullCity) {
    let str = ""
    let streetId = document.getElementById("street");
    let URL = "http://localhost:8080/findStreet"

    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            fullCity: fullCity
        })
    }
    let response = await fetch(URL, options)
    let nextinfo = await response.json();
    for (let i = 0; i < nextinfo.length; i++) {
        str += '<option value="' + nextinfo[i] + '"> ' + nextinfo[i] + ' </option>'
    }
    streetId.innerHTML = str;


}*/
