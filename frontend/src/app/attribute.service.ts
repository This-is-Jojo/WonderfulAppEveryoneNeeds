import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from './message.service';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {StringResponse} from './string-response';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AttributeService {
  private attrApiUrl = '//localhost:8080/attributes';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  getAttributesMap(objectTypeId: number): Observable<Map<number, string>> {
    const url = `${this.attrApiUrl}/getAttributesMapForObjectType=${objectTypeId}`;

    return this.http.get<Map<number, string>>(url).pipe(
      tap(_ => this.log(`fetched attr with id=${objectTypeId}`)),
      catchError(this.handleError<Map<number, string>>(`getAttributesMap id=${objectTypeId}`))
    );
  }

  getAttributeName(attrId: number): Observable<StringResponse> {
    const url = `${this.attrApiUrl}/getNameOf=${attrId}`;

    return this.http.get<StringResponse>(url).pipe(
      tap(response => this.log(`fetched attr with id=${attrId}, response: ${response.response}`)),
      catchError(this.handleError<StringResponse>(`getAttributeName id=${attrId}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`AttributeService: ${message}`);
  }
}
