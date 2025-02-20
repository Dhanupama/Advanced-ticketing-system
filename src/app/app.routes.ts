import { Routes } from '@angular/router';
import { FormPageComponent } from './form-page/form-page.component';

export const routes: Routes = [
  { path: '', redirectTo: '/form-page', pathMatch: 'full' },
  { path: 'form-page', component: FormPageComponent }
];