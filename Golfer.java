//Liz Crittenden
//Project 4 CSC 103

public class Golfer implements Comparable<Golfer>{

//Invariant of the Golfer Class:
// 1. The objects created from this class represent the players in a season of golf
// 2. The name of the player is stored in the data member lastame
// 3. The number of rounds played is stored in the data member numberOfRounds
// 4. The player's handicap is stored in the data member handicap
// 5. The player's average score is stored in the data member averageScore

   private String lastname;
   private int numberOfRounds;
   private int handicap;
   private double averageScore;
   
// Default constructor
   public Golfer(){
      lastname = null;
      numberOfRounds = 0;
      handicap = 0;
      averageScore = 0;
   }

// Constructor to set lastname to parameter 
// Used for the compareTo method   
   public Golfer(String name){
      lastname = name;
   }
 
 // Constructor to set data members to parameter values
   public Golfer(String name, int rounds, int hcap, double score){
      lastname = name;
      numberOfRounds = rounds;
      handicap = hcap;
      averageScore = score;
   }
   
// Accessor to return lastname
   public String getLastname(){
      return lastname;
   }

// Accessor to return numberOfRounds
   public int getNumberOfRounds(){
      return numberOfRounds;
   }
   
// Accessor to return handicap
   public int getHandicap(){
      return handicap;
   }
   
// Accessor to return averageScore
   public double getAverageScore(){
      return averageScore;
   }
   
// Modifier to set lastname
   public void setLastname(String newName){
      lastname = newName;
   }
   
// Modifier to set numberOfRounds
   public void setNumberOfRounds(int num){
      numberOfRounds = num;
   }
   
// Modifier to set handicap
   public void setHandicap(int newHandicap){
      handicap = newHandicap;
   }
   
// Modifier to set averageScore
   public void setAverageScore(double score){
      averageScore = score;
   }
   
// Modifier to update numberOfRounds and averageScore
   public void addNewScore(int newRounds, double newScore){
      numberOfRounds = numberOfRounds + newRounds;
      averageScore = (averageScore + newScore)/numberOfRounds;
   }
   
 //Compares two golfers by last name to determine which comes first alphabetically
 // Return: -1 or 1 depending on which is greater
 // 0 if objects are equal
   public int compareTo(Golfer candidate){
      return this.lastname.compareTo(candidate.lastname);
      }
     
// toString for printing golfer data
   public String toString(){
      return "Last name: " + this.lastname + ". Number of rounds: " + this.numberOfRounds + ". Handicap: " + this.handicap + ". Average Score: " + this.averageScore + ".";
   } 
}