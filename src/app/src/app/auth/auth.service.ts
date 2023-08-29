import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {TSUser} from '../models/TSCredentials';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
          private http: HttpClient
  ) { }

  login(email:string, password:string): Observable<TSUser> {
    return this.http.post<TSUser>('/api/login', {email,password});
  }
}
