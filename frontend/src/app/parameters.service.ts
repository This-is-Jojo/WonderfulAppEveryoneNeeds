import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from './message.service';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ParametersService {

  private parametersApiUrl = '//localhost:8080/parameters';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  getParametersMap(objectId: number): Observable<Map<number, string>> {
    const url = `${this.parametersApiUrl}/${objectId}`;
    return this.http.get<Map<number, string>>(url).pipe(
      tap(parameter => this.log('Fetched parameter ' + parameter)),
      catchError(this.handleError<Map<number, string>>(`getParametersMap id=${objectId}`))
    );
  }

  updateParametersMap(objectId: number, map: Map<number, string>): Observable<any> {
    const url = `${this.parametersApiUrl}/${objectId}`;
    return this.http.put<Map<number, string>>(url, map, httpOptions).pipe(
      tap(parameter => this.log('Fetched parameter ' + parameter)),
      catchError(this.handleError<Map<number, string>>(`getParametersMap id=${objectId}`))
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
    this.messageService.add(`ParametersService: ${message}`);
  }
}


