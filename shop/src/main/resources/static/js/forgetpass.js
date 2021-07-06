$j("#forget").click(function(){
$j("#formforget").css("display","block");
    $j("#formlogin").css("display","none");

});
$j("#formforget").submit(function(e){
loadForget();
return false;
});

function loadForget() {
    var email = document.getElementById("emailforget").value;

    var action = "email=" + email


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function (e) {
        if (this.readyState == 1) {
            document.getElementById("changeforget").innerHTML = "PROCESSING...";

        }

        if(this.readyState == 4 && this.status == 404){
            alert("We dont find your password")
        document.getElementById("changeforget").innerHTML = "SEND EMAIL";


        }
        if (this.readyState == 4 && this.status == 200) {
                document.getElementById("changeforget").innerHTML = "SEND EMAIL";
        // document.getElementById("guestlogin").style.display = "none";
            //  document.getElementById("clonelogin").style.display = "block";
            alert("Email has been sent");




            }

    };
    xhttp.open("POST", "/forget", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhttp.send(action);
}
