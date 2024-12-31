import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStoargeService } from 'src/app/basic/services/storage/user-stoarge.service';

const BASIC_URL = "http://localhost:8082/company";
const BASIC_URL2 = "http://localhost:8083/client";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  postAd(adDTO:any): Observable<any>{
    console.log(adDTO);
    return this.http.post(BASIC_URL + `/create-ad`, adDTO, {
      headers : this.createAuthorizationHeader()
    })
  }

  getAllAdsByUserId(): Observable<any>{
    const userId = UserStoargeService.getUserId();
    console.log(UserStoargeService.getToken());
    return this.http.get(BASIC_URL + `/allAds/${userId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  getAdById(adId:any): Observable<any>{
    return this.http.get(BASIC_URL + `/ad/${adId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  updateAd(adId:any, adDTO:any): Observable<any>{
    return this.http.put(BASIC_URL + `/ad/${adId}`, adDTO, {
      headers : this.createAuthorizationHeader()
    })
  }

  deletedAd(adId:any): Observable<any>{
    return this.http.delete(BASIC_URL + `/ad/${adId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  getAllAdBookings(): Observable<any>{
    const companyId = UserStoargeService.getUserId();
    return this.http.get(BASIC_URL2 + `/bookings/${companyId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  changeBookingStatus(bookingId: number, status: string): Observable<any>{

    return this.http.get(BASIC_URL2 + `/changeStatus/${bookingId}/${status}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader(): HttpHeaders{
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      'Bearer ' + UserStoargeService.getToken()
    )
  }
}
