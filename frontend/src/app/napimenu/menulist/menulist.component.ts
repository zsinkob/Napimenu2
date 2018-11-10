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

  trackByName(index: number, menu: any): number { return menu.restaurant.name; }

  redraw(event) {
    this.breakRows();
  }

 /* resizeGridItem(item) {
    const grid = document.getElementsByClassName('grid')[0];
    const rowHeight = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-auto-rows'));
    const rowGap = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-row-gap'));
    const rowSpan = Math.ceil((item.querySelector('.content').getBoundingClientRect().height + rowGap) / (rowHeight + rowGap));
    item.style.gridRowEnd = 'span ' + rowSpan;
  }

  resizeAllGridItems() {
    const allItems = document.getElementsByClassName('item');
    allItems.forEach(item => this.resizeGridItem(item));
  }*/


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
