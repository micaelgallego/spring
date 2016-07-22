import {Component}  from 'angular2/core';
import {RouteParams, Router} from 'angular2/router';
import {Book, BookService}   from './book.service';

@Component({
  template: `
  <div *ngIf="book">
  <h2>Book "{{book.title}}"</h2>
  <div *ngIf="book.id">
    <label>Id: </label>{{book.id}}
  </div>
  <div>
    <label>Title: </label>
    <input [(ngModel)]="book.title" placeholder="title"/>
  </div>
  <div>
    <label>Abstract: </label>
    <textarea [(ngModel)]="book.description" placeholder="description"></textarea>
  </div>
  <p>
    <button (click)="cancel()">Cancel</button>
    <button (click)="save()">Save</button>
  </p>
  </div>`
})
export class BookFormComponent {

  newBook: boolean;
  book: Book;

  constructor(
    private _router:Router,
    routeParams:RouteParams,
    private service: BookService){

      let id = routeParams.get('id');
      if(id){
        service.getBook(id).subscribe(
          book => this.book = book,
          error => console.error(error)
        );
        this.newBook = false;
      } else {
        this.book = { title:'', description:'' };
        this.newBook = true;
      }
  }

  cancel() {
    window.history.back();
  }

  save() {
    this.service.saveBook(this.book).subscribe(
    	book => {}, 
    	error => console.error('Error creating new book: '+error)
    );
    window.history.back();
  }
}
