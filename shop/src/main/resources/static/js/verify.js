$j("#verify").click(function(){
$j("#formverify").css("display","block");
    $j("#formlogin").css("display","none");
    $j("#emailverify").val($j("#mine").val());
     $j("#notactive").css("display","none");

});
$j("#formverify").submit(function(e){
loadVerify();
return false;
});
function outverify(){
$j("#formverify").css("display","none");
}

function loadVerify() {
    var email = document.getElementById("emailverify").value;

    var action = "email=" + email


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function (e) {
        if (this.readyState == 1) {
            document.getElementById("changeverify").innerHTML = "PROCESSING...";

        }

        if(this.readyState == 4 && this.status == 404){
            alert("We dont find your account")
        document.getElementById("changeverify").innerHTML = "SEND EMAIL";


        }
           if(this.readyState == 4 && this.status == 302){
                    alert("Your have been active")
                document.getElementById("changeverify").innerHTML = "SEND EMAIL";


                }
        if (this.readyState == 4 && this.status == 200) {
                document.getElementById("changeverify").innerHTML = "SEND EMAIL";
        // document.getElementById("guestlogin").style.display = "none";
            //  document.getElementById("clonelogin").style.display = "block";
            alert("Email has been sent");




            }

    };
    xhttp.open("POST", "/verify", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhttp.send(action);
}
