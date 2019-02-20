import { Injectable } from '@angular/core';
import {GenericObject} from './generic-object';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ObjectsService {

  private objectsApiUrl = 'api/objects';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET Object by id. Will 404 if id not found */
  getObjectById(id: number): Observable<GenericObject> {
    const url = `${this.objectsApiUrl}/${id}`;
    return this.http.get<GenericObject>(url).pipe(
      tap(object => this.log(`fetched object id=${id} with parentObj=${object.parentObject.name}`)),
      catchError(this.handleError<GenericObject>(`getObjectById id=${id}`))
    );
  }

  getChildrenObjects(parentId: number): Observable<GenericObject[]> {
    const url = `${this.objectsApiUrl}/?parentObject=${this.getObjectById(parentId)}`;
    return this.http.get<GenericObject[]>(url)
      .pipe(
      tap(_ => this.log(`fetched objects with parentId=${parentId}`)),
      catchError(this.handleError<GenericObject[]>(`getChildrenObjects id=${parentId}`))
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

  //////// Save methods //////////

  createObject(object: GenericObject): Observable<GenericObject> {
    return this.http.post<GenericObject>(this.objectsApiUrl, object, httpOptions).pipe(
      tap((newObject: GenericObject) => this.log(`Object created w/ id=${newObject.id}`)),
      catchError(this.handleError<GenericObject>('createObject'))
    );
  }

  /** DELETE: delete object from the server */
  deleteObject(object: GenericObject | number): Observable<GenericObject> {
    const id = typeof object === 'number' ? object : object.id;
    const url = `${this.objectsApiUrl}/${id}`;

    return this.http.delete<GenericObject>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted object id=${id}`)),
      catchError(this.handleError<GenericObject>('deleteObject'))
    );
  }

  /** PUT: update object on the server */
  updateObject(object: GenericObject): Observable<any> {
    return this.http.put(this.objectsApiUrl, object, httpOptions).pipe(
      tap(_ => this.log(`Updated object id=${object.id}`)),
      catchError(this.handleError<any>('updateObject'))
    );
  }

  private log(message: string) {
    this.messageService.add(`ObjectService: ${message}`);
  }
}
