$(document).ready(function(){
    $(window).scroll(function(){
       if($(this).scrollTop()>250)
       {
           $(".custom-scroll").fadeIn();
       }
       else
       {    
           $(".custom-scroll").fadeOut();
       } 
    });
    $('.custom-scroll').click(function(){
        $('html,body').animate({scrollTop:0});
    });
});