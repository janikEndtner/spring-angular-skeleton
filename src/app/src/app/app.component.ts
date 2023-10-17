import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable, tap} from 'rxjs';
import {AuthService} from './auth/auth.service';
import {TSUser} from './models/TSCredentials';
import {UserService} from './shared/user.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {

    public user$: Observable<TSUser>;

    constructor(
            private readonly router: Router,
            private readonly auth: AuthService,
            private readonly userService: UserService
    ) {
    }

    public ngOnInit(): void {
        this.user$ = this.userService.user$.pipe(tap(u => console.log(u)));
    }

    public logout(): void {
        return this.auth.logout();
    }

    public isAdmin(): boolean {
        // TODO: implement admin functionality
        return true;
    }
}
