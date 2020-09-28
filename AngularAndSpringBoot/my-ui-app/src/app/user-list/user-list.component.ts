import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  page:number=0;
  users : User[];
  pages : number[];

  constructor(private userService: UserServiceService) { }

  setPage(i:number,event:any) {
    event.preventDefault();
    this.page = i;
    this.getUsers();  
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers(this.page).subscribe(
      data=> {
        this.users = data['content'];
        this.pages = new Array(data['totalPages']);
      },
      error=>{
        console.log(error);
      }
    );
  }

}
