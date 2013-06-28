$(function(){
   
    $("#btnSignUp").click(function(){
        $(".erreur").css("display","none");
        $("#contentSignIn").effect("drop", 500, 
            function(){ 
                $("#contentSignUp").fadeIn(500);
            });
    });
    $("#btnSignIn").click(function(){
        $(".erreur").css("display","none");
        $("#contentSignUp").fadeOut(500, 
            function(){ 
                $("#contentSignIn").effect("slide", 500);
            });
    });

});

