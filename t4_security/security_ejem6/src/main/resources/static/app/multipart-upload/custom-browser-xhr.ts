import {BrowserXhr} from 'angular2/http';
import {Injectable} from 'angular2/core';

@Injectable()
export class CustomBrowserXhr extends BrowserXhr {
  
  constructor() {
	  super();
  }
  
  build(): any {
    let xhr = super.build();
    xhr.withCredentials = true;
    return <any>(xhr);
  }
}