package Web;

import com.opencsv.*;
import org.jsoup.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WebScraping {
    String str;
    static File csvFile = new File("C:\\Users\\Mobile Computer\\OneDrive\\Desktop\\ScrapCSV.csv");
    HashMap<String[], String> hash = new HashMap<String[], String>();
    WebScraping(){
        str = "";
    }
    /**
     * GetConnetion return connection between pages.
     *
     * @param url
     * @return
     */
    public static Document getConnection(String url) {
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            System.out.println(e);
        }

        return document;
    }

    /**
     * Get Stories from catagaries.
     *
     * @param stories
     * @param label
     */
    public static void printStoriestText(ArrayList<Element> stories, String label) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String arr[] = new String[100];
            int index = -1;
            for (Element story : stories) {
                String str = "";
                Document storyDoc = getConnection(story.attr("href"));
                Elements page = storyDoc.getElementsByTag("body").not("footer").not("header").select("p");//selecting main element in body(excluding header & footer)
                index++;
                for (int i = 0; i < page.size(); i++) {
                    String st = page.text();
                    str += st;
                    System.out.println(st);
                }
                arr[index] = str;
            }
            writer.writeNext(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void PrintHeadlines(Element link) {

        String url = link.attr("href");
//        System.out.println("..........URL........"+url);
        Document document = getConnection(url);

        ArrayList<Element> stories = new ArrayList<>(100);

        // pages of catagory
        Elements pageLink = document.getElementsByTag("main").not("footer").not("header");//selecting main element in body(excluding header & footer)
        Elements pages = pageLink.select("nav").select("ul").select("a[href]"); //Selecting stories from main body

        // First page not contain pages..
        if (pages.isEmpty()) {
            Elements bodyLink = document.getElementsByTag("main").not("footer").not("header");
            Elements bodyStories = bodyLink.select("div").select("a[href]");

            for (Element story : bodyStories) {
                if (!stories.contains(story)) {
                    stories.add(story);
                }
            }
        } // Others pages..
        else {
            for (int i = 0; i < 5; i++) {

                Document document2 = getConnection(url + pages.get(i).attr("href"));
                Elements bodyLink = document2.getElementsByTag("main").not("footer").not("header");
                Elements bodyStories = bodyLink.select("div").select("a[href]");

                for (Element story : bodyStories) {
                    if (!stories.contains(story)) {
                        stories.add(story);

                    }
                }
            }
            printStoriestText(stories, link.text());

        }
        System.out.println("Name of Catagory " + link.text());
        System.out.println("Number of stories " + stories.size());

    }

    
    public String unique() throws FileNotFoundException
    {
        try (Scanner sc = new Scanner(new File("C:\\Users\\lals0\\OneDrive\\Desktop\\Data.csv"))) {

            sc.useDelimiter(",");
            while (sc.hasNext()) {
                str=str+sc.next();
            }


            String [] arr=str.split(" ");
            String temp  []=new String [10000];
            int size=0;
            int count=0;
            // System.out.print(arr.length);

            for (int i=0;i< arr.length;i++) {
                count = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i].equals(arr[j])) {
                        count++;
                    }
                }
                if (count == 1) {
                    temp[size++] = arr[i];
                }
            }

            count=0;
            String re="";
            for (int i=0;i<size;i++){
                re=re+temp[i]+" ";
                count++;
            }
            System.out.println(count);
            return re;
        }
        catch (Exception e){
            System.out.print("");
        }
        return "";
    }

    public  String  Frequent (){

        try (Scanner sc = new Scanner(new File("C:\\Users\\lals0\\OneDrive\\Desktop\\Data.csv"))) {

            sc.useDelimiter(",");
            while (sc.hasNext()) {
                str = str + sc.next();
            }


            String[] arr = str.split(" ");
            String temp[] = new String[100000];

            int size = 0;
            int count = 0;

            //System.out.print(arr.length);

            for (int i = 0; i < arr.length; i++) {
                count = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i].equals(arr[j]))
                        count++;
                }
                if (count > 650) {
                    temp[size++] = arr[i];
                }
            }

            String tepo="";

            count = 0;
            for (int i = 0; i < size; i++) {
                tepo+=temp[i]+" ";
            }
            tepo=RemoveDuplicate(tepo);
            //    System.out.print(tepo);

            temp=tepo.split(" ");
            //count=temp.length;
            String re="";
            for (int i=0;i<10;i++){
                count++;
                System.out.print(temp[i]+" ");
                re=re+temp[i]+" ";
            }
            System.out.print("\n----->"+count);
            return re;

        }

        catch (Exception e){
            System.out.print(e);
        }
        return "";
    }

    public  String RemoveDuplicate(String st){

        String sentence, result = "";
        String allWords[];

        st = st.toLowerCase();  //convert to lower case

        // Split the given sentence to get each word as String array
        allWords = st.split(" ");
        // Use for loop to remove duplicate words
        for(int i=0; i<allWords.length; i++) {
            for(int j=i+1; j<allWords.length; j++) {
                if(allWords[i].equals(allWords[j])) {
                    allWords[j] = "remove";
                }
            }
        }
        // Convert to String
        for(String word: allWords) {
            if(word != "remove") {
                result = result + word + " ";
            }
        }
        // Display given String after removing duplicates
        return  result;
    }

    public  String NumberOfStories(){

        try (Scanner sc = new Scanner(new File("C:\\Users\\lals0\\OneDrive\\Desktop\\Data.csv"))) {

            sc.useDelimiter(",");
            while (sc.hasNext()) {
                str = str + sc.next();
            }
            String[] Pakistan  = str.split("Pakistan");
            String[] Aas_Pass  = str.split("Aas Pass");
            String[]  World = str.split("World");
            String[] Fun  = str.split("Fun Fankar");
            String[] Khel = str.split("Khel");

            System.out.print(Pakistan.length-1);
            System.out.print(Aas_Pass.length-1);
            System.out.print(World.length-1);
            System.out.print(Fun.length-1);
            System.out.print(Khel.length-1);
            int count =0;
            count=(Pakistan.length-1)+(Aas_Pass.length-1)+(World.length-1)+(Fun.length-1)+(Khel.length-1);
            String te="";
            System.out.println("count "+count);
            count=count-100;
            te=te+"Numbers of Stories Retrieved Each"+10+"\nTotal number of stories "+count;
            return  te;
        }
        catch (Exception e){
            System.out.print(e+" exception ");
        }
        return  " exception";

    }


    public String Min()
    {
        try (Scanner sc = new Scanner(new File("C:\\Users\\lals0\\OneDrive\\Desktop\\Data.csv"))) {

            sc.useDelimiter(",");

            while (sc.hasNextLine()) {
                str =str+ sc.nextLine()+"\n";
            }

            String[] arr = str.split("\n");
            System.out.print(arr.length);
            int count=0;
            int index=0;
            int min=Integer.MAX_VALUE;
            for(int i=1;i<arr.length;i++)
            {
                String [] temp=arr[i].split(" ");
                count=temp.length-1;
                if(count<=min)
                {
                    min=count;
                    index=i;

                }
            }
            return arr[index];
        }
        catch (Exception e){
            System.out.print(e);
        }
        return "";
    }


    public String Max()
    {
        String[] arr;
        int index;
        try (Scanner sc = new Scanner(new File("C:\\Users\\lals0\\OneDrive\\Desktop\\Data.csv"))) {

            sc.useDelimiter(",");

            while (sc.hasNextLine()) {
                str =str+ sc.nextLine()+"\n";
            }
            //  System.out.print(str);


            arr = str.split("\n");
            System.out.print(arr.length);
            int count=0;
            index=0;
            int max=Integer.MIN_VALUE;
            for(int i=1;i<arr.length;i++)
            {
                String [] temp=arr[i].split(" ");
                count=temp.length-1;
                if(count>=max)
                {
                    max=count;
                    index=i;
                }

            }
            System.out.print(arr[index]);
            return arr[index];
        }

        catch (Exception e){
            System.out.print(e);
        }

        return "";
    }

    public static void main(String[] args) throws IOException {
        new WebScraping();
        Document document = getConnection("https://www.bbc.com/urdu");

        String title = document.title();
        System.out.println("Title" + title);

        Elements header = document.getElementsByTag("header").not("body").not("footer");
        Element menuLinks = header.select("nav").select("ul").first();
        Elements menu = menuLinks.select("a[href]");
        ArrayList<Element> catagory = new ArrayList<>();

        for (int i = 0; i < menu.size(); i++) {
            if (!catagory.contains(menu.get(i))) {
                catagory.add(menu.get(i));
            }
        }

        System.out.println("No: of catagory in " + catagory.size());

        for (Element link : menu) {
            link.attr("href", "https://www.bbc.com/" + link.attr("href"));
            PrintHeadlines(link);
        }
//        fileWriter.close();
    }
}
