package Lab;

import java.util.List;

public class Monitor extends Thread{
    List<Double> weight;
    Double sum;
    String threadInfo;
    int start, end, partNum;

    public Monitor(List<Double> weight, int start , int end, int num)
    {
        this.start = start;
        this.end = end;
        this.partNum = num;
        this.weight = weight;
    }

    @Override
    public void run() {
        threadInfo = threadInfo();
        findOwnValues();
    }

    public String threadInfo(){
        return  "Name: " + Thread.currentThread().getName() + "\n" +
                "State: " + Thread.currentThread().getState() + "\n" +
                "Priority: " + Thread.currentThread().getPriority() + "\n" +
                "Id: " + Thread.currentThread().getId() + "\n" +
                "Is alive : " + Thread.currentThread().isAlive() + "\n\n";
    }

    public void findOwnValues()
    {
        System.out.println(this);
        System.out.println("Calculated part " + partNum + " sum = " + sum(start,end) + '\n');
        sum = sum(start,end);
    }

    public Double sum(int start,int end){
        Double sum = 0.0;
        for (int i = start; i < end; i++) {
         sum += weight.get(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Part: [ ");
        for( int i = start; i < end; ++i) {
          stringBuilder.append(weight.get(i)).append(" ");
        }
        stringBuilder.append("]");
        return  stringBuilder.toString();
    }
}
