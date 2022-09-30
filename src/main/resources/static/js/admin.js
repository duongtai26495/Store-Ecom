const alls = []
const images = new Array()
const view_all = $('#all_image_product');


//upload image 
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
				$('#edit_item_image').attr('value',result.data)
				console.log(result.data);
				
			}
			if(type === 'new'){

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

//get all image from db
function getAll(){
$('#show_all_images').toggleClass("show")
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
			$('#all_image_product').append("<div onclick=selectImages('"+image.id+"','"+image.name+ "') class='border-5 image_row' style=background-image:url(/images/"+image.name+"); ></div>");
		})
	
	}).catch(error => console.log('error', error));
  
}
//push images into select and input
function selectImages(id, name){
	
	if(!images.includes(id)){
		images.push(id)
		console.log(name)
		$('#selected_image').append("<div class='border-5 image_row' style=background-image:url(/images/"+name+"); ></div>");
			
	}
	
}


//open menu product


$('#btn_open_new_p').click(()=>{
	 $('#new_p_panel').toggleClass("open");
})

//open left menu
$('#open_menu_left').click(()=>{
	$('#left-menu').toggleClass("open-menu")
})
