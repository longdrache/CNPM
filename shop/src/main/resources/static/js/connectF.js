
function statusChangeCallback(response) { // Called with the results from FB.getLoginStatus().
//            console.log('statusChangeCallback');
//            console.log(response); // The current login status of the person.



    if (response.status === 'connected') { // Logged into your webpage and Facebook.
        var at = response.authResponse.accessToken;
        var userid = response.authResponse.userID;
       
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', function (response) {
            console.log('Successful login for: ' + response.name);
           
            loginF(userid,at,response.name);


        });
        
        

    } else { // Not logged into your webpage or we are unable to tell.

    }
}
function loginF(userid, password, name) {
    var action = "email=" + userid + "&password=" + password + "&name=" + name;



                document.getElementById("changewort").innerHTML = "SIGN IN";
                document.getElementById("loginuser").innerHTML = "Hi, " + name
               
                document.getElementById("formlogin").style.display = "none";
                document.getElementById("help").style.display = "none";
                document.getElementById("joinus").style.display = "none";
                document.getElementById("login").style.display = "none";
                document.getElementById("editac").style.display = "none";
                document.body.style.overflow = "auto";
                document.getElementsByClassName("twoicon")[0].style.display = "block";
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'flogin');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=ISO-8859-15');
   
    xhr.send(action);

}

function checkLoginState() { // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function (response) { // See the onlogin handler
        statusChangeCallback(response);

    });
}


window.fbAsyncInit = function () {
    FB.init({
        appId: '1588094261381402',
        cookie: true, // Enable cookies to allow the server to access the session.
        xfbml: true, // Parse social plugins on this webpage.
        version: 'v9.0' // Use this Graph API version for this call.
    });


    FB.getLoginStatus(function (response) { // Called after the JS SDK has been initialized.
        statusChangeCallback(response); // Returns the login status.
    });
};


  