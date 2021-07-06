var $j = jQuery.noConflict();

     

// When the user clicks anywhere outside of the modal, close it

            $j(document).ready(function () {
                $j("#login").click(function () {
                    $j("#formlogin").css("display", "block");
                    $j("body").css({
                        "overflow": "hidden",
                        "height": $j(window).height()
                    });
                    return false;
                  
                });
            });
              $j(document).ready(function () {
                $j("#clicklogin1").click(function () {
                    $j("#formlogin").css("display", "block");
                    $j("#formsignup").css("display", "none");

                });
            });
             $j(document).ready(function () {
                $j("#clicklogin2").click(function () {
                    $j("#formlogin").css("display", "block");
                    $j("#formsignup").css("display", "none");

                });
            });
            