import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStoargeService } from 'src/app/basic/services/storage/user-stoarge.service';

@Component({
  selector: 'app-ad-detail',
  templateUrl: './ad-detail.component.html',
  styleUrls: ['./ad-detail.component.scss']
})
export class AdDetailComponent {

  adId= this.activatedroute.snapshot.params['adId'];
  avatarUrl:any;
  ad:any;
  defaultDate = new Date();
  disableOldDates = (current: Date): boolean => {
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Set time to 00:00:00 for accurate comparison
    return current && current.getTime() < today.getTime(); // Allow dates from today onwards
  };

  reviews:any;

  validateForm!: FormGroup;

  constructor(private clientService: ClientService,
    private activatedroute: ActivatedRoute,
    private notification: NzNotificationService,
    private router: Router,
    private fb: FormBuilder){}


    ngOnInit(){
      this.validateForm = this.fb.group({
        bookDate: [null, [Validators.required]]
      })
      this.getAdDetailsByAdId();
    }


    getAdDetailsByAdId(){
      this.clientService.getAdDetailsByAdId(this.adId).subscribe(res=>{
        console.log(res);
        this.avatarUrl = 'data:image/jpeg;base64,' + res.returnedImg;
        this.ad = res;
      })
    }

    bookService(){
      const bookServiceDTO = {
        bookDate : this.validateForm.get(['bookDate']).value,
        adId : this.adId,
        userId: UserStoargeService.getUserId(),
        reservationStatus: 'PENDING',
        companyId: this.ad.userId,
      }

      this.clientService.bookService(bookServiceDTO).subscribe(res =>{
        this.notification
        .success(
          'SUCCESS',
          `Request posted successfully`,
          { nzDuration: 5000 }
        );
        this.router.navigateByUrl('/client/bookings');
      })
    }

}
