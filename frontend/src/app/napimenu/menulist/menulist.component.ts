import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../menu.service';

@Component({
  selector: 'app-menulist',
  templateUrl: './menulist.component.html',
  styleUrls: ['./menulist.component.scss']
})
export class MenulistComponent implements OnInit {

  dailyMenu: any[];

  constructor(private menuservice: MenuService) {
  }

  ngOnInit() {
    this.menuservice.getMenu()
      .subscribe(dailyMenu => this.dailyMenu = dailyMenu);
  }

}
