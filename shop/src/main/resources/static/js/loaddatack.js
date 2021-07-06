

$j("#changewort").click(function () {
    var check = false;
    var checke = checkValidateZe();
    var checkp = checkValidateZp();
    if (checke && checkp)
    {
        check = true;
    }
    if (check)
        loadU();

    return false;
});
function loadU() {
    var emaill = document.getElementsByName("emaillo")[0].value;
    var passwordl = document.getElementsByName("pswlo")[0].value;
    var checkBox = document.getElementById("myCheck");
    var checked="";
    if(checkBox.checked) checked="&remember-me=on";
    var action = "username=" + emaill + "&password=" + passwordl+checked;
   
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function (e) {
        if (this.readyState == 1) {
            document.getElementById("changewort").innerHTML = "PROCESSING...";
            document.getElementById("info").style.display = "none";
            document.getElementById("closelogin").style.display = "none";
            document.getElementById("notactive").style.display = "none";
        }
          var ic = JSON.parse(this.response);
        if(this.readyState == 4 && this.status == 400){

        document.getElementById("changewort").innerHTML = "SIGN IN";
                        document.getElementById("info").style.display = "block";
                        document.getElementById("closelogin").style.display = "block";
document.getElementById("notactive").style.display = "none";
        }
          if(this.readyState == 4 && this.status == 404){

                document.getElementById("changewort").innerHTML = "SIGN IN";
                                document.getElementById("info").style.display = "none";
                                document.getElementById("notactive").style.display = "block";
                                document.getElementById("closelogin").style.display = "block";

                }
        if (this.readyState == 4 && this.status == 200) {
                document.getElementById("changewort").innerHTML = "SIGN IN";
        // document.getElementById("guestlogin").style.display = "none";
            //  document.getElementById("clonelogin").style.display = "block";
              location.reload();


               
             
            }

    };
    xhttp.open("POST", "/login", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhttp.send(action);
}

