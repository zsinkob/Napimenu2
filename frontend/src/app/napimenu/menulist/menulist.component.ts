import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../menu.service';

@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.scss']
})
export class MenulistComponent implements OnInit {

  dailyMenu: any[] = [];

  loading = false;

  constructor(private menuservice: MenuService) {
  }

  ngOnInit() {
    this.loading = true;
    this.menuservice.getMenu()
      .subscribe(dailyMenu => {
        this.loading = false;
        for (let i = 0; i < dailyMenu.length; i += 3) {
          const chunk = dailyMenu.slice(i, i + 3);
          this.dailyMenu.push(chunk);
        }
      });
  }

  newRow(index: number) {
    if (index % 3 === 0) {
      return 'w3-row-padding';
    } else {
      return '';
    }
  }

}
