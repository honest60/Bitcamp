(function($){
	function floatLabel(inputType){
		$(inputType).each(function(){
			var input = $(this).find("input, select, textarea");
      var label = $(this).find("label");
			// on focus add cladd active to label
			input.focus(function(){
				input.next().addClass("active");
        console.log("focus");
			});
			//on blur check field and remove class if needed
			input.blur(function(){
				if(input.val() === '' || input.val() === 'blank'){
					label.removeClass();
				}
			});
		});
	}
	floatLabel(".float-label");
 
  //just for reset button
  $("#clear").on("click" , function(){
    $(".active").removeClass("active");
  });
  
})(jQuery);