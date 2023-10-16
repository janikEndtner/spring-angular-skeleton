import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpError} from './HttpError';
import {RuntimeError} from './RuntimeError';

@Injectable({
    providedIn: 'root'
})
export class ErrorService {
    private _errors = new BehaviorSubject<RuntimeError[]>([]);

    public addHttpError(httpError: HttpError): void {
        const newList = [...this._errors.value, httpError];
        this._errors.next(newList);
    }

    public get errors$(): Observable<RuntimeError[]> {
        return this._errors.asObservable();
    }
}
