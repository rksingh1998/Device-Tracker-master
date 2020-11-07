/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package external.device.tracker;

/**
 *
 * @author rahul 
 */
import java.io.*;
import java.util.*;
public class tracker {
    public static void totalspace(File f)
    {
    System.out.println("total space is :"+(float)f.getTotalSpace()/1024*1024*1024);
    }
    
    public static void freeandusablespace(File f)
    {
      System.out.println("free space is :"+(float)f.getFreeSpace()/1024*1024*1024);
      float ans=(float)f.getTotalSpace()/1024*1024*1024-(float)f.getFreeSpace()/1024*1024*1024;
      System.out.println("usable space is :"+ans);
    }
    
    public static void findfolderandfiles(File f)
    {
      File[] listoffiles=f.listFiles();
      for(File listfile : listoffiles)
      {
        if(listfile.isFile())
        {
          System.out.println("the file name is :"+listfile.getName());
        }
        else if(listfile.isDirectory())
        {
           System.out.println("the folder name is :"+listfile.getName());
        }
      }
    }
    
    public static void main(String args[])
    {
       Scanner sc=new Scanner(System.in); 
       int t;
      //an array of string taken for giving the names to various drives to be created in windows  
      String[] names=new String[]{"A","B","C","D","E","F","G","H","I","J"};
      //file object is created and allotted to point to each of the drives names
      File[] drives=new File[names.length];
      //an array of booleans to be taken to check the reading permissions for the drive and track its status 
      //whether it is pluuged in or plugged out
      boolean[] isdrive=new boolean[names.length];
      
      //set the initial reading permissions values for each drive variable and assign the object for them
      for(int i=0;i<names.length;i++)
      {
          drives[i]=new File(names[i]+":/");
          isdrive[i]=drives[i].canRead();
      }
      
      System.out.println("waiting for the incoming drives...");
      while(true)
      {
         for(int i=0;i<names.length;i++)
         {
           boolean plugin=drives[i].canRead();
           if(plugin!=isdrive[i]&&plugin==true)
           {
               System.out.println("drive "+names[i]+" has been plugged in");
              do
              {
                System.out.println("choose from following options :");
                System.out.println("1. to find the total space on the device");
                System.out.println("2 to find the free space and usable space");
                System.out.println("3. to find out the files and folders names present on device");
                int choice=sc.nextInt();
                switch(choice)
                {
                    case 1:
                        totalspace(drives[i]);
                        break;
                    case 2:
                        freeandusablespace(drives[i]);
                        break;
                    case 3:
                        findfolderandfiles(drives[i]);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                System.out.println("enter 1 to continue checking and 2 to exit");
                t=sc.nextInt();
              }while(t==1);
              
           }
           else if(plugin==false)
           {
             System.out.println("the device on drive "+names[i]+" is been plugged off");
           
           }
           isdrive[i]=plugin;
         
         }
         // wait before looping
        try { Thread.sleep(100); }
        catch (InterruptedException e) { /* do nothing */ } 
      
      }
    }
}
