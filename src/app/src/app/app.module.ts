import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgModule, isDevMode} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HomeComponent} from './home/home.component';
import {AuthModule} from './auth/auth.module';
import {XhrInterceptor} from './xhr.interceptor';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';

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
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
