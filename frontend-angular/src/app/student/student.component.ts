import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent implements OnInit , AfterViewInit{

  getPayments(student: any) {
    this.router.navigateByUrl("/payments")

}

filterStudents(event: Event):void {
  let value = (event.target as HTMLInputElement).value ;
  this.dataSource.filter=value;
}
  public students : any ;
  public dataSource : any ;
  public displayedColumns=["id","firstName","lastName","payments"];
  @ViewChild(MatPaginator) paginator! : MatPaginator ;
  @ViewChild(MatSort) sort! : MatSort;

  constructor(private router : Router){

  }
  ngOnInit(): void {
    this.students=[];
    for(let i =1 ; i<100 ; i++){
      this.students.push({
        id:i,
        firstName:Math.random().toString(20),
        lastName:Math.random().toString(20),
      });
    }
    this.dataSource= new MatTableDataSource(this.students);   
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator=this.paginator;
    this.dataSource.sort = this.sort ;
  }
}
