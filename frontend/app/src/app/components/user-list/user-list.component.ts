import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {TSUser} from '../../models/TSUser';
import {UserService} from '../../shared/user.service';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {

    public allUsers$: Observable<TSUser[]>

    public constructor(
            private readonly userService: UserService,
    ) {
    }

    public ngOnInit(): void {
        this.allUsers$ = this.userService.getAllUsers$();
    }

}
