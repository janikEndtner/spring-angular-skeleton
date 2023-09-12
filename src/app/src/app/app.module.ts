import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {isDevMode, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthModule} from './auth/auth.module';
import {HomeComponent} from './home/home.component';
import {JwtTokenInterceptor} from './interceptors/jwt-token.interceptor';
import {XhrInterceptor} from './interceptors/xhr.interceptor';

const routes: Routes = [
    {path: '', pathMatch: 'full', redirectTo: 'home'},
    {path: 'home', component: HomeComponent},
];

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        NgbModule,
        RouterModule.forRoot(routes),
        StoreModule.forRoot({}, {}),
        StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() }),
        AuthModule.forRoot(),
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: JwtTokenInterceptor, multi: true},
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
