

     

// When the user clicks anywhere outside of the modal, close it

            $j(document).ready(function () {
                $j("#editac").click(function () {
                    $j("#formedit").css("display", "block");
                    $j("body").css({
                        "overflow": "hidden",
                        "height": $j(window).height()
                    });

                });
            });
