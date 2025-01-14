import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main 
{
  
  // array of 10 Country objects
  private Country[] countryArray = new Country[10];  
  // index of current shown country
  private int index = 0;
  private JTextField ans;
  private String[] questions = new String[] {"What is the name of this country?", "What is this country's capital?", "What is this country's main spoken language?"};
  private int questionNumber;

  // GUI elements
  private JFrame jFrame = new JFrame("Countries");
  private ImageIcon img;
  private JLabel imageLabel;
  private JLabel outputLabel;
  
  public static void main(String[] args) {
    // Create the GUI
    Main gui = new Main();
    gui.loadCountries();
    gui.showCountry();
  }

  /* loadCountries() reads in the data from the countries-data.csv file and fills in the countryArray with data*/
  public void loadCountries() 
  {
    // Open the data file - do not change
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");
    Scanner scan = null;
    try {
      scan = new Scanner(file);
    } catch(FileNotFoundException e) { 
        System.out.println("File not found");     
    }
 
    // For loop that creates a new Country object and assigns it into a slot of countryArray
    for (int i = 0; i <countryArray.length; i ++)
    {
      String input = scan.nextLine();
      String[] data = input.split(",");
      System.out.println("Read in " + data[0]);
      Country country = new Country(data[0], data[1], data[2], data[3]);
      countryArray[i] = country;
    }
  }

  /* showCountry() will show the image associated with the current country*/
  public void showCountry() {
    String imagefile = countryArray[index].getImage();
    img = new ImageIcon("/workspaces/Countries/workspace/" + imagefile);
    imageLabel.setIcon(img);
  }
  
  /*No precondition
  Postcondition: nextButton increments index, ff the index is greater than 9, reset it back to 0, the outputLable and input box are cleared, and the image of the current country is displayed*/
  public void nextButtonClick()
  {
    index++;
    if (index>9)
    {
      index = 0;
    }
    outputLabel.setText("");
    ans.setText("");
    showCountry();   
  }
  
  /*No precondition
  Postcondition: The current country's name, capital, and most spoken language is printed to the UI and the console.*/
  public void reviewButtonClick()
  {
     Country currCountry = countryArray[index];
     System.out.println(currCountry.toString());
     outputLabel.setText(currCountry.toString());
  }

  /*No precondition
   Postcondition: outputLabel gets cleared, before a random question from the questions array is displayed on the screen.
  *questionNumber will also be used when the ans button is pressed to determine what to compare the input to.*/
  public void quizButtonClick()
  {
    outputLabel.setText("");
    questionNumber = (int)(Math.random()*3);
    outputLabel.setText(questions[questionNumber]);
  }

public Main() {
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // buttons at the top
        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton newButton = new JButton("Next");
        JButton ansButton = new JButton("Submit");
        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(newButton);
        jFrame.add(ansButton);
        
        // create a new image icon
        img = new ImageIcon("worldmap.jpg");
        // create a label to display image
        imageLabel = new JLabel(img);
        // and one for output
        outputLabel = new JLabel();
        jFrame.add(imageLabel);
        jFrame.add(outputLabel);

        ans = new JTextField(20);
        jFrame.add(ans);

        jFrame.setVisible(true);
        // add event listener for button click
        reviewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      reviewButtonClick();
    }
        });
    quizButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      quizButtonClick();
    }
    });

    ansButton.addActionListener(new ActionListener() {
      /*Precondition: User input must be with the first letter capital and the rest lower case
      *Postcondition: when the ansButton is clicked, the country that is is displayed on the screen will be set to count. questionNumber is then used to determine which aspect
      *(country name, capital, or language) of the country is being questioned, and then determines if the user's input is the correct answer or not*/
      public void actionPerformed(ActionEvent e) 
      {
        Country count = countryArray[index];
        if (questionNumber == 0)
        {
          if (ans.getText().equals(count.getCountry()))
          {
            outputLabel.setText("Correct!");
          }
          else 
        {
          outputLabel.setText("Incorrect. Try again.");
        }
        }

        if (questionNumber == 1)
        {
          if (ans.getText().equals(count.getCapital()))
          {
            outputLabel.setText("Correct!");
          }
          else 
        {
          outputLabel.setText("Incorrect. Try again.");
        }
        }

        if (questionNumber == 2)
        {
          if (ans.getText().equals(count.getLang()))
          {
            outputLabel.setText("Correct!");
          }
          else 
        {
          outputLabel.setText("Incorrect. Try again.");
        }
        }
      }
    });
   
   newButton.addActionListener(new ActionListener()  {
    public void actionPerformed(ActionEvent e) 
    {
      nextButtonClick();
    }
   });
  }
}