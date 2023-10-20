import * as moment from 'moment';

export class RuntimeError extends Error {
    constructor(message?: string) {
        if (!message) {
            message = RuntimeError.getDefaultMessage(message);
        }
        super(message);
        console.error(message);
    }

    private static getDefaultMessage(message?: string): string {
        return `An error occured at ${moment().format()}
        message: ${message}`;
    }
}
