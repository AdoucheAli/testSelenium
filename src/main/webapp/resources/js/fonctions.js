$(document).ready(function(){

     if ($("#erreurSaisieSignUp").css("height") != null && $("#erreurSaisieSignUp").css("height") > "0px") {
         //$("#wrapFormSignUp").css("display","block");
         alert("ali");
     }

     if( $("#erreurSaisieSignIn").css("height") == "0px") {
     $("#volet").css("top","-" + $("#volet").css("height") );
    }
    
    
//    if( $("#erreurSaisieSignUp").css("height") > "0px") {
//        $("#wrapFormSignIn").css("display","none");
//        $("#wrapFormSignUp").css("display","block");
//    } else if ($("#erreurSaisieSignIn").css("height") > "0px"){
//        $("#wrapFormSignUp").css("display","none");
//        $("#wrapFormSignIn").css("display","block");
//    }
});

$(function(){

    $("#btnSignUp").click(function(){
        $("#erreurSaisieSignUp").css("display","none");
        $("#wrapFormSignIn").effect("drop", 900, 
            function(){ 
                $("#wrapFormSignUp").fadeIn(700,  
                function(){
                     $("#ouvrir").css("border-color","#e67e22");
                     $("#ouvrir").css("background","#d35400");
                });
            });
            
        
    });
 
    $("#ouvrir").hover(function(){
        var temp =  $("#ouvrir").css("background-color");
        $("#ouvrir").css("background",$("#ouvrir").css("color"));
        $("#ouvrir").css("color", temp);
    });
    
    $("#btnSignIn").click(function(){
        $("#wrapFormSignUp").fadeOut(500, 
        function(){ 
            $("#wrapFormSignIn").effect("slide", 900, 
            function(){
                 $("#ouvrir").css("border-color","#9b59b6");
                 $("#ouvrir").css("background","#8e44ad");
            });
        });
       
    });
    
    $("#ouvrir").click(function(){ 
        if ($("#volet").css("top") < "0px") {
            $("#volet").css("top","10px");
        } else {
            $("#volet").css("top","-" + $("#volet").css("height") );
        }       
    });

});

