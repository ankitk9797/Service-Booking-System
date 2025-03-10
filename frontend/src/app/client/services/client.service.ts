import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStoargeService } from 'src/app/basic/services/storage/user-stoarge.service';

const BASIC_URL = "http://localhost:8082/company";
const BASIC_URL2 = "http://localhost:8083/client";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getAllAds(): Observable<any>{
    return this.http.get(BASIC_URL + `/allAds`, {
      headers : this.createAuthorizationHeader()
    })
  }

  searchAdByName(name:any): Observable<any>{
    return this.http.get(BASIC_URL + `/search/${name}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  getAdDetailsByAdId(adId:any): Observable<any>{
    return this.http.get(BASIC_URL + `/ad/${adId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  bookService(bookDTO:any): Observable<any>{
      console.log(bookDTO);
    return this.http.post(BASIC_URL2 + `/bookService`, bookDTO , {
      headers : this.createAuthorizationHeader()
    })
  }

  getMyBookings(): Observable<any>{
    const userId = UserStoargeService.getUserId();
    return this.http.get(BASIC_URL2 + `/bookings/${userId}`,  {
      headers : this.createAuthorizationHeader()
    })
  }

  giveReview(reviewDTO:any): Observable<any>{
    return this.http.post(BASIC_URL + `api/client/review`, reviewDTO , {
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
