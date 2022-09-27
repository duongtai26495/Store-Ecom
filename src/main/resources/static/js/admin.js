
function uploadNewFile(file, type){
	
	
	if(file.files && file.files[0]){
		var formdata = new FormData();
		formdata.append("img", file.files[0]);
		console.log(file.files[0]);
		var requestOptions = {
		  method: 'POST',
		  body: formdata,
		  redirect: 'follow'
		};
		
		fetch("/master/uploadImage", requestOptions)
		  .then(response => response.json())
		  .then(result => {
			if(type === 'edit'){
				$('#image_select_edit').attr('src', '/images/'+result.data+'')
				$('#new_item_image').attr('value',result.data)
			}
			if(type === 'new'){
				$('#image_select_new').attr('src', '/images/'+result.data+'')
				$('#new_item_image').attr('value',result.data)
				console.log(result.data);
			}
			
			
			})
		  .catch(error => console.log('error', error));
			}
			
	
}


