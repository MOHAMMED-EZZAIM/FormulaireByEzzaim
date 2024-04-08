import {Injectable, OnInit} from '@angular/core';
import { Formulaire } from '../model/formulaire.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormulaireService{
  get items(): Array<Formulaire> {
    return this._items;
  }

  set items(value: Array<Formulaire>) {
    this._items = value;
  }


  _item: Formulaire = new Formulaire();
  _items:Array<Formulaire>=new Array<Formulaire>();

  get item(): Formulaire {
    return this._item;
  }

  set item(value: Formulaire) {
    this._item = value;
  }

  constructor(private http: HttpClient) { }

  public save(): Observable<Formulaire> {
    return this.http.post<Formulaire>(environment.url, this.item);
  }

  public get():Observable<Array<Formulaire>>{
    // @ts-ignore
    return this.http.get(environment.url);
  }
}
