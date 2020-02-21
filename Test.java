public class HashBucket {
    private static class Node{
        private int key;
        private int value;
        Node next;
        public Node(int key,int val){
            this(key,val,null);
        }
        public Node(int key,int value,Node next){
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }

    private Node[] arr;
    private int size;
    private static final double LOADFACTOR=0.75;

    public HashBucket(){
        arr=new Node[10];
        size=0;
    }

    public void put(int key,int val){
        int index=key%arr.length;
        for(Node cur=arr[index];cur!=null;cur=cur.next){
            if(cur.key==key){
                cur.value=val;
                return;
            }
        }
        Node node=new Node(key,val,arr[index]);
        arr[index]=node;
        size++;
        if(getLoadfactor()>LOADFACTOR){
            resize();
        }
    }
    private double getLoadfactor(){
        return size*(0.1)/arr.length;
    }
    private void resize(){
        Node[] newarr=new Node[arr.length*2];
        for(int i=0;i<arr.length;i++){
            Node next=null;
            for(Node cur=arr[i];cur!=null;cur=next){
                next=cur.next;
                int index=cur.key%newarr.length;
                cur.next=newarr[index];
                newarr[index]=cur;
            }
        }
        arr=newarr;
    }

    public int get(int key){
        int index=key%arr.length;
        for(Node cur=arr[index];cur!=null;cur=cur.next){
            if(cur.key==key){
                return cur.value;
            }
        }
        return -1;
    }

    public int size(){
        return size;
    }
}


public class Test {
    public static void main(String[] args) {
        HashBucket hashBucket=new HashBucket();
        hashBucket.put(1,9);
        hashBucket.put(2,8);
        hashBucket.put(3,7);
        System.out.println(hashBucket.get(2));

    }
}
