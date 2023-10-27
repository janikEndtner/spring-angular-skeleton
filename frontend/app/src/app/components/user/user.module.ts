import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {NgbDropdownConfig} from '@ng-bootstrap/ng-bootstrap';
import {SharedModule} from '../../shared/shared.module';
import {UserDetailsComponent} from './user-details/user-details.component';
import {UserListComponent} from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';

const routes: Routes = [
    {
        path: '',
        component: UserListComponent,
    },
    {
        path: 'add',
        component: UserDetailsComponent,
    },
    {
        path: 'edit/:id',
        component: UserDetailsComponent,
    },
];

@NgModule({
    declarations: [
        UserListComponent,
        UserDetailsComponent,
        UserFormComponent,
    ],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        SharedModule,
    ],
    providers: [
        NgbDropdownConfig
    ]
})
export class UserModule {
}
