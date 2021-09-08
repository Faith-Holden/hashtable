package solution;

public class Main {
    public static void main(String[]args){
        HashMap myHashMap = new HashMap();
        int counter = 0;
        while(counter<25){
            myHashMap.put("Key"+counter, "Val"+counter);
            System.out.println("Key"+counter + " maps " + "Val"+counter);
            counter++;
        }
        System.out.print("The value associated with Key5 is ");
        System.out.println(myHashMap.get("Key5"));
        System.out.print("Contains key 24 =  "+ myHashMap.containsKey("Key24"));
        System.out.println(myHashMap.size()+ " is the size.");

        myHashMap.remove("Key24");
        System.out.println("After deleting Key24, ");
        System.out.println(myHashMap.size()+ " is the new size.");

    }
}
