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
  private readonly BASE_PATH = 'auth/'

  public constructor(
          private http: HttpClient
  ) {}

  public login(email:string, password:string ) {
    return this.http.post<TSSessionInformation>( this.BASE_PATH + 'login', {email, password})
            .pipe(
                    tap(res => this.setSession(res)),
                    shareReplay()
            );
  }

  private setSession(authResult: TSSessionInformation) {
    const expiresAt = moment(authResult.expiresAt);

    localStorage.setItem(this.ID_TOKEN, authResult.idToken);
    localStorage.setItem(this.EXPIRES_AT, JSON.stringify(expiresAt.valueOf()) );
  }

  public logout(): void {
    localStorage.removeItem(this.ID_TOKEN);
    localStorage.removeItem(this.EXPIRES_AT);
  }

  public isLoggedIn() {
    if (!this.getExpiration()) {
      return false;
    }
    return moment().isBefore(this.getExpiration());
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

  private getExpiration(): moment.Moment | undefined {
    const expiration = localStorage.getItem(this.EXPIRES_AT);
    if (!expiration) {
      return undefined;
    }
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }

  public getExpiresIn(): number {
    const expAt = this.getExpiration();
    if (!expAt) {
      throw new Error("expires in is undefined");
    }
    return expAt.diff(moment());
  }

  public getToken(): string | null {
    return localStorage.getItem(this.ID_TOKEN);
  }
}
