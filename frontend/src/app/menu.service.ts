import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';


@Injectable()
export class MenuService {

  private favorites = [];

  constructor(private http: HttpClient) {
    const currentFavorites = JSON.parse(localStorage.getItem('favorite-menus'));
    if (currentFavorites) {
      this.favorites = currentFavorites;
    }
  }

  getMenu(): Observable<any> {
    return this.http.get(`/api/dailymenu`);
  }

  isFavorite(restaurant: string): boolean {
    return this.favorites.indexOf(restaurant) > -1;
  }

  saveFavorite(restaurant: string) {
    const index = this.favorites.indexOf(restaurant);
    if (index > -1) {
      this.favorites.splice(index, 1);
    } else {
      this.favorites.push(restaurant);
    }
    localStorage.setItem('favorite-menus', JSON.stringify(this.favorites));
  }

}
