import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import {CompanyService} from "../../../company/services/company.service";

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss']
})
export class MyBookingsComponent {

  bookedServices:any;

  constructor(private clientService: ClientService, private companyService: CompanyService){}

  ngOnInit(){
    this.getMyBookings();
  }

  getMyBookings(){
    this.clientService.getMyBookings().subscribe(res =>{
      this.bookedServices = res;
    })
  }

}
