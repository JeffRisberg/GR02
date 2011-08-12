 (function($) {
	  collapseExpand = function() {
	    
  		//toggle the component with class msg_body
 		 $(".titleHeading").css("cursor","pointer").click(function()
  		{
  		//select the sub components
  		var mainComp = $(this);
	
		  //select all subcomponents
  		var subComp = mainComp.next();  		
 		  
 		  if (subComp.is(':hidden')) {
 		    //alert("opening");
			  mainComp.removeClass('closed');
        mainComp.addClass('open');
		  } else {
		    //alert("closing");			 
        mainComp.removeClass('open');
        mainComp.addClass('closed');
		  }   	 
   	 	
   	 	subComp.slideToggle("slow");
   	 	 	
  		});
  		
  		$(".maximize").click(function()
  		{ 
  		var parentId =$(this).parent().attr("id");
  		var subCompId = $(this).parent().next().attr("id");
  		 
  		
  		//If restore button is clicked
  		if ($(this).is('.restore')) {
  			//show appropriate box
  			
  		$(".titleHeading").not("#"+parentId ).show();
  		$(".content").not("#"+subCompId).show();
		 // retrieve the old stored size
  		 var box = $(this).data('size');
   		 //apply it to content
  		 var oldHeight = box[1];
  	 	 $("#subCompId").height(oldHeight);
  		 $(this).removeClass('restore');
  		 $(this).addClass('expanded');
  		 
  		}else{
  	 		//Hide other panels
  		$(".titleHeading").not("#"+parentId).hide();
  		$(".content").not("#"+subCompId).hide();
  		
  			$(this)
            // save original box size
            .data('size', [$("#subCompId").width(), $("#subCompId").height()]);
            
            //modify the height by some factor
 		 	$("#subCompId").height($(window).height()*1.1);
 		 	
 		  $(this).addClass('restore');
 		  $(this).removeClass('expanded');
 		  	  
 		}
 		return false; 	
  		}); 
	}
	})(jQuery);