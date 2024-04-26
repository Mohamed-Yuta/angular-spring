import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { StudentComponent } from './student/student.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PaymentsComponent } from './payments/payments.component';
import { LoadPaymentsComponent } from './load-payments/load-payments.component';
import { LoadStudentsComponent } from './load-students/load-students.component';
import { AdminTemplateComponent } from './admin-template/admin-template.component';

const routes: Routes = [
  {path : "login",component : LoginComponent},
  {path : "admin",component : AdminTemplateComponent, children:[
    {path : "home",component : HomeComponent},
    {path : "students",component : StudentComponent},
    {path : "profile",component : ProfileComponent},
    {path : "dashboard",component : DashboardComponent},
    {path : "payments",component : PaymentsComponent},
    {path : "load-payments",component : LoadPaymentsComponent},
    {path : "load-students",component : LoadStudentsComponent},
  ]},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
