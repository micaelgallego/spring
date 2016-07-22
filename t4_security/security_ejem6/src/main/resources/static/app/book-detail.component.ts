import {Component}  from 'angular2/core';
import {RouteParams, Router} from 'angular2/router';

import {Book, BookService}   from './book.service';
import {LoginService}   from './login.service';

@Component({
    template: `
  <div *ngIf="book">
  <h2>Book "{{book.title}}"</h2>
  <div>
    <p>{{book.description}}</p>
  </div>
  <p>
    <button *ngIf="loginService.isLogged && loginService.isAdmin" (click)="removeBook()">Remove</button>
    <button *ngIf="loginService.isLogged" (click)="editBook()">Edit</button>
    <br>
    <button (click)="gotoBooks()">All Books</button>
  </p>
  </div>`
})
export class BookDetailComponent {

    book: Book;

    constructor(private router: Router, routeParams: RouteParams, private service: BookService,
    		private loginService: LoginService) {
    	
        let id = routeParams.get('id');
        service.getBook(id).subscribe(
            book => this.book = book,
            error => console.error(error)
        );
    }

    removeBook() {
        let okResponse = window.confirm("Do you want to remove this book?");
        if (okResponse) {
            this.service.removeBook(this.book).subscribe(
                _ => this.router.navigate(['Books']),
                error => console.error(error)
            )
        }
    }

    editBook() {
        this.router.navigate(['BookEdit', { id: this.book.id }]);
    }

    gotoBooks() {
        this.router.navigate(['Books']);
    }
}
