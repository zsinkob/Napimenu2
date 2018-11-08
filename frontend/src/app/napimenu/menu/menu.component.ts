import { Component, OnInit, Input, Output,  EventEmitter } from '@angular/core';
import { DomSanitizer, SafeResourceUrl, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @Input() menu: any;
  @Output() selected: EventEmitter<any> = new EventEmitter();

  constructor(private _sanitizer: DomSanitizer) { }

  ngOnInit() {
  }

  formatImage(): any {
    return this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,'
      + this.menu.image);
  }

  formatMenu(): any {
    return this._sanitizer.bypassSecurityTrustHtml(this.menu.menu.replace(new RegExp('\n', 'g'), '<br/>'));
  }

  saveFavorite() {
    const restaurant = this.menu.restaurant.name;
    const currentFavorites = JSON.parse(localStorage.getItem('favorite-menus'));
    if (!currentFavorites) {
      localStorage.setItem('favorite-menus', JSON.stringify([restaurant]));
    } else {
      const index = currentFavorites.indexOf(restaurant);
      if (index > -1) {
        currentFavorites.splice(index, 1);
      } else {
        currentFavorites.push(this.menu.restaurant.name);
      }
      localStorage.setItem('favorite-menus', JSON.stringify(currentFavorites));
    }
    this.selected.emit(restaurant);
  }

}
