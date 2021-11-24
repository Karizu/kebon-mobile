package com.selada.kebonmobile.network;

import com.selada.kebonmobile.model.LoginModel;
import com.selada.kebonmobile.model.RegisterModel;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.model.request.CommodityBulkRequest;
import com.selada.kebonmobile.model.request.CommodityRequest;
import com.selada.kebonmobile.model.request.DeliveryAddress;
import com.selada.kebonmobile.model.request.HarvestRequest;
import com.selada.kebonmobile.model.request.LeaseSiteRequest;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.model.response.RegisterResponse;
import com.selada.kebonmobile.model.response.SchemeResponse;
import com.selada.kebonmobile.model.response.SiteLeasableResponse;
import com.selada.kebonmobile.model.response.SiteResponse;
import com.selada.kebonmobile.model.response.Status;
import com.selada.kebonmobile.model.response.calendar.HarverstCalendarResponse;
import com.selada.kebonmobile.model.response.calendardetail.DetailHarvestCalendarResponse;
import com.selada.kebonmobile.model.response.commodity.AvailableCommodity;
import com.selada.kebonmobile.model.response.commodity.ResponseCommodity;
import com.selada.kebonmobile.model.response.commoditymonitoring.CommodityMonitoringResponse;
import com.selada.kebonmobile.model.response.detailcommodities.DetailCommoditiesResponse;
import com.selada.kebonmobile.model.response.farmactivities.DetailFarmActivityResponse;
import com.selada.kebonmobile.model.response.farmactivities.FarmActivitiesResponse;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryInSite;
import com.selada.kebonmobile.model.response.farmsummary.FarmSummaryResponse;
import com.selada.kebonmobile.model.response.filtercalendar.CommodityNSitesResponse;
import com.selada.kebonmobile.model.response.harvest.HarvestInquiryResponse;
import com.selada.kebonmobile.model.response.homecontent.HomeContent;
import com.selada.kebonmobile.model.response.invoice.InvoiceDetailResponse;
import com.selada.kebonmobile.model.response.invoice.PaymentMethod;
import com.selada.kebonmobile.model.response.invoice.history.InvoiceHistoryResponse;
import com.selada.kebonmobile.model.response.leasesite.LeaseSiteResponse;
import com.selada.kebonmobile.model.response.mycommodities.MyFarmCommoditiesResponse;
import com.selada.kebonmobile.model.response.notification.NotificationResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL_PROD = "http://13.250.108.11:8081/v0/";
    String BASE_URL_DEV = "http://18.142.123.254:8082/v0/";
//    String BASE_URL_DEV = "http://192.168.1.18:8082/v0/";
    String BASE_URL_CONTENT_DEV = "http://kebon.id/wp-json/wp/v2/";
    String BASE_URL_CONTENT_PROD = "http://kebon.id/wp-json/wp/v2/";

    @Headers("Content-Type: application/json")
    @POST("auth/register")
    Call<ApiResponse<LoginResponse>> register(@Body RegisterModel registerModel);

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<ApiResponse<LoginResponse>> signIn(@Body LoginModel loginModel);

    @GET("site")
    Call<ApiResponse<List<SiteResponse>>> getListSite(@Header("Authorization") String token);

    @GET("lease/packages/siteobjects")
    Call<ApiResponse<List<SiteLeasableResponse>>> getListSiteLeasable(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("lease/checkout")
    Call<ApiResponse<LeaseSiteResponse>> leaseASite(@Body LeaseSiteRequest leaseSiteRequest, @Header("Authorization") String token);

    @GET("farm/my/summary/site")
    Call<FarmSummaryResponse> getMyFarmSummary(@Header("Authorization") String token);

    @GET("farm/my/summary")
    Call<FarmSummaryResponse> getMyFarmSummaryProfile(@Header("Authorization") String token);

    @GET("farm/my/commodities")
    Call<MyFarmCommoditiesResponse> getMyFarmCommodities(@Header("Authorization") String token);

    @GET("farm/my/commodities")
    Call<MyFarmCommoditiesResponse> getFilterMyFarmCommodities(@Query("site_id") String id, @Header("Authorization") String token);

    @GET("farm/my/commodities/{id}/monitoring")
    Call<ApiResponse<CommodityMonitoringResponse>> getMyFarmCommoditiesMonitoring(@Path("id") String id, @Header("Authorization") String token);

    @GET("farm/my/commodities/{id}/monitoring")
    Call<ApiResponse<CommodityMonitoringResponse>> filterMyFarmCommoditiesMonitoring(@Path("id") String id, @Query("site_id") String site_id, @Header("Authorization") String token);

    @GET("farm/my/commodities/{id}")
    Call<ApiResponse<DetailCommoditiesResponse>> getMyFarmDetailCommodities(@Path("id") String id, @Header("Authorization") String token);

    @GET("farm/my/{id}/monitoring")
    Call<FarmSummaryResponse> getMonitoringMyFarm(@Path("id") String id, @Header("Authorization") String token);

    @GET("billing/invoices/{id}")
    Call<ApiResponse<InvoiceDetailResponse>> getInvoiceDetail(@Path("id") String invoice_id, @Header("Authorization") String token);

    @GET("billing/invoices/lease")
    Call<ApiResponse<InvoiceHistoryResponse>> getInvoiceHistory(@Header("Authorization") String token);

    @GET("billing/payment_methods")
    Call<ApiResponse<List<PaymentMethod>>> getPaymentMethods(@Header("Authorization") String token);

    @GET("farm/my/summary/site/{id}")
    Call<FarmSummaryInSite> getListCommodity(@Path("id") String id, @Query("p") String query, @Query("farm_scheme_id") String farm_scheme_id, @Header("Authorization") String token);

    @GET("farm/schemes")
    Call<ApiResponse<List<SchemeResponse>>> getListScheme(@Header("Authorization") String token);

    @POST("farm/my/{id}/activity")
    Call<ResponseBody> postActivity(@Path("id") String id, @Body CommodityRequest commodityRequest, @Header("Authorization") String token);

    @POST("farm/my/{id}/activity/bulk")
    Call<ResponseBody> postBulkActivity(@Path("id") String id, @Body CommodityBulkRequest commodityBulkRequest, @Header("Authorization") String token);

    @GET("posts")
    Call<List<HomeContent>> getContentHome();

    @GET("farm/my/harvests/calendar")
    Call<HarverstCalendarResponse> getHarvestCalendar(@Query("start_date") String start_date, @Query("end_date") String end_date, @Header("Authorization") String token);

    @GET("farm/my/harvests/calendar")
    Call<HarverstCalendarResponse> filterHarvestCalendar(@Query("site_id") String site_id, @Query("commodity_id") String commodity_id, @Query("start_date") String start_date, @Query("end_date") String end_date, @Header("Authorization") String token);

    @GET("farm/my/harvests/calendar/detail")
    Call<DetailHarvestCalendarResponse> getDetailHarvestCalendar(@Query("date") String date, @Header("Authorization") String token);

    @GET("farm/my/harvests/calendar/detail")
    Call<DetailHarvestCalendarResponse> getDetailHarvestCalendar(@Query("site_id") String site_id, @Query("commodity_id") String commodity_id, @Query("date") String date, @Header("Authorization") String token);

    @GET("farm/my/siteNCommodities")
    Call<CommodityNSitesResponse> getCommodityNSitesResponse(@Header("Authorization") String token);

    @GET("farm/my/activities")
    Call<FarmActivitiesResponse> getHistoryActivity(@Header("Authorization") String token);

    @GET("farm/my/activities/{id}")
    Call<DetailFarmActivityResponse> getDetailHistoryActivity(@Path("id") String id, @Header("Authorization") String token);

    @GET("farm/activity/statuses")
    Call<ApiResponse<List<Status>>> getStatus(@Header("Authorization") String token);

    @GET("farm/my/activities")
    Call<FarmActivitiesResponse> filterHistoryActivity(@Query("activity_status_code") String activity_status_code, @Query("start_date") String start_date, @Query("end_date") String end_date, @Header("Authorization") String token);

    @GET("notification/my")
    Call<ApiResponse<List<NotificationResponse>>> getListNotification(@Header("Authorization") String token);

    @GET("farm/my/summary/site/{id}")
    Call<HarvestInquiryResponse> getInquiryHarvest(@Path("id") String id, @Query("p") String query, @Query("commodity_id") String commodity_id, @Header("Authorization") String token);

    @POST("farm/my/{id}/activity")
    Call<ResponseBody> doHarvest(@Path("id") String id, @Body HarvestRequest harvestRequest, @Header("Authorization") String token);

    @GET("auth/refresh-token")
    Call<ApiResponse<LoginResponse>> refreshToken(@Header("Authorization") String token);

    @GET("farm/my/delivery_address/recent")
    Call<ApiResponse<List<DeliveryAddress>>> getRecentAddress(@Header("Authorization") String token);
}
