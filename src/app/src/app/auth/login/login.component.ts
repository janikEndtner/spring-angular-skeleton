import {Component} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {noop, tap} from 'rxjs';
import {AuthService} from '../auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {

    public credentials = {username: '', password: ''};
    public error: boolean = false;

    public form: UntypedFormGroup;

    public constructor(
            private readonly router: Router,
            private readonly auth: AuthService,
            private fb: UntypedFormBuilder,
    ) {
        this.form = fb.group({
            email: ['test@angular-university.io', [Validators.required]],
            password: ['test', [Validators.required]],
        });
    }

    public login() {

        const val = this.form.value;

        this.auth.login(val.email, val.password)
                .pipe(
                        tap(user => {

                            console.log(user);

                            // this.store.dispatch(login({user}));

                            this.router.navigateByUrl('/courses');

                        }),
                )
                .subscribe({
                  next: () => {},
                  error: () => alert('Login Failed')
                })

    }

}
