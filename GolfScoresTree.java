//Liz Crittenden
//Project 4 CSC 103

import java.util.*;
import java.io.*;

public class GolfScoresTree{
   public static void main(String[] args) throws IOException{
      int menu; //Declaring variables
      boolean flag = true;
      String playerName, target;
      int newRounds, newHandicap;
      double newAverage;
      Golfer player, player2, cursor;
      TreeBag <Golfer> bagOfGolfers = new TreeBag <Golfer>();
      File infile = new File("golferinfo.txt"); //Opening input file
      Scanner golfscan = new Scanner(infile);
      Scanner keyboard = new Scanner(System.in);
      if(infile.exists())
         System.out.println();
      else
         infile.createNewFile();
      while(golfscan.hasNextLine()){ //Loops to receive all input from file
         String line = new String(golfscan.nextLine());//Reads line
         String[] words = line.split(" ");//Separates line by whitespace
         playerName = words[0];//Storing data
         newRounds = Integer.parseInt(words[1]);
         newHandicap = Integer.parseInt(words[2]);
         newAverage = Double.parseDouble(words[3]);
         player = new Golfer(playerName, newRounds, newHandicap, newAverage);//Creates golfer object with retrieved info
         bagOfGolfers.add(player); //Add to treeBag
      }
      
      while(flag){//Loops menu until user chooses to exit
         System.out.println("Menu choices:\n1. Display listing to screen of all golfers stats(ordered by last name)");
         System.out.println("2. Display the golfers in current tree format \n3. Find and display one individual golfers stats");
         System.out.println("4. Update an individual golfers stats(by adding an additional score)\n5. Remove a golfer from the Database");
         System.out.println("6. Add a new golfer to the Database \n7. Quit and update datafile");
         menu = keyboard.nextInt();
         if(menu < 1 || menu > 7)
            System.out.println("Please select a valid menu option.");
         switch(menu){
             case 1: //Prints golfers in alphabetical order
               bagOfGolfers.display();
               System.out.println();
               break;
             case 2: //Prints golfers as binary search tree
               bagOfGolfers.displayAsTree();
               System.out.println();
               break;
             case 3: //Retrieves golfer of user's choosing
               System.out.println("Enter the (capitalized) last name of the golfer you'd like to see stats for.");
               target = keyboard.next();
               cursor = bagOfGolfers.retrieve(new Golfer(target));
               if(cursor != null){
                  System.out.println("Last name: " + cursor.getLastname() + ". Number of rounds: " + cursor.getNumberOfRounds() + ". Handicap: " + cursor.getHandicap() + ". Average Score: " + cursor.getAverageScore() + ".");
               }else{
                  System.out.println("That player isn't within this database.");
               }
               System.out.println();
               break;
            case 4: //Updates a golfer's stats
               int totalRounds, totalHandicap, oldrounds;
               double totalScore;
               System.out.println("Enter the (capitalized) last name of the golfer who played a new round.");
               target = keyboard.next();
               cursor = bagOfGolfers.retrieve(new Golfer(target));
               System.out.println("What's the new average score?");
               totalScore = keyboard.nextDouble();
               System.out.println("Enter the new handicap.");
               totalHandicap = keyboard.nextInt();
               oldrounds = cursor.getNumberOfRounds();
               totalRounds = oldrounds + 1;
               if(cursor==null){
                  System.out.println(target + " isn't within this database.");
               } else {
                  cursor.setNumberOfRounds(totalRounds);
                  cursor.setAverageScore(totalScore);
                  cursor.setHandicap(totalHandicap);
                  System.out.println("Information updated:\n" + cursor.toString());
               }
               System.out.println();
               break;
            case 5://Removes golfer from list
               System.out.println("Enter the (capitalized) last name of the golfer you'd like to remove.");
               target = keyboard.next();
               cursor = bagOfGolfers.retrieve(new Golfer(target));
               bagOfGolfers.remove(cursor);
               System.out.println(target + " has been removed from the list.");
               System.out.println();
               break;
           case 6: //Adds new player
               String name;
               int hcap, rounds;
               double score;
               Golfer newPlayer;
               System.out.println("Enter the name, handicap, rounds played, and average score of the new player.");
               name = keyboard.next();
               hcap = keyboard.nextInt();
               rounds = keyboard.nextInt();
               score = keyboard.nextDouble();
               player2 = new Golfer(name, rounds, hcap, score);
               bagOfGolfers.add(player2);
               System.out.println(name + " has been added to the list.");
               System.out.println();
               break;
           case 7://Exits menu
               try{
                  FileOutputStream outstream = new FileOutputStream(infile);
                  PrintWriter writer = new PrintWriter(outstream);
                  BTNode cursor2 = bagOfGolfers.setRoot();
                  writer.println(cursor2.getData().toString());//Writes one line to output file. Ran out of time to finish
                  writer.close();
                  System.out.println("The file has been updated. Goodbye.");
                  flag = false;
               } catch (IOException e){
                  System.out.println("The file could not be updated. Goodbye.");
                  flag = false;
               }
               break;
         }
      }
   }
}