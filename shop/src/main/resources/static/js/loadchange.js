
$j('#changeworde').bind('keypress', function (e) {
    if (e.keyCode == 13) {
        var check = false;
        var checkcp = checkValidateZcpE();
        var checkp = checkValidateZpE();
        // Enter pressed... do anything here...
        if (checkcp && checkp)
        {
            check = true;
        }
        if (check)
            loadEUS();
    }
});
$j("#changeworde").click(function () {
    var check = false;
    var checkp = checkValidateZpE();
    var checkcp = checkValidateZcpE();

    if (checkp && checkcp)
    {
        check = true;
    }
    if (check)
        loadEUS();

    return false;
});
function loadEUS() {

    var passwordnew = document.getElementById("sucpasse").value;
     var passwordold = document.getElementById("minpee").value;
    var sid = document.getElementById("idhidden").value;
    var action = "id=" + sid + "&passnew=" + passwordnew+"&passold="+passwordold;


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 1) {
            document.getElementById("changeworde").innerHTML = "PROCESSING...";
            document.getElementById("cpasse").style.display = "none";
            document.getElementById("cpasse1").style.display = "none";
            document.getElementById("passe1").style.display = "none";
            document.getElementById("passe2").style.display = "none";
            document.getElementById("cupasse").style.display = "none";

        }
          if(this.readyState == 4 && this.status == 400){
                        document.getElementById("changeworde").innerHTML = "CHANGE PASSWORD";
                        alert("Enter the old password fail!!!")

                    }
        if (this.readyState == 4 && this.status == 200) {
            var text = this.responseText.trim();
                document.getElementById("changeworde").innerHTML = "CHANGE PASSWORD";
                alert("Change Sucessful!!!");
                document.getElementById("formedit").style.display = "none";
                document.body.style.overflow = "auto";
                document.getElementsByClassName("twoicon")[0].style.display = "block";


        }
    };
    xhttp.open("POST", "changepassword", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=ISO-8859-15");
    
    xhttp.send(action);

}

