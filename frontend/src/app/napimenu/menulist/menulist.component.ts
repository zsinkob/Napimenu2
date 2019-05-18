import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {MenuService} from '../../menu.service';

@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.scss']
})
export class MenulistComponent implements OnInit {

  dailyMenu: any[] = [];

  originalMenu: any;

  loading = false;

  constructor(private menuservice: MenuService, private _cd: ChangeDetectorRef) {
  }


  ngOnInit() {
    this.loading = true;
    this.menuservice.getMenu()
      .subscribe(dailyMenu => {
        this.originalMenu = dailyMenu;
        this.breakRows();
        this.loading = false;
      });
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
    const favorites = JSON.parse(localStorage.getItem('favorite-menus'));
    const result = [];
    if (!favorites) {
      return menu;
    } else {
      menu.forEach((element) => {
        if (favorites.indexOf(element.restaurant.name) !== -1) {
          result.unshift(element);
        } else {
          result.push(element);
        }
      });
      return result;
    }

  }

  newRow(index: number) {
    if (index % 3 === 0) {
      return 'w3-row-padding';
    } else {
      return '';
    }
  }

}
