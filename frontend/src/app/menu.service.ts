import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';


@Injectable()
export class MenuService {

  constructor(private http: HttpClient) { }

  getMenu(): Observable<any> {
    return this.http.get(`/api/dailymenu`);
  }

}
