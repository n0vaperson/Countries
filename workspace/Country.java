public class Country
{
  //instance variables
  private String country;
  private String capital;
  private String lang;
  private String image;

  //constructor with inputs
  public Country(String country, String capital, String lang, String image)
  {
    this.country = country;
    this.capital = capital;
    this.lang = lang;
    this.image = image;
  }

  //default constructor
  public Country()
  {
    country = "Undefined";
    capital = "Undefined";
    lang = "Undefined";
    image = "Undefined";
  }

  //getter/setter methods
  public String getCountry() 
  {
    return country;
  }

  public String getCapital() 
  {
    return capital;
  }

  public String getLang() 
  {
    return lang;
  }

  public String getImage() 
  {
    return image;
  }


  //toString method that returns the country's name, capital and most used language
  public String toString()
  {
    return country +"'s capital is " + capital + " and its most used language is " + lang;
  }
  
}