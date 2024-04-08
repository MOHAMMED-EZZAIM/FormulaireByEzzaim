import {Component, OnInit} from '@angular/core';
import {FormulaireService} from "../../../shared/service/formulaire.service";
import {Formulaire} from "../../../shared/model/formulaire.model";
import {FormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-formulaire-create',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    NgIf,
    NgForOf
  ],
  templateUrl: './formulaire-create.component.html',
  styleUrl: './formulaire-create.component.css'
})
export class FormulaireCreateComponent{
  get service(): FormulaireService {
    return this._service;
  }

  set service(value: FormulaireService) {
    this._service = value;
  }

  public _ok:boolean=false;
  constructor(public _service :FormulaireService) {
  }
  get item(): Formulaire {
    return this._service._item;
  }

  set item(value: Formulaire) {
    this._service._item = value;
  }
  public save(){
    this._service.save().subscribe({
      next:data=>{
        this.item=data
      },
      error:err => {
        console.log(err)
      }
    })
    this._ok=true

    }

  public  get(){
    this._service.get().subscribe({
      next:data=>{
        this.items=data
      },
    error:err => {
        console.log(err)
    }
    })
  }


  get items(): Array<Formulaire> {
    return this._service._items;
  }

  set items(value: Array<Formulaire>) {
    this._service._items = value;
  }

  supprimet() {
    this.item.name="";
    this.item.prenom="";
    this.item.email="";
    this.item.number="";
    this.item.educational="";
    this.item.code=""
  }
}
