import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {tap} from 'rxjs';
import {UserService} from '../../shared/user.service';
import {AuthService} from '../auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {

    public credentials = {email: '', password: ''};
    public error: boolean = false;

    public constructor(
            private readonly router: Router,
            private readonly auth: AuthService,
            private readonly userService: UserService,
    ) {}

    public login() {

        if (this.credentials.email && this.credentials.password) {
            this.auth.login(this.credentials.email, this.credentials.password)
                    .pipe(tap(() => this.userService.reloadUser()))
                    .subscribe(
                            () => {
                                console.log("User is logged in");
                                this.router.navigateByUrl('/');
                            }
                    );
        }

    }

}
