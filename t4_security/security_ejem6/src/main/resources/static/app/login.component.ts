import {Component}  from 'angular2/core';
import {RouteParams, Router} from 'angular2/router';
import {LoginService}   from './login.service';

@Component({
  selector: 'login',
  templateUrl: 'app/login.component.html'
})
export class LoginComponent {

  constructor(private loginService: LoginService){}
    
  logIn(event: any, user: string, pass: string){
	  
	  event.preventDefault();
	  
	  this.loginService.logIn(user, pass).subscribe(
	      user => console.log(user),
	      error => alert("Invalid user or password")
      );
  }
  
  logOut(){
	this.loginService.logOut().subscribe(
		response => {}, 
		error => console.log("Error when trying to log out: "+error)
	);
  }
  
}
