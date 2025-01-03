import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import {AuthService} from "../../../basic/services/auth/auth.service";

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrls: ['./company-dashboard.component.scss']
})
export class CompanyDashboardComponent {

  bookings:any;

  constructor(private companyService: CompanyService,
    private notification: NzNotificationService, private authService: AuthService,){}

  ngOnInit(){
    this.getAllAdBookings();
  }

  getAllAdBookings(){
    this.companyService.getAllAdBookings().subscribe(res =>{
      this.bookings = res;
    })
  }

  changeBookingStatus(bookingId: number, status:string){
    this.companyService.changeBookingStatus(bookingId, status).subscribe(res=>{
      this.notification
      .success(
        'SUCCESS',
        `Booking status changed successfully`,
        { nzDuration: 5000 }
      );
      this.getAllAdBookings();
    }, error =>{
      this.notification
        .error(
          'ERROR',
          `${error.message}`,
          { nzDuration: 5000 }
        )
    })
  }

}
