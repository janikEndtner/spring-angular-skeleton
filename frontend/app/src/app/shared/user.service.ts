import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {map, Observable, ReplaySubject} from 'rxjs';
import {AuthService} from '../auth/auth.service';
import {TSUser} from '../models/TSUser';
import {RestUtil} from '../utils/RestUtil';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private _user = new ReplaySubject<TSUser>(1);
    private readonly BASE_PATH = 'user/';

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
        this.http.get(this.BASE_PATH + 'current')
                .pipe(map(rest => RestUtil.restToUser(rest)))
                .subscribe(u => this._user.next(u));
    }

    public get user$(): Observable<TSUser> {
        return this._user.asObservable();
    }
}
