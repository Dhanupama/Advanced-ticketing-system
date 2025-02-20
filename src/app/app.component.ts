import { Component } from '@angular/core';
import { FormPageComponent } from './form-page/form-page.component';
import { HttpClient, HttpClientModule } from '@angular/common/http'; // Import the standalone form component beacuse this is Angular 18 project

@Component({
  selector: 'app-root',
  standalone: true,  // Mark root as standalone
  imports: [FormPageComponent,HttpClientModule],  // Import your form component , connecting method with backend
  template: `<app-form-page></app-form-page>`,  // Use the form component in the template and nvigate form html file
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    constructor(private http: HttpClient) {
        // HttpClient is now available for use
      }
}