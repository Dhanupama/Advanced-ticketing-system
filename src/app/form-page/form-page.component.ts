import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Import HttpClient for API calls
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { CommonModule } from '@angular/common'; // Import CommonModule for common Angular directives

@Component({
  selector: 'app-form-page',
  standalone: true,  // Mark this component as standalone
  templateUrl: './form-page.component.html',  // Link to your template
  styleUrls: ['./form-page.component.css'],  // Link to your styles
  imports: [CommonModule, FormsModule]  // Import necessary Angular modules
})
export class FormPageComponent {
  formData = {
    totalTicket: 0,
    ticketRetrivalTime: 0,
    customerRetrivalTime: 0,
    maxTicketCapacity: 0,
    customerCount: 0,
    vendorCount: 0
  };
  // must pass json format

  constructor(private http: HttpClient) {}

  // connect back end using API
  onSubmit() {
    const apiUrl = 'http://localhost:8080/api/tickets/start';
    this.http.post(apiUrl, this.formData, { responseType: 'text' }).subscribe(
      (response) => {
        console.log('Form submitted successfully', response);
      },
      (error) => {
        console.error('Error submitting form', error);
      }
    );
  }
  
  // Clear form data form
  onClear() {
    this.formData = {
    totalTicket: 0,
    ticketRetrivalTime: 0,
    customerRetrivalTime: 0,
    maxTicketCapacity: 0,
    customerCount: 0,
    vendorCount: 0
    };
  }

  // Save form data 
  onSave() {
    const apiUrl = 'https://your-api-endpoint.com/save'; //this is not working
    this.http.post(apiUrl, this.formData).subscribe(
      (response) => {
        console.log('Form data saved', response);
      },
      (error) => {
        console.error('Error saving form data', error);
      }
    );
  }

  // Reset the form
  onReset() {
    this.formData = {
    totalTicket: 0,
    ticketRetrivalTime: 0,
    customerRetrivalTime: 0,
    maxTicketCapacity: 0,
    customerCount: 0,
    vendorCount: 0
    };
  }
}