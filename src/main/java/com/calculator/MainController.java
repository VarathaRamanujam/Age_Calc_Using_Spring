package com.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	//http://localhost:8080/ageCalculator
	@GetMapping("/home")
	public String  ageCalculator() {
		return "index";
	}
	
	@GetMapping("/ageCalulating") // ageCalulating-html action 
	//@ResponseBody
	public String processAgeCalc(@RequestParam("Month")int m,@RequestParam("Day")int d,@RequestParam("Year")int y , Model model) {
	
		String num="";
		String num2="";
		String num3="";
		String num4="";
		
		int count=0;	
		int ls[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		//System.out.println	((ls[dc.getMonth()-1])-(dc.getDate()));
		
		int Month =m-1;
				
		if(Month<0  || Month>12 ) {
			num=("Invalid Month");	
		}
		else {
		int date =d;
				
		if(date>31 || date<0) {
			num=("Invalid Date");
		
		}
		else {
			date = ls[Month]-date;//month days remaining
			for(int i=Month+1;i<ls.length;i++)
				count+=ls[i];//after my month count another days
				
			int count1=0;//count days after birth year
			int current_year=2022;
			int birth_year=y+1;
					
			if(birth_year<0   || birth_year>=current_year )
				num=("Invalid Year Pls Try Again");
			else {
			if(birth_year<current_year) {
				count1=current_year-birth_year;
				count1*=364;
				int j=1;
				int find=0;
				int count2=0;// leap years count
			for(int i=birth_year+1;i<current_year;i++) {		
				int unit=0;
			    int tens=0;
			    unit=i%10;
			    find=i/10;
			    tens=find%10;
			    if(unit==0 && tens==0) {
			    	if(i%400==0)
			    		count2++;
			    }else {
			    	if(i%4==0)
			    		count2++;
			    }      
			    i=birth_year+(j++);   //count2-leap count-remainingmonth count1-year*month
			}
			 num=(current_year-(birth_year-1)+" Age");

			 int current_day=15;
			 int current_month=11;
 num2=			(count+count1+count2+date-(ls[10]+ls[11]+((ls[current_month-1])-(current_day)))+" Days ");
 num3=			(((count+count1+count2-(((ls[current_month-1])-(current_day))+ls[10]+ls[11]))*24)+" Hours ");
 num4=			(((((count+count1+count2-((ls[current_month-1])-(current_day))+ls[10]+ls[11]))*24)*60)+" Seconds ");	
			}
			}	
		}
		}
		// model.addAttribute("num", num);
		 model.addAttribute("result1", num);
		 model.addAttribute("result2", num2);
		 model.addAttribute("result3", num3);
		 model.addAttribute("result4", num4);
		//return num +"\n--->"+num2+"\n--->"+num3+"\n--->"+num4;
		 return "result";
	}
	
//	@GetMapping("/ageCalulating2")
//	@ResponseBody
//	public boolean ageCalculatorSingle(@RequestParam("Month")int m) {
//		return (m%2==0);
//	}
	
	
}

