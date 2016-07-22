import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {HTTP_PROVIDERS, Http} from 'angular2/http';

import {BookListComponent} from './book-list.component';
import {BookDetailComponent} from './book-detail.component';
import {BookFormComponent} from './book-form.component';
import {LoginComponent} from './login.component';

import {BookService} from './book.service';
import {LoginService} from './login.service';

import {Alert} from 'ng2-bootstrap/ng2-bootstrap';

@Component({
  selector: 'app',
  templateUrl: 'app/app.component.html',
  providers:  [BookService, LoginService, HTTP_PROVIDERS],
  directives: [LoginComponent, ROUTER_DIRECTIVES, Alert]
})
@RouteConfig([
  {path: '/books', name: 'Books', component: BookListComponent, useAsDefault: true},
  {path: '/book/:id', name: 'BookDetail', component: BookDetailComponent},
  {path: '/book/new', name: 'BookNew', component: BookFormComponent},
  {path: '/book/edit/:id', name: 'BookEdit', component: BookFormComponent}
])
export class AppComponent {	
}
