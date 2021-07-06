$j("#forder").click(function(){
$j("#formorder").css("display","block");


});
function outfd(){
$j("#formorder").css("display","none");

}
$j("#orderc").keyup(function(){
var a =$j("#orderc").val().replaceAll(" ","");
$j("#orderc").val(a);
});