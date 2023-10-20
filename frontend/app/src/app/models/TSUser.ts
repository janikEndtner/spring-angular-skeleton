import {TSRole} from './TSRole';

export class TSUser {
    private _id: number;
    private _email: string;
    private _roles: TSRole[];

    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get email(): string {
        return this._email;
    }

    public set email(value: string) {
        this._email = value;
    }

    public get roles(): TSRole[] {
        return this._roles;
    }

    public set roles(value: TSRole[]) {
        this._roles = value;
    }
}
