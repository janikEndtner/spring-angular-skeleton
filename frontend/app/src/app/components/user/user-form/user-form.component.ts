import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TSUser} from '../../../models/TSUser';

@Component({
    selector: 'app-user-form',
    templateUrl: './user-form.component.html',
    styleUrls: ['./user-form.component.css'],
})
export class UserFormComponent {

    submitted = false;

    @Input({required: true})
    public user!: TSUser;

    @Output()
    public userChanged: EventEmitter<TSUser> = new EventEmitter<TSUser>;

    onSubmit(): void {
        this.submitted = true;
        this.userChanged.emit()
    }

}
