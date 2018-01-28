package rubberduckies.fims.adapters;

//The class products, where all products in the database will be an object with variables upc, name, and the date the object was scanned
public class Products
{
	 
	 public String upc;
	 public String name;
	 public String month;
	 public String day;
	 public String year;
	 
	 public Products()
	 {
	 upc = "000000";
	 name = "";
	 day = "1";
	 month = "1";
	 year = "1";
	 }
	 
	 
	 public Products (String upc, String name, String day, String month, String year)
	 {
	 this.upc = upc;
	 this.name = name;
	 this.day = day;
	 this.month = month;
	 this.year = year;
	 }
	 
	 
	public void setUPC(String theUPCNum)
	{
	upc = theUPCNum;
	}

	public void setName(String name)
	{
	this.name = name;
	}
	
	public void setDay(String day)
	{
	this.day = day;
	}
	
	public void setMonth (String month)
	{
	this.month = month;
	}
	
	
	public void setYear (String year)
	{
	this.year = year;
	}	
	
	
	public String getName()
	{
	return name;
	}
	
	public String getUPC()
	{
	return upc;
	}
	
	public String getDay()
	{
	return day;
	}

	public String getMonth()
	{
	return month;
	}
	
	public String getYear()
	{
	return year;
	}
		
	
}
