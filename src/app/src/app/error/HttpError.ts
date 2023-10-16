import * as moment from 'moment/moment';
import {RuntimeError} from './RuntimeError';

export class HttpError extends RuntimeError {

    private readonly _status: number;
    private readonly _path: string;

    constructor(statusCode: number, path: string, message: string, timestamp: moment.Moment) {
        super(HttpError.getErrorMessage(timestamp, statusCode, path));
        this._status = statusCode;
        this._path = path;
    }

    private static getErrorMessage(timestamp: moment.Moment, statusCode: number, path: string) {
        return `A HttpError occured at ${timestamp.format()} with status code ${statusCode},
        path: ${path}`
    }
}
