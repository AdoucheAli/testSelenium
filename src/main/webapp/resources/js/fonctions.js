$(document).ready(function(){

    //     if ($("#erreurSaisieSignUp").css("height") != null && $("#erreurSaisieSignUp").css("height") > "0px") {
    //         //$("#wrapFormSignUp").css("display","block");
    //         alert("ali");
    //     }

    if( $("#erreurSaisieSignIn").css("height") == "0px") {
        $("#volet").css("top","-" + $("#volet").css("height") );
    }
    if ( $("#isSubmittedSignIn").attr("value") != null && $("#isSubmittedSignIn").attr("value") == "true"){
        $("#isSubmittedSignUp").attr("value","false");
        $("#wrapFormSignUp").css("display","none");
        $("#wrapFormSignIn").css("display","block");
    }
    if ( $("#isSubmittedSignUp").attr("value") != null && $("#isSubmittedSignUp").attr("value") == "true"){
        $("#isSubmittedSignIn").attr("value","false");
        $("#wrapFormSignUp").css("display","block");
        $("#wrapFormSignIn").css("display","none");
    }
     

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

    $("#submitSignIn").click(function(){ 
        $("#isSubmittedSignIn").attr("value", "true");
    });
    
    $("#submitSignUp").click(function(){ 
        $("#isSubmittedSignUp").attr("value", "true");
    });
    
    $("#ouvrir").hover(function(){
        var temp =  $("#ouvrir").css("background-color");
        $("#ouvrir").css("background",$("#ouvrir").css("color"));
        $("#ouvrir").css("color", temp);
    });
});

