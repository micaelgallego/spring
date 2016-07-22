import {Injectable} from 'angular2/core';
import {Http, Headers, RequestOptions} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

export interface Book {  
    id?: number;
    title: string;
    description: string;
}

const URL = 'books/';

@Injectable()
export class BookService {

  constructor(private http: Http) { }

  getBooks() {
    return this.http.get(URL)
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }
  
  getBook(id: number | string) {
	    return this.http.get(URL+id)
	      .map(response => response.json())
	      .catch(error => this.handleError(error));
  }

  saveBook(book: Book) {

    let body = JSON.stringify(book);
    let headers = new Headers({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
    });
    let options = new RequestOptions({ headers });

    return this.http.post(URL, body, options)
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  removeBook(book: Book) {	  
	  
	let headers = new Headers({
	   'X-Requested-With': 'XMLHttpRequest'
	});
	let options = new RequestOptions({ headers });  
	  
    return this.http.delete(URL + book.id, options)
      .map(response => undefined)
      .catch(error => this.handleError(error));
  }

  updateBook(book: Book) {

    let body = JSON.stringify(book);
    let headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    let options = new RequestOptions({ headers });

    return this.http.put(URL + book.id, body, options)
      .map(response => response.json())
      .catch(error => this.handleError(error));
    }

    private handleError(error: any){
      console.error(error);
      return Observable.throw("Server error (" + error.status + "): " + error.text())
    }
}