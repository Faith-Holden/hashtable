package solution;

public class HashMap {
    private Node[] hashtable;
    private int itemNumber = 0;
    public HashMap(){
        hashtable = new Node[10];
    }

    private class Node{
        Node prevNode;
        Node nextNode;
        String key;
        String value;
        Node(String key, String value){
            this.key = key;
            this.value = value;
            itemNumber++;
        }
    }

    public boolean containsKey(String key){
        return findEntry(key) != null;
    }

    public String get(String key){
        Node tempNode = findEntry(key);
        if(tempNode!=null){
            return tempNode.value;
        }else {
            return "No such key found in the hashmap!";
        }

    }

    private Node findEntry(String key){
        int hash = Math.abs(key.hashCode()) % hashtable.length;
        Node tempNode = hashtable[hash];
        if(tempNode!=null){
            while(!tempNode.key.equals(key)){
                if(tempNode.nextNode==null){
                    return null;
                }else{
                    tempNode = tempNode.nextNode;
                }
            }
            return tempNode;
        }else{
            return null;
        }
    }

    public void put(String key, String value){
        Node newNode = new Node(key,value);
        Node tempNode = findEntry(key);
        int hash = Math.abs(key.hashCode()) % hashtable.length;

        if(itemNumber > hashtable.length * 0.75){
//            itemNumber=0;
            Node[] originalArrayCopy = hashtable.clone();
            hashtable = new Node[originalArrayCopy.length*2];
            for(int i = 0; i<originalArrayCopy.length; i++){
                if(originalArrayCopy[i]!=null){
                    while(originalArrayCopy[i]!=null){
                        addToArray(originalArrayCopy[i]);
                        originalArrayCopy[i]=originalArrayCopy[i].nextNode;
                    }
                }
            }
        }

        if(tempNode==null){
            hashtable[hash]=newNode;
        }else{
            if(tempNode.nextNode!=null){
                newNode.nextNode = tempNode.nextNode;

            }
            newNode.prevNode = tempNode;
            tempNode.nextNode = newNode;
        }
    }

    private void addToArray (Node nodeToAdd){
        int hash = Math.abs(nodeToAdd.key.hashCode()) % hashtable.length;
        Node tempNode = hashtable[hash];

        if(tempNode==null){
            hashtable[hash]=nodeToAdd;
        }else{
            while(tempNode.nextNode!=null){
                tempNode = tempNode.nextNode;
            }
            tempNode.nextNode = nodeToAdd;
        }
//        itemNumber++;
    }

    public void remove(String key){
        int hash = Math.abs(key.hashCode()) % hashtable.length;
        Node tempNode = findEntry(key);
        if(tempNode!=null){
            if(tempNode.prevNode!=null){
                tempNode.prevNode.nextNode = tempNode.nextNode;
            }else if(tempNode.nextNode!=null){
                hashtable[hash]=tempNode.nextNode;
                tempNode.nextNode.prevNode=null;
            }else{
                hashtable[hash]=null;
            }
            itemNumber--;
        }
    }

    public int size(){
        return itemNumber;
    }

}
