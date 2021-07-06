function out() {
    document.getElementById("formlogin").style.display = "none";
    document.body.style.overflow = "auto";
    document.getElementById("info").style.display = "none";
    document.getElementById("cemail").style.display = "none";
    document.getElementById("cpass").style.display = "none";
}


function outsign() {
    document.getElementById("formsignup").style.display = "none";
    document.body.style.overflow = "auto";
    document.getElementById("info").style.display = "none";
    document.getElementById("cupass1").style.display = "none";
    document.getElementById("cupass2").style.display = "none";
    document.getElementById("cupass").style.display = "none";
    document.getElementById("upass").style.display = "none";
    document.getElementById("upass1").style.display = "none";
    document.getElementById("ufname").style.display = "none";
    document.getElementById("ulname").style.display = "none";
}
function outedit() {
    document.getElementById("formedit").style.display = "none";
    document.body.style.overflow = "auto";
    document.getElementById("info").style.display = "none";
    document.getElementById("passe1").style.display = "none";
    document.getElementById("passe2").style.display = "none";
    document.getElementById("cupasse").style.display = "none"
    document.getElementById("cpasse").style.display = "none";
    document.getElementById("cpasse1").style.display = "none";
    document.getElementById("cpasseee").style.display = "none";

}
function outforget(){
document.getElementById("formforget").style.display = "none";

}

function outoders() {
    document.getElementById("formorders").style.display = "none";
    document.body.style.overflow = "auto";


}

function checkValidateZe() {
    var email = document.getElementById("mine").value;
    var mailformat = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    if (!email.match(mailformat)) {
        document.getElementById("cemail").style.display = "block";
        return false;
    }

    document.getElementById("cemail").style.display = "none";
    return true;
}

function checkValidateZp() {
    var x = document.getElementById("minp").value.length;
    if (x == 0) {
        document.getElementById("cpass").style.display = "block";
        return false;
    }
    document.getElementById("cpass").style.display = "none";
    return true;

}

function checkValidateZp1() {
    var x = document.getElementById("supass").value.length;
    if (x == 0) {
        document.getElementById("cupass1").style.display = "block";
        document.getElementById("cupass2").style.display = "none";
        document.getElementById("cupass").style.display = "none";
        return false;
    } else
    if (x < 6) {
        document.getElementById("cupass").style.display = "block";
        document.getElementById("cupass1").style.display = "none";
        document.getElementById("cupass2").style.display = "none";
        return false;
    } else
    if (x > 15) {
        document.getElementById("cupass2").style.display = "block";
        document.getElementById("cupass").style.display = "none";
        document.getElementById("cupass1").style.display = "none";
        return false;
    }
    document.getElementById("cupass1").style.display = "none";
    document.getElementById("cupass2").style.display = "none";
    document.getElementById("cupass").style.display = "none";

    return true;

}

function checkValidateZcp1() {
    var x = document.getElementById("sucpass").value.length;
    var s = document.getElementById("supass").value;
    var s1 = document.getElementById("sucpass").value;
    if (x == 0) {
        document.getElementById("upass").style.display = "block";
        document.getElementById("upass1").style.display = "none";
        return false;
    }
    if (s1 != s) {
        document.getElementById("upass1").style.display = "block";
        document.getElementById("upass").style.display = "none";
        return false;
    }
    document.getElementById("upass").style.display = "none";
    document.getElementById("upass1").style.display = "none";
    return true;

}

function checkValidateZfn() {
    var x = document.getElementById("sufname").value.length;

    if (x == 0) {
        document.getElementById("ufname").style.display = "block";
        return false;
    }
    document.getElementById("ufname").style.display = "none";
    return true;

}


function checktwopass() {
    var x = document.getElementById("sucpass").value.length;
    if (x !== 0) {
        checkValidateZp1();
        checkValidateZcp1();
    }
    if (x === 0) {
        checkValidateZp1();
    }
}
function checkValidateZe1() {
    var email = document.getElementById("sue").value;
    var mailformat = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    if (!email.match(mailformat)) {
        document.getElementById("uemail").style.display = "block";
        document.getElementById("getone").style.display = "none";
        document.getElementsByClassName('joina')[0].style.display = "none";

        return false;
    }
    document.getElementById("uemail").style.display = "none";
    document.getElementById("getone").style.display = "none";
    document.getElementsByClassName('joina')[0].style.display = "none";
    return true;

}

function checkValidateZpE() {
    var x = document.getElementById("passedit").value.length;
    if (x == 0) {
        document.getElementById("passe1").style.display = "block";
        document.getElementById("passe2").style.display = "none";
        document.getElementById("cupasse").style.display = "none";
        return false;
    } else
    if (x < 6) {
        document.getElementById("cupasse").style.display = "block";
        document.getElementById("passe1").style.display = "none";
        document.getElementById("passe2").style.display = "none";
        return false;
    } else
    if (x > 15) {
        document.getElementById("passe2").style.display = "block";
        document.getElementById("cupasse").style.display = "none";
        document.getElementById("passe1").style.display = "none";
        return false;
    }
    document.getElementById("passe1").style.display = "none";
    document.getElementById("passe2").style.display = "none";
    document.getElementById("cupasse").style.display = "none";

    return true;

}

function checkValidateZcpE() {
    var x = document.getElementById("sucpasse").value.length;
    var s = document.getElementById("passedit").value;
    var s1 = document.getElementById("sucpasse").value;
    if (x == 0) {
        document.getElementById("cpasse").style.display = "block";
        document.getElementById("cpasse1").style.display = "none";
        return false;
    }
    if (s1 != s) {
        document.getElementById("cpasse1").style.display = "block";
        document.getElementById("cpasse").style.display = "none";
        return false;
    }
    document.getElementById("cpasse").style.display = "none";
    document.getElementById("cpasse1").style.display = "none";
    return true;

}



function checkValidateZpEE() {
    var x = document.getElementById("minpee").value.length;
    if (x == 0) {
        document.getElementById("cpassee").style.display = "block";
        return false;
    }
    document.getElementById("cpassee").style.display = "none";
    return true;

}

function checktwopassE() {
    var x = document.getElementById("sucpasse").value.length;
    if (x != 0) {
        checkValidateZpE();
        checkValidateZcpE();
    }
    if (x == 0) {
        checkValidateZpE();
    }
}
