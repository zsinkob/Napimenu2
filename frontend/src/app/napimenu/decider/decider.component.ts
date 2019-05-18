import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-decider',
  templateUrl: './decider.component.html',
  styleUrls: ['./decider.component.scss']
})
export class DeciderComponent implements OnInit {

  @Input() menuList: any[];
  winner: any;

  constructor() {
  }

  ngOnInit() {
  }

  selectRestaurant() {
    const randomMenu = Math.floor(Math.random() * this.menuList.length);
    this.winner = this.menuList[randomMenu];
  }


}
