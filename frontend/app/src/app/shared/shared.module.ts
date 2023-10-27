import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {FormGroupComponent} from './form-group/form-group.component';

@NgModule({
    declarations: [
        FormGroupComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
    ],
    exports: [
        FormGroupComponent,
        FormsModule,
    ],
})
export class SharedModule {
}
