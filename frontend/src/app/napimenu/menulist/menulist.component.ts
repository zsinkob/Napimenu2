import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MenuService } from '../../menu.service';

@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.scss']
})
export class MenulistComponent implements OnInit {

  dailyMenu: any[] = [];

  originalMenu: any;

  loading = 'idle';

  constructor(private menuservice: MenuService, private _cd: ChangeDetectorRef) {
  }

  isError = false;


  ngOnInit() {
    this.loading = 'loading';
    this.menuservice.getMenu()
      .subscribe(dailyMenu => {
        this.originalMenu = dailyMenu;
        this.breakRows();
        this.loading = 'success';
      }, () => this.loading = 'error');
  }

  breakRows() {
    const sorted = this.sortFavories(this.originalMenu);
    this.dailyMenu = sorted;
    this._cd.markForCheck();
  }

  trackByName(index: number, menu: any): number {
    return menu.restaurant.name;
  }

  redraw(event) {
    this.breakRows();
  }

  sortFavories(menu: any[]): any[] {
    const result = [];
    const favorites = [];
    menu.forEach((element) => {
      if (this.menuservice.isFavorite(element.restaurant.name)) {
        favorites.push(element);
      } else {
        result.push(element);
      }
    });
    if (favorites.length > 0) {
      result.unshift(...favorites);
    }
    return result;
  }

  newRow(index: number) {
    if (index % 3 === 0) {
      return 'w3-row-padding';
    } else {
      return '';
    }
  }

}
