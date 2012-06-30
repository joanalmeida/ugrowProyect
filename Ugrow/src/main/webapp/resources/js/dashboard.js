document.getElementById("Delete").onclick=confirmPost;
document.getElementById("SelectAll").onclick=selectCheckboxes;

function confirmPost(){
	var autoDelete=false;
	for(i=0;i<document.DeleteForm.elements.length; i++){
		if(document.DeleteForm.elements[i].type=="checkbox"){
			if(document.DeleteForm.elements[i].checked==true){
				if(document.DeleteForm.elements[i].value==document.getElementById("userId").value){
					autoDelete=true;
				}
			}
		}
	}
	if(autoDelete){
		alert("You can't delete yourself.");
	}else{
		var agree = confirm("You are about to delete users. If you confirm this operation these users will not be able to access the application. Do you want to delete these users?");
		if (agree){
			document.forms["DeleteForm"].submit();
		}
	}
}

function selectCheckboxes(){
	var checking=false
	for(i=0;i<document.DeleteForm.elements.length; i++){
		if(document.DeleteForm.elements[i].type=="checkbox"){
			if(document.DeleteForm.elements[i].checked==false){
				checking=true
			}
		}
	}
	for(i=0;i<document.DeleteForm.elements.length; i++){
		if(document.DeleteForm.elements[i].type=="checkbox"){
			document.DeleteForm.elements[i].checked=checking;
		}
	}
}