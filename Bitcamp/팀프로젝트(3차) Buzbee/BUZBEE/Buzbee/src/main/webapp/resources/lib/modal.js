/**
 * 
 */
// Get the modal
        var modal = document.getElementById('myModal');
 
        // Get the button that opens the modal
        var btn = document.getElementById("myBtn");
        
        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];
        
        var buzing = document.getElementById("modal-sendBtn");  

        var count = document.getElementById('modal-textCount');      
        
        $("#modal-text").keyup(function(){
			if($("#modal-text").val().trim().length > 0) {
				$('#modal-sendBtn').attr("class", "modal-button");
				$('#modal-sendBtn').attr('disabled', false);
			} 
			else $('#modal-sendBtn').attr("class", "modal-button disabled");
			
			$('#modal-textCount').html($("#modal-text").val().length);	
			
			if($("#modal-text").val().length > 140) {
				$('#modal-sendBtn').attr("class", "modal-button disabled");
				$('#modal-textCount').css("color", "red");	
				$('#modal-sendBtn').attr('disabled', true);
			} else {
				$('#modal-textCount').css("color", "#31708f");					
			}
		});
        
        // When the user clicks on the button, open the modal (처음 모달 띄웠을 때)
        btn.onclick = function() {
        	$('#modal-text').val('');
        	$(".img-area-main").show();
    	    if (count.innerText) {
    	    	count.innerText = "0";
	        }
    	    $('#modal-sendBtn').attr("class", "modal-button disabled");
			$('#modal-sendBtn').attr('disabled', true);
            modal.style.display = "block";             
        }
 
        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
        	modal.style.display = "none";  
            
        }
        
        // 버징하기 버튼
        buzing.onclick = function() {        	
        	if($("#modal-text").val().length>0 && $("#modal-text").val().length<=140) {          			
        		modal.style.display = "none";         		
        	}
        }
        
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }    
        }
