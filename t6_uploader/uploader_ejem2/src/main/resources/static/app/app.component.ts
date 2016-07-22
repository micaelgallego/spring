import {Component, OnInit} from 'angular2/core';
import {HTTP_PROVIDERS, Http} from 'angular2/http';
import {MultipartItem} from "./multipart-upload/multipart-item";
import {MultipartUploader} from "./multipart-upload/multipart-uploader";

@Component({
  selector: 'app',
  templateUrl: 'app/app.component.html',
  providers: [HTTP_PROVIDERS]
	  
})
export class AppComponent implements OnInit {

	private description: string;
	private file: File;
	
	private images: String[] = [];

	constructor(private http: Http){}
	
	ngOnInit(){
		this.loadImages();
	}
	
	loadImages(){
		
		this.http.get("/images").subscribe(
			response => this.images = response.json();
		)		
	}
	
	selectFile($event) {		
		this.file = $event.target.files[0];
		console.debug("Selected file: " + this.file.name + " type:" + this.file.size + " size:" + this.file.size);		
	}
	
	upload() {
		
		console.debug("Uploading file...");

		if (this.file == null || this.description == null){
			console.error("You have to select a file and set a description.");
			return;
		}		
		
		let formData = new FormData();
			
		formData.append("description", this.description);
		formData.append("file",  this.file);

		let multipartItem = new MultipartItem(new MultipartUploader({url: '/image/upload'}));
		
		multipartItem.formData = formData;
		
		multipartItem.callback = (data, status, headers) => {
						
			if (status == 200){				
				console.debug("File has been uploaded");
				this.loadImages();				
			} else {
				console.error("Error uploading file");
			}
		};
		
		multipartItem.upload();
	}
}
