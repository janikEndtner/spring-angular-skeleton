import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from './auth/auth.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent {

    constructor(
            private readonly router: Router,
            private readonly auth: AuthService
    ) {
    }

    public isLoggedIn(): boolean {
        return this.auth.isLoggedIn();
    }

    public logout(): void {
        return this.auth.logout();
    }

    public isAdmin(): boolean {
        // TODO: implement admin functionality
        return true;
    }
}
