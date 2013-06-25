$(function(){

    $("#btnSignUp").click(function(){
        $("#wrapFormSignIn").effect("drop", 500, function(){ $("#wrapFormSignUp").fadeIn(300);});
       
        
    });
 
    $("#btnSignIn").click(function(){
        $("#wrapFormSignUp").fadeOut(300, function(){ $("#wrapFormSignIn").effect("slide", 500);});
            
    });
    
    $("#ouvrir").click(function(){
        if ($("#volet").css("top") == "-440px") {
            $("#volet").css("top","10px");
        } else {
            $("#volet").css("top","-440px");
        }
        
    });
    
    $("#submitSignIn").click(function(){
        $("#volet").css("top","10px");
    });
    
    $("#submitSignUp").click(function(){
        $("#volet").css("top","10px");
    });
});

