import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UserDetailsComponent} from '../user-details/user-details.component';
import {UserListComponent} from './user-list/user-list.component';

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
    ],
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
    ]
})
export class UserModule {
}
