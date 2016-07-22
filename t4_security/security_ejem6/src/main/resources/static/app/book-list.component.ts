import {Component, OnInit}   from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams, Router} from 'angular2/router';

import {Book, BookService}   from './book.service';
import {LoginService}   from './login.service';

@Component({
    directives: [ROUTER_DIRECTIVES],
    template: `
    <h2>Books</h2>
    <ul class="items">
      <li *ngFor="#book of books">
        <a [routerLink]="['BookDetail', {id: book.id}]">{{book.title}}</a>
      </li>
    </ul>
    <button *ngIf="loginService.isLogged" (click)="newBook()">New book</button>
  `
})
export class BookListComponent implements OnInit {

    books: Book[];

    constructor(private router:Router, private service: BookService, 
    		private loginService:LoginService) {}

    ngOnInit(){
      this.service.getBooks().subscribe(
        books => this.books = books,
        error => console.log(error)
      );
    }

    newBook() {
      this.router.navigate(['BookNew']);
    }
}
