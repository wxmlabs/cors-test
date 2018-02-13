(function () {
    'use strict';
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        document.body.innerText = "Browser do not support XML Http Request."
    }

    function ajaxGet(url, result) {
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && typeof result === 'function') {
                result(xmlhttp);
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    }

    function ajaxPostJson(url, jsonObj, result) {
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && typeof result === 'function') {
                result(xmlhttp);
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(jsonObj));
    }

    function ajaxPutJson(url, jsonObj, result) {
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && typeof result === 'function') {
                result(xmlhttp);
            }
        };
        xmlhttp.open("PUT", url, true);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(jsonObj));
    }

    function ajaxPatchJson(url, jsonObj, result) {
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && typeof result === 'function') {
                result(xmlhttp);
            }
        };
        xmlhttp.open("PATCH", url, true);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(jsonObj));
    }

    function ajaxDelete(url, result) {
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && typeof result === 'function') {
                result(xmlhttp);
            }
        };
        xmlhttp.open("DELETE", url, true);
        xmlhttp.send();
    }

    window.$Ajax = {
        Get: ajaxGet,
        Post: ajaxPostJson,
        Put: ajaxPutJson,
        Patch: ajaxPatchJson,
        Delete: ajaxDelete
    };
})();
