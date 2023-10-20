import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import * as moment from 'moment';
import {catchError, Observable} from 'rxjs';
import {ErrorService} from '../error/error.service';
import {HttpError} from '../error/HttpError';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private readonly errorService: ErrorService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
                catchError(async err => {
                    if (err instanceof HttpErrorResponse) {
                        const e = new HttpError(
                                err.error.status,
                                err.error.path,
                                err.error.message,
                                moment(err.error.timestamp)
                                );
                        this.errorService.addHttpError(e);
                    }
                    throw err;
                }));
    }
}
