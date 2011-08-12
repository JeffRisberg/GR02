 (function($) {
	  collapseExpand = function() {
	  $(".titleHeading").css("cursor","pointer").click(function()
  		{ 
  		var panelId =$(this).parent().attr("id");
   		var headingId =$(this).attr("id");
  		var subCompId = $(this).next().attr("id");
  		
   		//If restore button is clicked
  		if ($(this).is('.restore')) {
  			//show appropriate box
  		$(".marketplacePanel").not("#"+panelId).show();
  	 	$(".titleHeading").not("#"+headingId ).show();
  		$(".content").not("#"+subCompId).show();
  		
		 // retrieve the old stored size
  		 var box = $(this).data('size');
   		 //apply it to content
  		 var oldHeight = box[1];
  	 	 $("#subCompId").height(oldHeight);
  	 	 
  	 	 $(".content").width(318); 
  	 	  $(".marketplacePanel").width(318);
  		 
  	 	 
  	 	 $(this).removeClass('restore');
  		 $(this).addClass('expand');
  		
  		  
  		}else{
  	 		//Hide other panels
  	 	$(".marketplacePanel").not("#"+panelId).hide();
  		$(".titleHeading").not("#"+headingId).hide();
  		$(".content").not("#"+subCompId).hide();
  		
  			$(this)
            // save original box size
            .data('size', [$("#subCompId").width(), $("#subCompId").height()]);
            
            //modify the height and width 
 		 	$("#subCompId").height($(window).height()*1.1);
 		 	
 		  	$(".marketplacePanel").width(645);
 		  	$(".content").width(645);	
 		 
 		 
 		  $(this).addClass('restore');
 		  $(this).removeClass('expand');
 		   
 		    	  
 		}
   	 	 	
  		}); 
	}
	})(jQuery);