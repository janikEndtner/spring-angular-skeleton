import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {StoreModule} from '@ngrx/store';
import {AuthService} from './auth.service';
import {LoginComponent} from './login/login.component';
import * as fromAuth from './reducers';

@NgModule({
    declarations: [LoginComponent],
    imports: [
        CommonModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forChild([{path: 'login', component: LoginComponent}]),
        StoreModule.forFeature(fromAuth.authFeatureKey, fromAuth.reducers, {metaReducers: fromAuth.metaReducers}),
    ],
})
export class AuthModule {
    static forRoot(): ModuleWithProviders<AuthModule> {
        return {
            ngModule: AuthModule,
            providers: [
                AuthService,
            ],
        }
    }
}
