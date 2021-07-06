



$j(document).ready(function () {
    $j("#clicksignup").click(function () {
        $j("#formsignup").css("display", "block");
        $j("#formlogin").css("display", "none");
        $j("body").css({
            "overflow": "hidden",
            "height": $j(window).height()
        });

    });
});

$j(document).ready(function () {
    $j("#joinus").click(function () {
        $j("#formsignup").css("display", "block");
        $j("#formlogin").css("display", "none");
        $j("body").css({
            "overflow": "hidden",
            "height": $j(window).height()
        });

    });
});
$j(document).ready(function () {
    $j("#ok").click(function () {
        $j("#forminfo").css("display", "none");
    });
});

