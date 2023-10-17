import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, ReplaySubject} from 'rxjs';
import {AuthService} from '../auth/auth.service';
import {TSUser} from '../models/TSCredentials';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private _user = new ReplaySubject<TSUser>(1);

    constructor(
            private readonly http: HttpClient,
            private readonly authService: AuthService
    ) {
        this.reloadUser();
    }

    public reloadUser(): void {
        if (!this.authService.isLoggedIn()) {
            return;
        }
        this.http.get<TSUser>('/api/user/current')
                .subscribe(u => this._user.next(u));
    }

    public get user$(): Observable<TSUser> {
        return this._user.asObservable();
    }
}
