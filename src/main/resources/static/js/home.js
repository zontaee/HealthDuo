let addressInfo = document.getElementById("addressInfo");

window.onload = async function () {
    str = "";
    asd = "";
    let URL = "http://localhost:8080/bbsRegion"
    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
    }
    let response = await fetch(URL, options)
    let regionInfo = await response.json();
    for (let i = 0; i < regionInfo.length; i++) {
        str += '<button class="btn-secondary btn-lg custom" value="button" onclick="nextRegion(\''+regionInfo[i]+'\')">' + regionInfo[i] + '</button>';
        if (i % 2 == 0 && i > 0) {
            str += '<br>'
        }
    }
    addressInfo.innerHTML = str;
}

async function nextRegion(region) {
    addressInfo.innerHTML = " ";
    str = "";

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

    if (region == "서울특별시") {
        for (let i = 0; i < nextinfo.length; i++) {

            str += '<button class="btn-secondary btn-lg custom" type="button" onclick="nextStreet(\''+ nextinfo[i] +'\')">' + nextinfo[i] + '</button>';
            if (i % 2 == 0 && i > 0) {
                str += '<br>'
            }
        }
        addressInfo.innerHTML = str;
    } else {
        for (let i = 0; i < nextinfo.length; i++) {
            str += '<button class="btn-secondary btn-lg custom" type="button" onclick="nextFullCity(\'' + nextinfo[i] +'\' )">' + nextinfo[i] + '</button>';
            if (i % 2 == 0 && i > 0) {
                str += '<br>'
            }
        }
        addressInfo.innerHTML = str;
    }
}

async function nextFullCity(city) {

    addressInfo.innerHTML = " ";
    str = "";

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

        str += '<button class="btn-secondary btn-lg custom" type="button"  onclick="nextStreet(\''+ nextinfo[i] +'\')">' + nextinfo[i] + '</button>';
        if (i % 2 == 0 && i > 0) {
            str += '<br>'
        }
    }
    addressInfo.innerHTML = str;


}

async function nextStreet(fullCity) {

    addressInfo.innerHTML = " ";
    str = "";

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

        str += '<button class="btn-secondary btn-lg custom" type="button" onclick="location.href=\'bbsLists?address='+ nextinfo[i] + '\'">' + nextinfo[i] + '</button>';
        if (i % 2 == 0 && i > 0) {
            str += '<br>'
        }
    }
    addressInfo.innerHTML = str;


}