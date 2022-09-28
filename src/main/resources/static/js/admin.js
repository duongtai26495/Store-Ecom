const alls = []
const images = new Array()
const view_all = $('#all_image_product');

function uploadNewFile(file, type){
	console.log("type: "+type)
	const btn_new = $('#label_upload_new');
	const btn_edit = $('#label_upload_edit');
	$("#image_name_edit").text("");
	if(file.files && file.files[0]){
		
		console.log(file.files[0].size);
		if(file.files[0].size > 500000){
			$("#image_name").text("The image too big")
			$("#image_name").css({"color":"red"})
			file.files[0].remove;
		}else{
			var formdata = new FormData();
			formdata.append("img", file.files[0]);
		var requestOptions = {
		  method: 'POST',
		  body: formdata,
		  redirect: 'follow'
		};
		
		fetch("/master/uploadImage", requestOptions)
		  .then(response => response.json())
		  .then(result => {
			if(type === 'edit'){
				
				btn_edit.css({
					"background-image": "url(/images/"+result.data+")",
					"background-position": "center center",
					"background-repeat": "no-repeat",
					"background-size":"cover",
					"background-color":"unset"})
				$("#image_name_edit").text("")
				$('#edit_item_image').attr('value',result.data)
				console.log(result.data);
				
			}
			if(type === 'new'){
/*				btn_new.css({
					"background-image": "url(/images/"+result.data+")",
					"background-position": "center center",
					"background-repeat": "no-repeat",
					"background-size":"cover",
					"background-color":"unset",
					"height":"350px"}) 
				$("#image_name").text("") */
				$('#new_item_image').attr('value',result.data)
				getAll()
				console.log(result.data);
			}
			file.files[0].remove;
			})
		  .catch(error => console.log('error', error));
			}
		}
		
}

 function getAll(){
$('#show_all_images').toggle(()=>
$('#show_all_images').css({
	'transition':'.3s',
	'width':'auto'
}))
alls.length = 0;
$('#all_image_product').empty()
let url = "/master/all_images";
var requestOptions = {
  method: 'GET',
  redirect: 'follow'
};
 fetch(url, requestOptions)
  .then(response => response.json())
  .then(result => {
		alls.push(...result.data)
		alls.forEach((image)=>{
			$('#all_image_product').append("<img onclick=selectImages('"+image.id+"','"+image.name+ "') class='border-5 image_row' style=background-image:url(/images/"+image.name+"); />");
		})
	
	}).catch(error => console.log('error', error));
  
}

function selectImages(id, name){
	if(!images.includes(id)){
		images.push(id)
		console.log(name)
		$('#selected_image').append("<img class='border-5 image_row' style=background-image:url(/images/"+name+"); />");
			
		$('#new_item_image').attr('value',images);
	}
	
}
