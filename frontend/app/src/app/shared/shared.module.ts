import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormGroupComponent} from './form-group/form-group.component';

@NgModule({
    declarations: [
        FormGroupComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
        NgbModule,
    ],
    exports: [
        FormGroupComponent,
        FormsModule,
        NgbModule,
    ],
})
export class SharedModule {
}
