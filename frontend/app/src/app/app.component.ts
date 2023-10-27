import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {filter, of, Subscription, switchMap, timer} from 'rxjs';
import {AppRoutingModule} from './app-routing.module';
import {AuthService} from './auth/auth.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {

    private subscription: Subscription;

    constructor(
            private readonly authService: AuthService,
            private readonly router: Router
    ) {
    }

    public ngOnInit(): void {
        if (this.authService.isLoggedOut()) {
            // in this case token expired while application was not running. we have to
            // delete token before login
            this.authService.logout();
            this.router.navigate(['/login']);
            return;
        }
        // user should be redirected to login, after expiration date of token is reached
        this.subscription = of(this.authService.getExpiresIn())
                .pipe(
                        filter(t => !!t),
                        switchMap(t => timer(t - 1000)) // subtract one second to be sure, user is redirected early enough
                )
                .subscribe(expired => {
                    this.router.navigate(['/login']);
                });
    }

    public ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }


}
