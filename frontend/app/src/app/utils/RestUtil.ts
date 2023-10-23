import {TSUser} from '../models/TSUser';

export class RestUtil {

    public static restToUsers(restObj: any): TSUser[] {
        return restObj.map((u: any) => this.restToUser(u));
    }
    public static restToUser(restObj: any): TSUser {
        const user = new TSUser();
        user.id = restObj.id;
        user.email = restObj.email;
        user.roles = restObj.userRoleDTOs;
        return user;
    }
}
