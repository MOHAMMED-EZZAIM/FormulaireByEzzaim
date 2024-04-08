import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FormulaireCreateComponent} from "./view/formulaire/create/formulaire-create.component";

const routes: Routes = [
  {path:"",component:FormulaireCreateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
