package com.incra

import java.text.SimpleDateFormat 
import java.util.Calendar;


/**
 * The <i>JqueryDatePickerTagLib </i> implements the tags used for the jQuery UI datepicker. It's characteristics are as follows:
   1.It creates three hidden input fields dateField_day, dateField_month, dateField_year
   2.It's responsible for populating these hidden input fields when a date has been selected from the calendar.
   3. Supports having multiple date fields without any conflict.
 *
 * @author Spoorthy Ananthaiah
 * @since 03/15/11
 */
class JqueryDatePickerTagLib  {
 
	
	def jqDatePicker  = { attrs, body -> 
		def out = out 
		def name = attrs.name 
		def id = attrs.id ?: name
		def date = attrs.remove('value')
		
		//if date is not null Create values for  date text field and supporting hidden text fields need by grails
		if(date!=null){
		long milliseconds = date.getTime();
		def day = new Date(milliseconds).getDay()
		def month =  new Date(milliseconds).getMonth()+1
		def year = new Date(milliseconds).getYear()+ 1900; 
		SimpleDateFormat sdfOutput = new SimpleDateFormat  ("MM/dd/yyyy") ;
		def value = sdfOutput.format(date);
		
		
		out << "<input type=\"text\" name=\"${name}\" id=\"${id}\" id=\"${id}\" value=\"${value}\" />"
		out << "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" value=\"${day}\"/>"
		out << "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" value=\"${month}\"/>"
		out << "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" value=\"${year}\"/>"
		}//otherwise Create date text field and supporting hidden text fields without values need by grails
		else{
		out << "<input type=\"text\" name=\"${name}\" id=\"${id}\" />"
		out << "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" />"
		out << "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" />"
		out << "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" />"
		}
		//Code to parse selected date into hidden fields required by grails
		out << "<script type=\"text/javascript\"> \$(document).ready(function(){"
		out << "\$(\"#${name}\").datepicker({"
		out << "onClose: function(dateText, inst) {"
		out << "\$(\"#${name}_month\").attr(\"value\",new Date(dateText).getMonth() +1);"
		out << "\$(\"#${name}_day\").attr(\"value\",new Date(dateText).getDate());"
		out << "\$(\"#${name}_year\").attr(\"value\",new Date(dateText).getFullYear());"
		out << "}"
		out << "});"
		out << "})</script>"

	}
	
 
}