import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from '@angular/common/http';
import {isDevMode, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthModule} from './auth/auth.module';
import {ErrorComponent} from './error/error.component';
import {ErrorService} from './error/error.service';
import {HomeComponent} from './home/home.component';
import {ErrorInterceptor} from './interceptors/error.interceptor';
import {JwtTokenInterceptor} from './interceptors/jwt-token.interceptor';
import {XhrInterceptor} from './interceptors/xhr.interceptor';
import {UserService} from './shared/user.service';

const routes: Routes = [
    {path: '', pathMatch: 'full', redirectTo: 'home'},
    {path: 'home', component: HomeComponent},
];

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        ErrorComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        HttpClientXsrfModule.withOptions({
            cookieName: 'XSRF-TOKEN',
            headerName: 'X-XSRF-TOKEN'
        }),
        NgbModule,
        RouterModule.forRoot(routes),
        StoreModule.forRoot({}, {}),
        StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() }),
        AuthModule.forRoot(),
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: JwtTokenInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
        ErrorService,
        UserService,
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
