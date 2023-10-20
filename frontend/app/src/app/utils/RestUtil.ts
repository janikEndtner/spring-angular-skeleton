import {TSUser} from '../models/TSUser';

export class RestUtil {
    public static restToUser(restObj: any) {
        const user = new TSUser();
        user.id = restObj.id;
        user.email = restObj.email;
        user.roles = restObj.userRoleDTOs;
        return user;
    }
}
