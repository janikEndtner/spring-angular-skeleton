import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ErrorService} from './error.service';
import {RuntimeError} from './RuntimeError';

@Component({
    selector: 'app-error',
    templateUrl: './error.component.html',
    styleUrls: ['./error.component.css'],
})
export class ErrorComponent implements OnInit {

    public errors$: Observable<RuntimeError[]>;

    constructor(
            private readonly errorService: ErrorService,
    ) {
      this.errors$ = this.errorService.errors$;
    }

    public ngOnInit(): void {
    }

    public removeMessage(e: RuntimeError): void {
        this.errorService.removeError(e);
    }
}
