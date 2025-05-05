
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;

 


public class LinkedListExample {
    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4, 5};
        int a=Arrays.stream(arr).sum();
        System.out.println(a);
        a=Arrays.stream(arr).max().getAsInt();
        System.out.println(a);
        a=Arrays.stream(arr).min().getAsInt();
        System.out.println(a); 
        String s="Hello";
        System.out.println(s);
        StringBuilder sb=new StringBuilder(s);
      



        a=(int) Math.sqrt(arr[2]);
        System.out.println(a);

    
LinkedList<String> list = new LinkedList<>(Arrays.asList("hi", "hello"));       
System.out.println(list);
boolean aaa=Arrays.stream(arr).anyMatch(x->x==3);
System.out.println(aaa);  
LinkedList<String> list1 = list.stream().collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
System.out.println(list1);

sb.reverse();
System.out.println(sb.toString());


 
    }
 

 

 

 
}
