package ru.levelup.elena.roshchina.qa.final_work;

import org.testng.annotations.Test;

import static junit.framework.TestCase.assertEquals;

/*
1.	Открыть сайт
2.	Авторизоваться под заданным пользователем
3.	Проверить, что авторизация прошла успешно
4.	Открыть Fleet -> Vehicles
5.	Нажать на кнопку Create Car
6.	На вкладке Generalзаполнить поля
•	License Plate
•	Tags (выбрать 3 tag)
•	Driver
•	Location
•	Model Year
•	Seats Number
•	Transmission
•	Fuel Type
•	VehicleModel (выбрать любую модель из списка)
7.	Нажать на кнопку Save And Close
8.	Проверить, что все поля заполнены в соответствии с введёнными данными
9.	Выйти из системы

 */


public class fleetManagementTest extends AbstractBaseFleetManagementTest {

    @Test
    public void entranceTest() {
        LoginPage entrancePage = new LoginPage(driver);
        entrancePage.open();                             //1.	Открыть сайт
        DashboardsPage mainPage = entrancePage.login();  //2.	Авторизоваться под заданным пользователем
        //3.	Проверить, что авторизация прошла успешно
        Sleep.sleep(1000);
        System.out.println(mainPage.getPageTitle());
        //4.	Открыть Fleet -> Vehicles
        FleetPage vehiclesPage = mainPage.gotoFleet("Vehicles");
        Sleep.sleep(1000);
        System.out.println(vehiclesPage.getPageTitle());
        //5.	Нажать на кнопку Create Car
        Sleep.sleep(1000);
        CreateCarPage createCarPage = vehiclesPage.createCar();
        System.out.println(createCarPage.getPageTitle());
        Sleep.sleep(2000);

        //6.	На вкладке Generalзаполнить поля
        //•	License Plate SDET
        //•	Tags (выбрать 3 tag)
        //•	Driver Prof
        //•	Location Trinidad
        //•	Model Year 2020
        //•	Seats Number 4
        //•	Transmission AT
        //•	Fuel Type  petroleum
        //•	VehicleModel (выбрать любую модель из списка)
        createCarPage.addModel();
        createCarPage.cancel();

        mainPage.logout();
        Sleep.sleep(1000);

    }
}
/*
* <div id="main-menu" class="main-menu-top">
    <ul class="nav-multilevel main-menu">
      <li class="dropdown dropdown-level-1 first">
          <a href="#" class="unclickable">
             <span class="title title-level-1"><i class="fa-bar-chart-o menu-icon"></i>Dashboards</span></a>
           <div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;">
                 <div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 121px;">
                      <ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1">
                         <li class="dropdown-menu-title dropdown-menu-title-level-1">
                            <span><i class="fa-bar-chart-o menu-icon"></i>Dashboards</span></li>
                         <li class="divider"><span></span></li>
                         <li data-menu="3" data-route="oro_dashboard_view" class="dropdown-menu-single-item first">
                                   <a href="/dashboard/view/3?change_dashboard=1">
                                             <span class="title title-level-2">Table</span></a></li>
                         <li data-menu="1" data-route="oro_dashboard_view" class="dropdown-menu-single-item">
                                 <a href="/dashboard/view/1?change_dashboard=1"><span class="title title-level-2">Dashboard</span></a></li>
                         <li class="menu-divider divider dropdown-menu-single-item"><span></span></li>
                         <li data-route="oro_dashboard_index" data-routes="[&quot;oro_dashboard_update&quot;,&quot;oro_dashboard_create&quot;,&quot;oro_dashboard_view&quot;]" class="dropdown-menu-single-item last">
                                  <a href="/dashboard"><span class="title title-level-2">Manage Dashboards</span></a></li>
                      </ul>
                 </div>
            </div></li>
        <li class="dropdown dropdown-level-1">
            <a href="#" class="unclickable">
                <span class="title title-level-1"><i class="fa-asterisk menu-icon"></i>
                   Fleet
                </span></a>
            <div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;">
                <div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 272px;">
                   <ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1">
                       <li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-asterisk menu-icon"></i>Fleet</span></li>
                       <li class="divider"><span></span></li>
                       <li class="dropdown-menu-single-item first"><a href="entity/Extend_Entity_Carreservation">
                            <span class="title title-level-2">Vehicles</span></a></li>
                       <li class="dropdown-menu-single-item"><a href="/entity/Extend_Entity_VehiclesOdometer">
                           <span class="title title-level-2">Vehicle Odometer</span></a></li>
                       <li class="dropdown-menu-single-item"><a href="/entity/Extend_Entity_VehicleCosts">
                            <span class="title title-level-2">Vehicle Costs</span></a></li>
                       <li class="dropdown-menu-single-item"><a href="/entity/Extend_Entity_VehicleContract">
                       * <span class="title title-level-2">Vehicle Contracts</span></a></li><li class="dropdown-menu-single-item"><a href="/entity/Extend_Entity_VehicleFuelLogs"><span class="title title-level-2">Vehicles Fuel Logs</span></a></li><li class="dropdown-menu-single-item"><a href="entity/Extend_Entity_VehicleServicesLogs"><span class="title title-level-2">Vehicle Services Logs</span></a></li><li class="dropdown-menu-single-item last"><a href="/entity/Extend_Entity_VehiclesModel"><span class="title title-level-2">Vehicles Model</span></a></li></ul></div></div></li><li class="dropdown dropdown-level-1"><a href="#" class="unclickable"><span class="title title-level-1"><i class="fa-users menu-icon"></i>
    Customers</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;"><div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 82px;"><ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1"><li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-users menu-icon"></i>
    Customers</span></li><li class="divider"><span></span></li><li data-route="oro_account_index" data-routes="[&quot;oro_account_*&quot;]" class="dropdown-menu-single-item first"><a href="/account"><span class="title title-level-2">Accounts</span></a></li><li data-route="oro_contact_index" data-routes="[&quot;\/^oro_contact_(?!group\\w+|group\\w+)\\w+$\/&quot;]" class="dropdown-menu-single-item last"><a href="/contact"><span class="title title-level-2">Contacts</span></a></li></ul></div></div></li><li class="dropdown dropdown-level-1"><a href="#" class="unclickable"><span class="title title-level-1"><i class="fa-shopping-cart menu-icon"></i>
    Sales</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;"><div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 44px;"><ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1"><li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-shopping-cart menu-icon"></i>
    Sales</span></li><li class="divider"><span></span></li><li data-route="oro_sales_opportunity_index" data-routes="[&quot;\/^oro_sales_opportunity\/&quot;]" class="dropdown-menu-single-item last"><a href="/opportunity"><span class="title title-level-2">Opportunities</span></a></li></ul></div></div></li><li class="dropdown dropdown-level-1"><a href="#" class="unclickable"><span class="title title-level-1"><i class="fa-puzzle-piece menu-icon"></i>
    Activities</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 18px;"><div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 30px;"><ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1"><li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-puzzle-piece menu-icon"></i>
    Activities</span></li><li class="divider"><span></span></li><li data-route="oro_call_index" data-routes="[&quot;oro_call_*&quot;]" class="dropdown-menu-single-item"><a href="/call/"><span class="title title-level-2">Calls</span></a></li><li data-route="oro_calendar_event_index" data-routes="[&quot;oro_calendar_event_*&quot;]" class="dropdown-menu-single-item last"><a href="/calendar/event"><span class="title title-level-2">Calendar Events</span></a></li></ul></div></div></li><li class="dropdown dropdown-level-1"><a href="#" class="unclickable"><span class="title title-level-1"><i class="fa-sitemap menu-icon"></i>
    Marketing</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;"><div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 82px;"><ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1"><li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-sitemap menu-icon"></i>
    Marketing</span></li><li class="divider"><span></span></li><li data-route="oro_campaign_index" data-routes="[&quot;oro_campaign_*&quot;]" class="dropdown-menu-single-item"><a href="/campaign/"><span class="title title-level-2">Campaigns</span></a></li><li data-route="oro_email_campaign_index" data-routes="[&quot;oro_email_campaign_*&quot;]" class="dropdown-menu-single-item"><a href="/campaign/email/"><span class="title title-level-2">Email Campaigns</span></a></li></ul></div></div></li><li class="dropdown dropdown-level-1"><a href="#" class="unclickable"><span class="title title-level-1"><i class="fa-folder-open menu-icon"></i>
    Reports &amp; Segments</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;"><div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 122px;"><ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1"><li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-folder-open menu-icon"></i>
    Reports &amp; Segments</span></li><li class="divider"><span></span></li><li class="dropdown dropdown-level-2 first"><a href="#" class="unclickable"><span class="title title-level-2">Reports</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__child" style="margin-top: 0px; top: 3px;"><ul class="dropdown-menu dropdown-menu-level-2 menu menu-level-2"><li class="dropdown dropdown-level-3 first"><a href="#" class="unclickable"><span class="title title-level-3">Accounts</span></a><ul class="dropdown-menu dropdown-menu-level-3 menu menu-level-3"><li data-route="oro_reportcrm_index" class="dropdown-menu-single-item first"><a href="/report/static/accounts/life_time_value"><span class="title title-level-4">Life Time</span></a></li><li data-route="oro_reportcrm_index" class="dropdown-menu-single-item last"><a href="/report/static/accounts/by_opportunities"><span class="title title-level-4">By Opportunities</span></a></li></ul></li><li class="dropdown dropdown-level-3"><a href="#" class="unclickable"><span class="title title-level-3">Leads</span></a><ul class="dropdown-menu dropdown-menu-level-3 menu menu-level-3"><li data-route="oro_reportcrm_index" class="dropdown-menu-single-item first last"><a href="/report/static/leads/by_date"><span class="title title-level-4">Leads By Date</span></a></li></ul></li><li class="dropdown dropdown-level-3 last"><a href="#" class="unclickable"><span class="title title-level-3">Opportunities</span></a><ul class="dropdown-menu dropdown-menu-level-3 menu menu-level-3"><li data-route="oro_reportcrm_index" class="dropdown-menu-single-item first"><a href="/report/static/opportunities/by_status"><span class="title title-level-4">Opportunities By Status</span></a></li><li data-route="oro_reportcrm_index" class="dropdown-menu-single-item"><a href="/report/static/opportunities/won_by_period"><span class="title title-level-4">Won Opportunities By Period</span></a></li><li data-route="oro_reportcrm_index" class="dropdown-menu-single-item last"><a href="/report/static/opportunities/total_forecast"><span class="title title-level-4">Total Forecast</span></a></li></ul></li></ul></div></li><li class="mobile-hide dropdown-menu-single-item" data-route="oro_report_index" data-routes="[&quot;oro_report_update&quot;,&quot;oro_report_create&quot;]"><a href="/report"><span class="title title-level-2">Manage Custom Reports</span></a></li><li class="divider dropdown-menu-single-item"><span></span></li><li class="dropdown dropdown-level-2"><a href="#" class="unclickable"><span class="title title-level-2">Campaigns</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__child" style="margin-top: 0px; top: 80px;"><ul class="dropdown-menu dropdown-menu-level-2 menu menu-level-2"><li data-route="oro_report_view" class="dropdown-menu-single-item first last"><a href="/report/view/1"><span class="title title-level-3">Campaign Performance</span></a></li></ul></div></li><li class="divider dropdown-menu-single-item"><span></span></li></ul></div></div></li><li class="mobile-hide dropdown dropdown-level-1 last"><a href="#" class="unclickable"><span class="title title-level-1"><i class="fa-gear menu-icon"></i>
    System</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__placeholder" style="margin-top: 0px;"><div class="dropdown-menu-wrapper dropdown-menu-wrapper__scrollable" style="max-height: 196px;"><ul class="dropdown-menu dropdown-menu-level-1 menu menu-level-1"><li class="dropdown-menu-title dropdown-menu-title-level-1"><span><i class="fa-gear menu-icon"></i>
    System</span></li><li class="divider"><span></span></li><li class="dropdown dropdown-level-2"><a href="#" class="unclickable"><span class="title title-level-2">User Management</span></a><div class="dropdown-menu-wrapper dropdown-menu-wrapper__child" style="margin-top: 0px; top: 3px;"><ul class="dropdown-menu dropdown-menu-level-2 menu menu-level-2"><li data-route="oro_user_index" data-routes="[&quot;\/^oro_user_(?!role\\w+|group\\w+|security\\w+|reset\\w+)\\w+$\/&quot;]" class="dropdown-menu-single-item first"><a href="/user"><span class="title title-level-3">Users</span></a></li><li data-route="oro_business_unit_index" data-routes="[&quot;oro_business_unit_*&quot;]" class="dropdown-menu-single-item"><a href="/organization/business_unit"><span class="title title-level-3">Business Units</span></a></li></ul></div></li><li data-route="oro_contact_group_index" data-routes="[&quot;oro_contact_group_*&quot;]" class="dropdown-menu-single-item"><a href="/contact/group"><span class="title title-level-2">Contact Groups</span></a></li><li data-route="oro_message_queue_root_jobs" data-routes="[&quot;oro_message_queue_*&quot;]" class="dropdown-menu-single-item"><a href="/message-queue/jobs/"><span class="title title-level-2">Jobs</span></a></li><li data-route="oro_navigation_global_menu_index" data-routes="[&quot;\/^oro_navigation_global_menu_[\\w_]+$\/&quot;]" class="dropdown-menu-single-item"><a href="/menu/global/"><span class="title title-level-2">Menus</span></a></li><li data-route="oro_system_calendar_index" data-routes="[&quot;oro_system_calendar_*&quot;]" class="dropdown-menu-single-item"><a href="/system-calendar/"><span class="title title-level-2">System Calendars</span></a></li></ul></div></div></li></ul>

</div>
* */