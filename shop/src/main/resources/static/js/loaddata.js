
$j('#changewort').bind('keypress', function (e) {
    if (e.keyCode == 13) {
        var check = false;
        var checke = checkValidateZe();
        var checkp = checkValidateZp();
        // Enter pressed... do anything here...
        if (checke && checkp)
        {
            check = true;
        }
        if (check)
            loadU();
    }
});
