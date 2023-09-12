import {Component} from '@angular/core';
import {Router} from '@angular/router';
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
            private readonly auth: AuthService
    ) {}

    public login() {


        if (this.credentials.email && this.credentials.password) {
            this.auth.login(this.credentials.email, this.credentials.password)
                    .subscribe(
                            () => {
                                console.log("User is logged in");
                                this.router.navigateByUrl('/');
                            }
                    );
        }

    }

}
