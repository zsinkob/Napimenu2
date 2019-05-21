import { Component, AfterViewInit, Input, Output,  EventEmitter, AfterContentInit, AfterViewChecked } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MenuService } from '../../menu.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements AfterViewInit {
  @Input() menu: any;
  @Output() selected: EventEmitter<any> = new EventEmitter();

  constructor(private _sanitizer: DomSanitizer, private menuservice: MenuService) { }

  ngAfterViewInit() {
    this.resizeAllGridItems();
  }

  formatImage(): any {
    return 'data:image/jpg;base64,'
      + this.menu.image;
  }

  formatMenu(): any {
    return this._sanitizer.bypassSecurityTrustHtml(this.menu.menu.replace(new RegExp('\n', 'g'), '<br/>'));
  }

  onResize(event) {
    this.resizeAllGridItems();
  }

  resizeGridItem(item) {
    const grid = document.getElementsByClassName('grid')[0];
    const rowHeight = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-auto-rows'), 10);
    const rowGap = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-row-gap'), 10);
    const rowSpan = Math.ceil((item.querySelector('.content').getBoundingClientRect().height + rowGap) / (rowHeight + rowGap));
    item.style.gridRowEnd = 'span ' + rowSpan;
  }

  resizeAllGridItems() {
    console.log('resize');
    const allItems = document.getElementsByClassName('item');
    for (let x = 0; x < allItems.length; x++) {
      this.resizeGridItem(allItems[x]);
    }
  }

  saveFavorite() {
    const restaurant = this.menu.restaurant.name;
    this.menuservice.saveFavorite(restaurant);
    this.selected.emit(restaurant);
  }

  isFavorite(): boolean {
    return this.menuservice.isFavorite(this.menu.restaurant.name);
  }

}
