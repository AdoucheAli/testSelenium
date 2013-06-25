$(document).ready(function(){
    alert($("#erreurSaisie").css("height"));
    if( $("#erreurSaisie").css("height") == "0px") {
     $("#volet").css("top","-" + $("#volet").css("height") );
    }
});

$(function(){

    $("#btnSignUp").click(function(){
        $("#wrapFormSignIn").effect("drop", 900, 
            function(){ 
                $("#wrapFormSignUp").fadeIn(700,  
                function(){
                     $("#ouvrir").css("border-color","#e67e22");
                     $("#ouvrir").css("background","#d35400");
                     $("#erreurSaisie").css("display","none");
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

