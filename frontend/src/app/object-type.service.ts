import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from './message.service';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {ObjectType} from './object-type';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ObjectTypeService {

  private objectsApiUrl = '//localhost:8080/objectTypes';

  constructor(private http: HttpClient,
              private messageService: MessageService) {
  }

  createObjectType(newObjectType: ObjectType): Observable<any> {
    return this.http.post<ObjectType>(this.objectsApiUrl, newObjectType).pipe(
      tap(_ => this.log(`created object type: ${newObjectType.name}`)),
      catchError(this.handleError<ObjectType>(`createObjectType name=${newObjectType.name}`))
    );
  }

  deleteObjectType(objectTypeId: number): Observable<any> {
    const url = `${this.objectsApiUrl}/${objectTypeId}`;

    return this.http.delete(url, httpOptions).pipe(
      tap(_ => this.log(`deleted objectType: ${objectTypeId}`)),
      catchError(this.handleError<ObjectType>(`deleteObjectType objectTypeId=${objectTypeId}`))
    );
  }

  getObjectTypeById(objectTypeId: number): Observable<ObjectType> {
    const url = `${this.objectsApiUrl}/${objectTypeId}`;
    return this.http.get<ObjectType>(url).pipe(
      tap(_ => this.log(`fetched object type id=${objectTypeId}`)),
      catchError(this.handleError<ObjectType>(`getObjectById id=${objectTypeId}`))
    );
  }

  getObjectTypesList(): Observable<ObjectType[]> {
    return this.http.get<ObjectType[]>(this.objectsApiUrl).pipe(
      tap(_ => this.log('Loaded object types list')),
      catchError(this.handleError<any>('getObjectTypesList()'))
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
    this.messageService.add(`ObjectTypeServiceService: ${message}`);
  }

}
