$j("#sform").submit(function () {
    var check = false;
    var checke = checkValidateZe1();
    var checkp = checkValidateZp1();
    var checkcp = checkValidateZcp1();
    var checkfn = checkValidateZfn();
    var checkln = checkValidateZln();

    if (checke && checkp && checkcp && checkfn && checkln)
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
    var fname = document.getElementsByName("fname")[0].value;
    var lname = document.getElementsByName("lname")[0].value;
    var action = "action=signup&emails=" + emails + "&spsw=" + passwords + "&fname=" + fname + "&lname=" + lname;
   
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 1) {
            document.getElementById("changewordsignup").innerHTML = "PROCESSING...";
             document.getElementById("getone").style.display = "none";

           
                document.getElementsByClassName('joina')[0].style.display = "none";
        }
        if (this.readyState == 4 && this.status == 200) {
            var text = this.responseText.trim();
            if (text == 'fail') {
                document.getElementById("changewordsignup").innerHTML = "SIGN IN";
                document.getElementById("getone").style.display = "block";

                document.getElementById("uemail").style.display = "none";
                document.getElementsByClassName('joina')[0].style.display = "block";



            }else{
            
                document.getElementById("changewordsignup").innerHTML = "SIGN IN";
                
                document.getElementById("formsignup").style.display = "none";
              document.getElementById("guestlogin").style.display = "none";
              document.getElementById("login").style.display = "none";
              document.getElementById("clonelogin").style.display = "block";
                document.body.style.overflow = "auto";
                
              
            }

        }
    };
    xhr.open("POST", "UServlet", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=ISO-8859-15");
    xhr.send(action);
}
                    