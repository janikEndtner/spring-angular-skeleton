import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {map, Observable, tap} from 'rxjs';
import {AuthService} from './auth/auth.service';
import {TSRole} from './models/TSRole';
import {TSUser} from './models/TSUser';
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

    public isAdmin$(): Observable<boolean> {
        return this.user$.pipe(map(u => u.roles.includes(TSRole.ADMIN)));
    }
}
