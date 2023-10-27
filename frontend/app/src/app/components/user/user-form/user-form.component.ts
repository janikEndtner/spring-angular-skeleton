import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgbDropdownConfig} from '@ng-bootstrap/ng-bootstrap';
import {TSRole} from '../../../models/TSRole';
import {TSUser} from '../../../models/TSUser';

@Component({
    selector: 'app-user-form',
    templateUrl: './user-form.component.html',
    styleUrls: ['./user-form.component.css'],
})
export class UserFormComponent {

    submitted = false;

    selectedRoles = Object.values(TSRole)
            .map(r => {
                return {role: r, checked: false};
            });

    @Input({required: true})
    public user!: TSUser;

    @Output()
    public userChanged: EventEmitter<TSUser> = new EventEmitter<TSUser>;

    constructor(config: NgbDropdownConfig) {
        config.autoClose = "outside";
    }

    onSubmit(): void {
        this.submitted = true;
        this.userChanged.emit()
    }

    protected readonly Object = Object;

    public getSelectedRolesAsString(): string {
        const roleStr = this.selectedRoles
                .filter(r => r.checked)
                .map(r => r.role)
                .join(', ');
        if (roleStr.length == 0) {
            return "Keine Rollen"
        }
        return roleStr;
    }
}
