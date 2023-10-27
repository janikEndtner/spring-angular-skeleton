import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Observable, of, switchMap} from 'rxjs';
import {TSUser} from '../../../models/TSUser';
import {UserService} from '../../../shared/user.service';

@Component({
    selector: 'app-user-details',
    templateUrl: './user-details.component.html',
    styleUrls: ['./user-details.component.css'],
})
export class UserDetailsComponent implements OnInit {

    public user$: Observable<TSUser>;

    constructor(
            private readonly route: ActivatedRoute,
            private readonly userService: UserService,
    ) {
    }

    public ngOnInit(): void {
        this.user$ = this.route.paramMap.pipe(
                switchMap(params => {
                    const id = params.get('id');
                    if (id) {
                        return this.userService.getUserById$(parseInt(id));
                    }
                    return of(new TSUser())
                }),
        )
    }

}
