import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import * as moment from 'moment';
import {shareReplay, tap} from 'rxjs';
import {TSSessionInformation} from '../models/TSSessionInformation';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly ID_TOKEN = 'id_token';
  private readonly EXPIRES_AT = 'expires_at';

  public constructor(private http: HttpClient) {

  }

  public login(email:string, password:string ) {
    return this.http.post<TSSessionInformation>('/api/auth/login', {email, password})
            .pipe(
                    tap(res => this.setSession(res)),
                    shareReplay()
            );
  }

  private setSession(authResult: TSSessionInformation) {
    const expiresAt = moment().add(authResult.expiresAt,'second');

    localStorage.setItem(this.ID_TOKEN, authResult.idToken);
    localStorage.setItem(this.EXPIRES_AT, JSON.stringify(expiresAt.valueOf()) );
  }

  public logout() {
    localStorage.removeItem(this.ID_TOKEN);
    localStorage.removeItem(this.EXPIRES_AT);
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

  private getExpiration() {
    const expiration = localStorage.getItem(this.EXPIRES_AT);
    if (!expiration) {
      throw Error('expires_at not found');
    }
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }
}
