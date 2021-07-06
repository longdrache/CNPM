$j("#sform").submit(function () {
    var check = false;
    var checke = checkValidateZe1();
    var checkp = checkValidateZp1();
    var checkcp = checkValidateZcp1();
    var checkfn = checkValidateZfn();


    if (checke && checkp && checkcp && checkfn )
    {
        check = true;
    }
    if (check) {
        loadINS();
    }


    return false;
});
function loadINS() {
    var emails = document.getElementsByName("semail")[0].value;
    var passwords = document.getElementsByName("spsw")[0].value;
    var name = document.getElementsByName("username")[0].value;
    var action = "email=" + emails + "&password=" + passwords + "&name=" + name;
   
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 1) {
            document.getElementById("changewordsignup").innerHTML = "PROCESSING...";
             document.getElementById("getone").style.display = "none";
             document.getElementById("closesignup").style.display = "none";

           
                document.getElementsByClassName('joina')[0].style.display = "none";
        }
        if(this.readyState == 4 && this.status == 302){
          document.getElementById("changewordsignup").innerHTML = "SIGN UP";
          document.getElementsByName("emaillo")[0].value=emails = document.getElementsByName("semail")[0].value;
            document.getElementById("getone").style.display = "block";
            document.getElementById("closesignup").style.display = "block";

            document.getElementById("uemail").style.display = "none";
            document.getElementsByClassName('joina')[0].style.display = "block";
                        }
        if (this.readyState == 4 && this.status == 200) {
            var text = this.responseText.trim();
                var ic = JSON.parse(text);
                document.getElementById("changewordsignup").innerHTML = "SIGN UP";
//                document.getElementById("loginuser").innerHTML = "Hi, " + ic.name;
//              document.getElementById("emailhidden").innerHTML=ic.email;
                document.getElementById("formsignup").style.display = "none";
                  document.getElementById("closesignup").style.display = "block";
                   document.getElementById("forminfo").style.display = "block";
//                document.getElementById("help").style.display = "none";
//                document.getElementById("joinus").style.display = "none";
//                document.getElementById("login").style.display = "none";
                document.body.style.overflow = "auto";
//                document.getElementsByClassName("twoicon")[0].style.display = "block";


        }
    };
    xhr.open("POST", "/signup", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
    
    xhr.send(action);
}
                    