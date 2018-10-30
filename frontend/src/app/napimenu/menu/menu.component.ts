import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @Input() menu: any;

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

}
