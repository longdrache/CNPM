
function onSignIn(googleUser) {
//        var id_token = googleUser.getAuthResponse().id_token;
//
    var profile = googleUser.getBasicProfile();
//  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
//  console.log('Name: ' + profile.getName());
//  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    //console.log(id_token);
    loginG(profile.getEmail(), profile.getId(), profile.getName());
}
function onSuccess(googleUser) {
    console.log('Logged in as: ' + googleUser.getBasicProfile().getName());

}

function onFailure(error) {
    console.log(error);
}
function renderButton() {
    gapi.signin2.render('g-signin2', {
        'scope': 'profile email',
        'width': 313,
        'height': 40,
        'longtitle': true,
        'theme': 'light',
        'onsuccess': onSignIn,
        'onfailure': onFailure
    });
}

function loginG(userid, password, name) {
    var action = "email=" + userid + "&password=" + password + "&name=" + name;





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
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed outG.');
    });
    FB.logout();
}