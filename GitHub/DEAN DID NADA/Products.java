
//The class products, where all products in the database will be an object with variables upc, name, and the date the object was scanned
public class Products
{
	 
	 public long upc;
	 public string name;
	 public int month;
	 public  int day;
	 public int year;
	 
	 public Products()
	 {
	 upc = 000000;
	 name = "";
	 day = 01;
	 month = 01;
	 year = 2018;
	 }
	 
	 
	 public Products (long upc, string name, int day, int month, int year)
	 {
	 this.upc = upc;
	 this.name = name;
	 this.day = day;
	 this.month = month;
	 this.year = year;
	 }
	 
	 
	public void setUPC(long theUPCNum)
	{
	upc = theUPCNum;
	}

	public void setName(string name)
	{
	this.name = name;
	}
	
	public void setDay( int day)
	{
	this.day = day;
	}
	
	public void setMonth ( int month) 
	{
	this.month = month;
	}
	
	
	public void setYear ( int year)
	{
	this.year = year;
	}	
	
	
	public string getName()
	{
	return name;
	}
	
	public long getUPC()
	{
	return upc;
	}
	
	public int getDay()
	{
	return day;
	}

	public int getMonth()
	{
	return month;
	}
	
	public int getYear()
	{
	return year;
	}
		
	
}
