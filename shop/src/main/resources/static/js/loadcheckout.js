(function () {
    'use strict';

    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');

        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
                event.preventDefault();
                event.stopPropagation();
            }, false);
        });
    }, false);
})();

function loadUOrder() {

   var rname= document.getElementById("username").innerHTML;
     var address= document.getElementById("address").innerHTML;
    var address2 = document.getElementById("address2").innerHTML;
    var country =document.getElementById("country").value;
    var lineItem=document.getElementById("country").innerHTML
    var action = "rname=" + rname + "&address=" + address+"&address2="+address2+"&country="+country+lineItem;


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
        if (this.readyState == 4 && this.status == 200) {
            var text = this.responseText.trim();
            if (text == 'se') {
                document.getElementById("changeworde").innerHTML = "CHANGE PASSWORD";
                alert("Change Sucessful!!!");
                document.getElementById("formedit").style.display = "none";
                document.body.style.overflow = "auto";
                document.getElementsByClassName("twoicon")[0].style.display = "block";

            }
            if(text=="eee1"){
                        document.getElementById("changeworde").innerHTML = "CHANGE PASSWORD";
                alert("ERROR!!!");
            }
            if(text=="eee"){
                document.getElementById("changeworde").innerHTML = "CHANGE PASSWORD";
                alert("Enter Password fail!!!")
                
            }
        }
    };
    xhttp.open("POST", "UServlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=ISO-8859-15");
    xhttp.send(action);

}

