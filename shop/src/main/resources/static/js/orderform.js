
// When the user clicks anywhere outside of the modal, close it

            $j(document).ready(function () {
                $j("#oder").click(function () {
                    $j("#formorders").css("display", "block");
                    $j("body").css({
                        "overflow": "hidden",
                        "height": $j(window).height()
                    });

                });
            });
